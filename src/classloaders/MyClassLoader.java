/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

import java.net.URL;
import java.net.URLClassLoader;

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
        System.out.println("looking for: "+ name);
        return super.loadClass(name);
       // return findClass(name);
    }
    
}
