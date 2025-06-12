package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        var url = getClass().getClassLoader().getResource(imagePath);
        if (url == null) {
            System.err.println("No se encontró la imagen: " + imagePath);
        } else {
            backgroundImage = new ImageIcon(url).getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

