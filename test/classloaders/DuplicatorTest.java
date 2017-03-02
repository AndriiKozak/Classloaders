/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

import java.net.URLClassLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrii_Kozak1
 */
public class DuplicatorTest {

    private Duplicator duplicator;

    @Before
    public void setUp() throws Exception {
        duplicator = new Duplicator(new MyClassLoader(ClassLoader.getSystemClassLoader().getResource("")));
    }

    @After
    public void tearDown() {
    }

    public void testDuplicateObject() throws Exception {

        Object a = new Object();
        Object b = duplicator.duplicate(a);
        assertTrue(!a.equals(b));
        assertTrue(a.getClass().equals(b.getClass()));
    }

    @Test
    public void testDuplicateMyClass() throws Exception {
        MyClass a = new MyClass(42);
        Object b = duplicator.duplicate(a);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(!a.equals(b));
        assertTrue(!a.getClass().equals(b.getClass()));
    }

    @Test(expected = ClassCastException.class)
    public void shouldThrowClasscastExceptionWhenCopying() throws Exception {
        MyClass a = new MyClass(42);
        MyClass b = (MyClass) duplicator.duplicate(a);

    }
}
