package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;

public class SectionTitle {

    public static JPanel sectionTitle(String text) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.BLACK);
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(label);
        return panel;
    }
}