package com.pds.curiousmind.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public final class AppConfig {
    private static final String CONFIGURATION_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Error loading configuration properties: " + e.getMessage());
            throw new RuntimeException("Failed to load configuration properties", e);
        } catch (NullPointerException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
            throw new RuntimeException("Configuration file not found", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}

