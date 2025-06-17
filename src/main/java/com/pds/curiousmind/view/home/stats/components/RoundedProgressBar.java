package com.pds.curiousmind.view.home.stats.components;

import javax.swing.*;
import java.awt.*;

/**
 * RoundedProgressBar is a custom JProgressBar with rounded corners,
 * designed for visually appealing progress display in the UI.
 */
public class RoundedProgressBar extends JProgressBar {

    /**
     * Constructs a RoundedProgressBar with the specified initial value.
     * Sets up appearance and preferred size.
     *
     * @param value The initial progress value (0-100).
     */
    public RoundedProgressBar(int value) {
        super(0, 100);
        setValue(value);
        setBorderPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(200, 8));
    }

    /**
     * Paints the progress bar with rounded corners and custom colors.
     * Uses anti-aliasing for smooth rendering.
     *
     * @param g The Graphics context to use for painting.
     */
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