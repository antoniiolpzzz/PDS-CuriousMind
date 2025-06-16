package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Utility class for loading and scaling icons for the UI.
 */
public class LoadIcon {

    /**
     * Loads an image icon from the given resource path and scales it to the specified size.
     *
     * @param path   The resource path to the image.
     * @param width  The desired width of the icon.
     * @param height The desired height of the icon.
     * @return The scaled ImageIcon, or null if the resource is not found.
     */
    public static ImageIcon loadIcon(String path, int width, int height) {
        URL url = LoadIcon.class.getClassLoader().getResource(path);
        if (url == null) {
            System.err.println("Icon not found: " + path);
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(url);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}