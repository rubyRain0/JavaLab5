package org.example;

import org.example.interfaces.AutoInjectable;
import org.example.interfaces.SomeInterface;
import org.example.interfaces.SomeOtherInterface;

/**
 * This class represents a bean with two dependencies that are automatically injected.
 */
public class SomeBean {

    /**
     * This field is of type SomeInterface and is automatically injected.
     */
    @AutoInjectable
    private SomeInterface _field1;

    /**
     * This field is of type SomeOtherInterface and is automatically injected.
     */
    @AutoInjectable
    private SomeOtherInterface _field2;

    /**
     * This method uses the two injected dependencies.
     */
    public void foo() {
        _field1.doSomething();
        _field2.doSomeOther();
    }
}
