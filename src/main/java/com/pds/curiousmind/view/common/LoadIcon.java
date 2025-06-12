package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoadIcon {

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
