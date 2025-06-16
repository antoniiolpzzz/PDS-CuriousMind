package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

// This class creates a styled button for selecting a course strategy.
public class ImageButton {

    public static JPanel createImageButton(String name, String iconPath, String[] selectedStrategy, List<JButton> strategyButtons, boolean isCard) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        JButton button;

        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        if(isCard){
             button = new JButton(loadIcon(iconPath, 180, 180));
            label.setFont(new Font(FONT_NAME, Font.PLAIN, 25));
        } else {
             button = new JButton(loadIcon(iconPath, 70, 70));
            label.setFont(new Font(FONT_NAME, Font.PLAIN, 12));
        }
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        button.setOpaque(true);
        addHoverEffect(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        strategyButtons.add(button);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));




        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
        panel.add(label);

        JButton finalButton = button;
        button.addActionListener(e -> {
            selectedStrategy[0] = name;
            for (JButton b : strategyButtons) {
                b.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                b.setBackground(Color.WHITE);
            }
            // Borde compuesto: negro + azul grueso
            finalButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)
            );
            finalButton.setBackground(Color.WHITE);
        });

        return panel;
    }
}