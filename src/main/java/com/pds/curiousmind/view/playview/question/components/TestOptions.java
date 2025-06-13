package com.pds.curiousmind.view.playview.question.components;


import javax.swing.*;
import java.awt.*;

public class TestOptions {

    public static JPanel createTestPanel(java.util.List<String> options) {

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(false);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ButtonGroup group = new ButtonGroup();
        for (String option : options) {
            JToggleButton optionButton = new JToggleButton(option);
            optionButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
            optionButton.setOpaque(true);
            optionButton.setBackground(new Color(245, 245, 245));
            optionButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            optionButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
            optionButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
            optionButton.setHorizontalAlignment(SwingConstants.LEFT);
            group.add(optionButton);
            optionsPanel.add(optionButton);
            optionsPanel.add(Box.createVerticalStrut(10));
        }

            return optionsPanel;

    }
}
