package com.pds.curiousmind.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * AppConfig is a utility class that loads configuration properties from a file named "config.properties".
 * It provides a method to retrieve property values by their keys.
 * This class is designed to be used throughout the application to access configuration settings.
 */
public final class AppConfig {
    /**
     * The name of the configuration file that contains application properties.
     * This file should be located in the classpath.
     */
    private static final String CONFIGURATION_FILE = "config.properties";
    /**
     * The Properties object that holds the configuration properties loaded from the file.
     * It is initialized statically to ensure that properties are loaded once when the class is first accessed.
     */
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            Logger.error("Failed to load configuration properties: " + e.getMessage());
            throw new RuntimeException("Failed to load configuration properties", e);
        } catch (NullPointerException e) {
            Logger.error("Configuration file not found: " + CONFIGURATION_FILE);
            throw new RuntimeException("Configuration file not found", e);
        }
    }

    /**
     * Retrieves the value of a property by its key.
     *
     * @param key the key of the property to retrieve
     * @return the value of the property, or null if the key does not exist
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}

