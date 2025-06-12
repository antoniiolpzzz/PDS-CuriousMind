package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
    private Color hoverBorderColor;
    private Color normalBorderColor;

    public StyledButton(String text, Color background, Color foreground) {
        super(text);
        setBackground(background);
        setForeground(foreground);
        setFocusPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);
        setMaximumSize(new Dimension(200, 40));
        setPreferredSize(new Dimension(200, 40));

        normalBorderColor = background.equals(Color.BLACK) ? Color.DARK_GRAY : new Color(200, 200, 200);
        hoverBorderColor = background.equals(Color.BLACK) ? Color.LIGHT_GRAY : new Color(150, 150, 255);

        setBorder(BorderFactory.createLineBorder(normalBorderColor, 1));
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(hoverBorderColor, 2));
            }
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(normalBorderColor, 1));
            }
        });
    }


}
