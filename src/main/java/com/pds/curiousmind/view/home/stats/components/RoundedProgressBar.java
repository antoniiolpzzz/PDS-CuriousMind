package com.pds.curiousmind.view.home.stats.components;

import javax.swing.*;
import java.awt.*;

/**
 * This class provides a custom progress bar with rounded corners for UI progress display.
 */
public class RoundedProgressBar extends JProgressBar {

    public RoundedProgressBar(int value) {
        super(0, 100);
        setValue(value);
        setBorderPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(200, 8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(0, 0, width, height, height, height);

        int progressWidth = (int) (width * getPercentComplete());
        g2.setColor(new Color(0, 200, 80));
        g2.fillRoundRect(0, 0, progressWidth, height, height, height);

        g2.dispose();
    }
}
