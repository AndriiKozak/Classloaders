/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 *
 * @author Andrii_Kozak1
 */
public class Duplicator {

    private final ClassLoader classLoader;

    public Duplicator(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Object duplicate(Object target) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class targetClass = target.getClass();
        Class dupeClass = classLoader.loadClass(targetClass.getName());
        Constructor[] constructors = dupeClass.getConstructors();
        Object dupe = null;
        Optional<Constructor> zeroArgumentConstructor = Arrays.stream(constructors).filter(c -> c.getParameters().length == 0).findAny();
        if (zeroArgumentConstructor.isPresent()) {
            dupe = zeroArgumentConstructor.get().newInstance();
        } else {
            int i = 0;
            while (dupe == null && i < constructors.length) {
                Parameter[] parameters = constructors[i].getParameters();
                Object[] values = new Object[parameters.length];
                for (int j = 0; j < parameters.length; j++) {
                    Class c = parameters[j].getType();
                    if (c.isPrimitive()) {
                        if (c.equals(boolean.class)) {
                            values[j] = Boolean.FALSE;
                        }
                        if (c.equals(float.class) || c.equals(double.class)) {
                            values[j] = new Float(0);
                        } else {
                            values[j] = new Integer(0);
                        }
                    } else {
                        values[j] = null;
                    }
                }
                try {
                    dupe = constructors[i].newInstance(values);
                } catch (Exception e) {
                    i++;
                }
            }
        }
        if (dupe == null) {
            throw new InstantiationException();
        }

        Map<String, Field> dupeFields = getAllFields(dupeClass);
        try {
            for (Entry<String, Field> entry : getAllFields(targetClass).entrySet()) {
                Field targetField = entry.getValue();
                boolean accesible = targetField.isAccessible();
                Field dupeField = dupeFields.get(entry.getKey());
                targetField.setAccessible(true);
                dupeField.setAccessible(true);
                Object value = targetField.get(target);
                dupeField.set(dupe, value);
                targetField.setAccessible(accesible);
                dupeField.setAccessible(accesible);

            }
        } catch (SecurityException ex) {
            throw new RuntimeException(ex);
        }
        return dupe;
    }

    private Map<String, Field> getAllFields(Class clazz) {
        Map<String, Field> result = new HashMap<>();

        for (Class parent = clazz; parent != null; parent = parent.getSuperclass()) {
            Arrays.stream(clazz.getDeclaredFields()).forEach(field -> result.put(field.getName(), field));
        }
        return result;
    }
}
