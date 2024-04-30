package org.example.implementation;

import org.example.interfaces.SomeOtherInterface;

/**
 * This class is an implementation of the SomeOtherInterface interface.
 * It prints the letter "C" when the doSomeOther() method is called.
 */
public class C implements SomeOtherInterface {

    /**
     * This method is defined by the SomeOtherInterface interface.
     * It prints the letter "C" to the console.
     */
    @Override
    public void doSomeOther() {
        System.out.print("C");
    }
}

