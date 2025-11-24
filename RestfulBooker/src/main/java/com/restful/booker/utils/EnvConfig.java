package com.restful.booker.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvConfig {

    // This object stores key-value pairs from the properties file
    private static final Properties properties = new Properties();

    static {
        // Load the properties file from the classpath resources
        try (InputStream input = EnvConfig.class
                .getClassLoader().getResourceAsStream("parameters.properties")) {

            if (input == null) {
                throw new RuntimeException("parameters.properties not found in resources");
            }

// Load all key-value pairs into the Properties object
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Could not load parameters.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}

