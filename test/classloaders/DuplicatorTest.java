/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

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
        duplicator = new Duplicator(new MyClassLoader());
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

    @Test
    public void testDuplicateMySubClass() throws Exception {
        MySubClass a = new MySubClass(42, 100500);
        Object b = duplicator.duplicate(a);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(!a.equals(b));
        assertTrue(!a.getClass().equals(b.getClass()));
    }

    @Test
    public void testDuplicateMyEnclosingClass() throws Exception {
        MyEnclosingClass a = new MyEnclosingClass(42);
        Object b = duplicator.duplicate(a);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(!a.equals(b));
        assertTrue(!a.getClass().equals(b.getClass()));
    }

    @Test
    public void testDuplicateMySimpleEnclosingClass() throws Exception {
        MySimpleEnclosingClass a = new MySimpleEnclosingClass(new MyClass(42));
        Object b = duplicator.duplicate(a);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(!a.equals(b));
        assertTrue(!a.getClass().equals(b.getClass()));
    }

    @Test
    public void testLoop() throws Exception {
        Loop loop = new Loop();
        loop.setLoop(loop);
        Object b = duplicator.duplicate(loop);
    }
}
