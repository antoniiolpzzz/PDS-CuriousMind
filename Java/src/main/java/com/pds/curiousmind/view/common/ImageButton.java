package com.pds.curiousmind.view.common;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.common.GlobalConstants.*;

/**
 * Utility class for creating a styled image button used to select a course strategy.
 * The button is placed inside a JPanel with a label underneath.
 */
public class ImageButton {

    /**
     * Creates a panel containing a button with an image and a label below it.
     * When clicked, the button updates the selected strategy and highlights itself.
     *
     * @param name              The name of the strategy to display and assign when selected.
     * @param iconPath          The resource path to the image icon.
     * @param selectedStrategy  A single-element array used to hold the selected strategy (acts as a mutable reference).
     * @param strategyButtons   A shared list of all strategy buttons (for styling on selection).
     * @param isCard            If true, displays a large card-style icon and larger label.
     * @return A JPanel containing the styled image button and label.
     */
    public static JPanel createImageButton(String name, String iconPath, String[] selectedStrategy, List<JButton> strategyButtons, boolean isCard) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JButton button;
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (isCard) {
            button = new JButton(loadIcon(iconPath, 120, 120));
            label.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
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
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Track this button in the list of strategy buttons
        strategyButtons.add(button);

        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
        panel.add(label);

        JButton finalButton = button;
        button.addActionListener(e -> {
            selectedStrategy[0] = name;

            // Reset style for all buttons
            for (JButton b : strategyButtons) {
                b.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                b.setBackground(Color.WHITE);
            }

            // Highlight selected button
            finalButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            finalButton.setBackground(Color.WHITE);
        });

        return panel;
    }
}
