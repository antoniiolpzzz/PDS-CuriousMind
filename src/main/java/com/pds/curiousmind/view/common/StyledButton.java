package com.pds.curiousmind.view.common;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
    private final Color hoverBorderColor;
    private final Color normalBorderColor;

    public StyledButton(String text, Color background, Color foreground) {
        super(text);
        setBackground(background);
        setForeground(foreground);
        setFocusPainted(false);
        setContentAreaFilled(false); // importante: lo pintamos nosotros
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setPreferredSize(new Dimension(90, 35));


        setBorder(new EmptyBorder(5, 12, 5, 12)); // padding interno

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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing para bordes suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo redondeado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // <- aquí defines cuán redondo es (20 = muy redondo)

        // Borde redondeado
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // Pintar el texto
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        // Ignorado: usamos nuestro propio relleno
    }
}
