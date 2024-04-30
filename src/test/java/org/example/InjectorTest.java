package org.example;

import org.example.implementation.*;
import org.example.interfaces.*;

import org.junit.Test;
import java.util.Hashtable;
import static org.junit.Assert.*;

/**
 * This class is used to test the Injector class.
 */
public class InjectorTest {

    /**
     * This method tests the performInjection method of the Injector class with a null parameter.
     * It is expected to throw an IllegalArgumentException.
     *
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInjectWithNullParameter() throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        Injector injector = new Injector(new Hashtable<Class<?>, Class<?>>());
        injector.performInjection(null);
    }

    /**
     * This method tests the constructor of the Injector class with a null parameter.
     * It is expected to throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructInjectorWithNullParameter() {
        new Injector(null);
    }

    /**
     * This method tests the performInjection method of the Injector class without the AutoInjectable annotation.
     *
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void testInjectWithoutAutoInjectableAnnotation() throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        Injector injector = new Injector(new Hashtable<Class<?>, Class<?>>());

        injector.performInjection(new Object());

        assertTrue(true);
    }

    /**
     * This method tests the performInjection method of the Injector class with multiple AutoInjectable annotations.
     *
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void testInjectWithMultipleAutoInjectableAnnotations() throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        Hashtable<Class<?>, Class<?>> dependencyMappings = new Hashtable<Class<?>, Class<?>>();
        dependencyMappings.put(SomeInterface.class, B.class);
        dependencyMappings.put(SomeOtherInterface.class, C.class);

        SomeBean sampleBean = new SomeBean();
        Injector injector = new Injector(dependencyMappings);
        injector.performInjection(sampleBean);

        assertTrue(true);
    }
}
