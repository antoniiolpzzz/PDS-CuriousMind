package com.pds.curiousmind.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * Utility class for loading and accessing application configuration properties.
 * <p>
 * The {@code AppConfig} class loads configuration properties from a file named {@code config.properties}
 * located in the classpath. It provides a static method to retrieve property values by their keys.
 * The properties are loaded once when the class is first accessed, ensuring efficient access
 * throughout the application's lifecycle.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     String dbUrl = AppConfig.get("database.url");
 *     String adapterClass = AppConfig.get("persistence.adapter.implementation");
 * </pre>
 * </p>
 *
 * <p>
 * If the configuration file is missing or cannot be loaded, an error is logged and a runtime exception is thrown.
 * </p>
 *
 * @author antoniolopeztoboso
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
     * @return the value of the property, or {@code null} if the key does not exist
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
