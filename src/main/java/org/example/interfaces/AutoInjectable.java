package org.example.interfaces;

import java.lang.annotation.*;

/**
 * This annotation is used to mark a field as being automatically injectable.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {
}
