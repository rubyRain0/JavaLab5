package org.example;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String configFilePath = "resources/config.properties";

        try (Reader reader = new FileReader(configFilePath)) {
            try {
                Properties configProperties = new Properties();
                configProperties.load(reader);

                Map<Class<?>, Class<?>> dependencyMappings = createDependencyMappingsFromProperties(configProperties);

                Injector injector = new Injector(dependencyMappings);
                SomeBean someBean = new SomeBean();

                injector.performInjection(someBean);
                someBean.foo();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Creates a mapping of interfaces to their implementations from the given properties.
     *
     * @param properties The properties containing the mappings.
     * @return A map of interfaces to their implementations.
     */
    private static Map<Class<?>, Class<?>> createDependencyMappingsFromProperties(Properties properties) {
        Map<Class<?>, Class<?>> mappings = new HashMap<>();

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);

            try {
                mappings.put(Class.forName(key), Class.forName(value));
            } catch (ClassNotFoundException ex) {
                System.out.println("Warning: Could not find class for key-value pair: " + key + "=" + value);
            }
        }

        return mappings;
    }
}
