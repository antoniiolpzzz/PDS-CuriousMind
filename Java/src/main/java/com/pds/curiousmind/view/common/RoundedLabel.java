package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;
import static com.pds.curiousmind.view.common.GlobalConstants.*;

/**
 * A custom JLabel with rounded corners, custom background, and border color.
 * This label is intended for UI components where a visually appealing, rounded style is desired.
 */
public class RoundedLabel extends JLabel {
    private Color backgroundColor = new Color(245, 245, 245);
    private Color borderColor = new Color(200, 200, 200);

    /**
     * Constructs a RoundedLabel with the specified text and default styling.
     *
     * @param text the text to be displayed in the label
     */
    public RoundedLabel(String text) {
        super(text);
        setOpaque(false);
        setFont(new Font(FONT_NAME, Font.PLAIN, 18));
        setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setPreferredSize(new Dimension(300, 50));
        setHorizontalAlignment(SwingConstants.LEFT);
    }

    /**
     * Sets the background color of the label.
     *
     * @param bg the new background color
     */
    public void setLabelBackground(Color bg) {
        this.backgroundColor = bg;
        repaint();
    }

    /**
     * Sets the border color of the label.
     *
     * @param color the new border color
     */
    public void setLabelBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    /**
     * Gets the current background color of the label.
     *
     * @return the background color
     */
    public Color getLabelBackground() {
        return backgroundColor;
    }

    /**
     * Paints the component with rounded corners and a custom border.
     *
     * @param g the Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        g2.dispose();
        super.paintComponent(g);
    }
}
