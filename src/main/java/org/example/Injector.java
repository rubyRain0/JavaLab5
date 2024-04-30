package org.example;

import org.example.interfaces.AutoInjectable;
import java.util.*;
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;

/**
 * This class is a utility for performing dependency injection.
 */
public final class Injector {
    private final Map<? extends Class<?>, ? extends Class<?>> injectionRules;

    /**
     * Constructs a new instance of Injector.
     *
     * @param injectionRules The mapping of interfaces to their implementations.
     * @throws IllegalArgumentException if the injectionRules parameter is null.
     */
    public Injector(Map<? extends Class<?>, ? extends Class<?>> injectionRules) throws IllegalArgumentException {
        if (injectionRules == null) {
            throw new IllegalArgumentException("The injectionRules parameter cannot be null.");
        }

        this.injectionRules = injectionRules;
    }

    /**
     * Injects dependencies into the specified object.
     *
     * @param <T> The type of the object.
     * @param target The object into which to inject dependencies.
     * @return The object with dependencies injected.
     * @throws InstantiationException if an error occurs while instantiating a dependency.
     * @throws IllegalAccessException if an error occurs while accessing a dependency.
     */
    public <T> T performInjection(T target) throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        if (target == null) {
            throw new IllegalArgumentException("The target parameter cannot be null.");
        }

        List<Field> annotatedFields = getAnnotatedFields(target, AutoInjectable.class);

        for (Field field : annotatedFields) {
            Class<?> implementationClass = injectionRules.get((Class<?>) field.getGenericType());

            if (implementationClass == null) {
                continue;
            }

            Object dependencyInstance = implementationClass.newInstance();

            field.setAccessible(true);
            field.set(target, dependencyInstance);
        }

        return target;
    }

    /**
     * Retrieves the fields of the specified object that are annotated with the specified annotation.
     *
     * @param obj The object from which to retrieve the fields.
     * @param annotation The annotation class to look for.
     * @return A list of fields annotated with the specified annotation.
     */
    private static List<Field> getAnnotatedFields(Object obj, Class<? extends Annotation> annotation) {
        List<Field> annotatedFields = new ArrayList<>();
        Class<?> currentClass = obj.getClass();

        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    annotatedFields.add(field);
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return annotatedFields;
    }
}