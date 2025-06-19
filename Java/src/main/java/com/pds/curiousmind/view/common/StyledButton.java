package com.pds.curiousmind.view.common;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.pds.curiousmind.view.common.GlobalConstants.*;


/**
 * StyledButton is a custom JButton with rounded corners, custom border colors,
 * and hover effects for a modern UI appearance.
 */
public class StyledButton extends JButton {
    private final Color hoverBorderColor;
    private final Color normalBorderColor;

    /**
     * Constructs a StyledButton with specified text, background, and foreground colors.
     * Sets up custom border, font, and hover effects.
     *
     * @param text The button label.
     * @param background The background color.
     * @param foreground The text color.
     */
    public StyledButton(String text, Color background, Color foreground) {
        super(text);
        setBackground(background);
        setForeground(foreground);
        setFocusPainted(false);
        setContentAreaFilled(false); // we paint the background ourselves
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        setPreferredSize(new Dimension(190, 35));

        setBorder(new EmptyBorder(5, 12, 5, 12)); // internal padding

        normalBorderColor = background.equals(Color.BLACK) ? Color.DARK_GRAY : new Color(200, 200, 200);
        hoverBorderColor = background.equals(Color.BLACK) ? Color.LIGHT_GRAY : new Color(150, 150, 255);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                borderColor = hoverBorderColor;
                repaint();
            }
            public void mouseExited(MouseEvent e) {
                borderColor = normalBorderColor;
                repaint();
            }
        });

        borderColor = normalBorderColor;
    }

    private Color borderColor;

    /**
     * Paints the button with rounded corners and custom border color.
     * Also applies anti-aliasing for smooth edges.
     *
     * @param g The Graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Enable anti-aliasing for smooth borders
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Draw rounded border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // Paint the button text and icon
        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * Overridden to ignore default content area filling,
     * as the button uses custom painting.
     *
     * @param b Ignored parameter.
     */
    @Override
    public void setContentAreaFilled(boolean b) {
        // Ignored: we use our own fill
    }
}