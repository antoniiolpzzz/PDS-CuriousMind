package com.pds.curiousmind.view.home.stats.components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

// This class displays a stats block with a title, icon, and value for user statistics.
public class StatsBlock extends JPanel {

    public StatsBlock(String title, String value, String iconPath) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        titlePanel.setBackground(new Color(230, 230, 230));
        titlePanel.setMaximumSize(new Dimension(200, 30));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setIcon(loadIcon(iconPath, 20, 20));
        titlePanel.add(titleLabel);

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        valueLabel.setOpaque(true);
        valueLabel.setBackground(new Color(0, 200, 80));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLabel.setMaximumSize(new Dimension(200, 30));

        add(titlePanel);
        add(Box.createVerticalStrut(5));
        add(valueLabel);
    }



}
