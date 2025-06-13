package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

// This class creates a styled button for selecting a course strategy.
public class StrategyButton {

    public static JPanel createStrategyButton(String name, String iconPath, String[] selectedStrategy, List<JButton> strategyButtons, boolean isCard) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        JButton button = new JButton();
        if(isCard){
             button = new JButton(loadIcon(iconPath, 100, 100));
        } else {
             button = new JButton(loadIcon(iconPath, 55, 55));
        }
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        addHoverEffect(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        strategyButtons.add(button);

        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));

        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
        panel.add(label);

        JButton finalButton = button;
        button.addActionListener(e -> {
            selectedStrategy[0] = name;
            for (JButton b : strategyButtons) {
                b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                b.setBackground(Color.WHITE);
            }
            // Borde compuesto: negro + azul grueso
            finalButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 120, 215), 3, true),
                    BorderFactory.createLineBorder(Color.BLACK, 2)
            ));
            finalButton.setBackground(Color.WHITE);
        });

        return panel;
    }
}