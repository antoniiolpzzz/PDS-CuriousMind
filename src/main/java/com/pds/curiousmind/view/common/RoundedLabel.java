package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {
    private Color backgroundColor = new Color(245, 245, 245);
    private Color borderColor = new Color(200, 200, 200);

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

    public void setLabelBackground(Color bg) {
        this.backgroundColor = bg;
        repaint();
    }

    public void setLabelBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public Color getLabelBackground() {
        return backgroundColor;
    }

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
