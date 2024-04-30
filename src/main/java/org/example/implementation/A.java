package org.example.implementation;

import org.example.interfaces.SomeInterface;

/**
 * This class is an implementation of the SomeInterface interface.
 * It prints the letter "A" when the doSomething() method is called.
 */
public class A implements SomeInterface {

    /**
     * This method is defined by the SomeInterface interface.
     * It prints the letter "A" to the console.
     */
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}