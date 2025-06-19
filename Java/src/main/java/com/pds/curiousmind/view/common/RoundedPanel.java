package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;

/**
 * RoundedPanel is a custom JPanel with rounded corners.
 * Useful for creating visually appealing UI panels.
 */
public class RoundedPanel extends JPanel {
    private int cornerRadius;

    /**
     * Constructs a RoundedPanel with the specified corner radius.
     *
     * @param radius The radius of the panel's corners.
     */
    public RoundedPanel(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
    }

    /**
     * Paints the panel with rounded corners using anti-aliasing.
     *
     * @param g The Graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();
    }
}