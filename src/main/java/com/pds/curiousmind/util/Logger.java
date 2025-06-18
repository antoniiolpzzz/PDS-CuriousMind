package com.pds.curiousmind.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger utility class for logging messages to both console and a temporary log file.
 * Provides methods for logging info and error messages.
 * The log file is created in the system's temporary directory.
 */
public final class Logger {
    private Logger() { /* Utility class: prevent instantiation */ }

    /** * DateTimeFormatter for formatting log timestamps.
     * The format is "yyyy-MM-dd HH:mm:ss".
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** * Temporary log file created in the system's temporary directory.
     * This file is used to store log messages.
     */
    private static File tempLogFile = null;


    static {
        try {
            tempLogFile = File.createTempFile("curiousmind-log-", ".txt");
        } catch (IOException e) {
            System.err.println("[LOGGER INIT ERROR] Could not create temp log file: " + e.getMessage());
        }
    }

    /**
     * Logs an informational message to the console and the log file.
     *
     * @param message The message to log.
     */
    public static void info(String message) {
        String log = "[INFO]  " + now() + " - " + message;
        System.out.println(log);
        writeToFile(log);
    }

    /**
     * Logs an error message to the console and the log file.
     *
     * @param message The error message to log.
     */
    public static void error(String message) {
        String log = "[ERROR] " + now() + " - " + message;
        System.err.println(log);
        writeToFile(log);
    }


    /**
     * Writes a log message to the temporary log file.
     * If the file cannot be written, it logs an error to the console.
     * @param log The log message to write to the file.
     */
    private static void writeToFile(String log) {
        if (tempLogFile != null) {
            try (FileWriter fw = new FileWriter(tempLogFile, true)) {
                fw.write(log + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("[LOGGER FILE ERROR] " + e.getMessage());
            }
        }
    }

    /**
     * Returns the current timestamp formatted as a string.
     * The format is "yyyy-MM-dd HH:mm:ss".
     *
     * @return The current timestamp as a formatted string.
     */
    private static String now() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
