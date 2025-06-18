package com.pds.curiousmind.view.common;

import com.pds.curiousmind.util.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Utility class for loading and scaling icons for the UI.
 */
public class LoadIcon {

    /**
     * Loads an image icon from the given resource pathORurl and scales it to the specified size.
     *
     * @param pathORurl   The resource pathORurl to the image.
     * @param width  The desired width of the icon.
     * @param height The desired height of the icon.
     * @return The scaled ImageIcon, or null if the resource is not found.
     */
    public static ImageIcon loadIcon(String pathORurl, int width, int height) {
        Image scaledImage;
        ImageIcon originalIcon = loadFromClasspath(pathORurl);
        if (originalIcon == null) {
            originalIcon = loadFromFile(pathORurl);
        }
        if (originalIcon == null) {
            originalIcon = loadFromUrl(pathORurl);
        }
        if (originalIcon == null || originalIcon.getImage() == null) {
            System.err.println("Icon not found or image is null: " + pathORurl);
            originalIcon = new ImageIcon();
        }
        try{
            scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            Logger.error("Failed to scale icon: " + pathORurl + " - " + e.getMessage());
            return new ImageIcon();
        }
        return new ImageIcon(scaledImage);
    }

    /**
     * Loads an image icon from the classpath.
     *
     * @param path The resource path to the image.
     * @return The ImageIcon, or null if the resource is not found.
     */
    private static ImageIcon loadFromClasspath(String path) {
        try {
            URL url = LoadIcon.class.getClassLoader().getResource(path);
            if (url != null) {
                return new ImageIcon(url);
            }
        } catch (Exception ignored) {}
        Logger.error("Icon resource not found in classpath: " + path);
        return null;
    }

    /**
     * Loads an image icon from a file path.
     *
     * @param path The file path of the image.
     * @return The ImageIcon, or null if the file does not exist.
     */
    private static ImageIcon loadFromFile(String path) {
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            return new ImageIcon(file.getAbsolutePath());
        }
        Logger.error("Icon file not found: " + path);
        return null;
    }

    /**
     * Loads an image icon from a URL.
     *
     * @param urlString The URL of the image.
     * @return The ImageIcon, or null if the URL is invalid or the image cannot be loaded.
     */
    private static ImageIcon loadFromUrl(String urlString) {
        try {
            URL url = java.net.URI.create(urlString).toURL();
            return new ImageIcon(url);
        } catch (Exception e) {
            Logger.error("Failed to load icon from URL: " + urlString + " - " + e.getMessage());
            return null;
        }
    }
}