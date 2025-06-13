package com.pds.curiousmind.view.home.course.components;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContentBlockRow {
    // Static method to allow usage from other classes (like LoadIcon)

    public static JPanel createContentColumnSection(java.util.List<String> contentNames) {
        Set<String> completedBlocks = new HashSet<>(Arrays.asList(
                "Basic words", "Basic sentences", "Introduce yourself"
        )); // <--- TODO: check if the content block is completed

        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setOpaque(false);

        for (String name : contentNames) {
            JLabel label = new JLabel(name);
            label.setFont(new Font("SansSerif", Font.PLAIN, 18));
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setOpaque(true);

            boolean isCompleted = completedBlocks.contains(name);
            if (isCompleted) {
                label.setBackground(new Color(214, 245, 214));
            } else {
                label.setBackground(new Color(245, 245, 245));
            }

            label.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
            label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (!isCompleted) {
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    Color originalBg = label.getBackground();
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        label.setBackground(new Color(230, 250, 255));
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        label.setBackground(originalBg);
                    }
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        // TODO: abrir el bloque o marcar como completado
                    }
                });
            }

            column.add(label);
            column.add(Box.createVerticalStrut(12));
        }

        return column;
    }
}
