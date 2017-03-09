/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrii_Kozak1
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL... urls) {
        super(urls, ClassLoader.getSystemClassLoader().getParent());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String pathName = name.replace(".", "/") + ".class";
        ClassLoader sc = ClassLoader.getSystemClassLoader();
        String resource = sc.getResource(pathName).toString();
        try {
            URL toDir = new URL(resource.substring(0, resource.length() - pathName.length()));
            super.addURL(toDir);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.loadClass(name);
    }

}
