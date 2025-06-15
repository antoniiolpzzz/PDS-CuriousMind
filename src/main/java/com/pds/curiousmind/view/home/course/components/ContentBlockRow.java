package com.pds.curiousmind.view.home.course.components;

import com.pds.curiousmind.view.common.RoundedLabel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContentBlockRow {

    // TODO: Receive a RegisteredContentBlock list contentBlocks
    public static JPanel createContentColumnSection(java.util.List<String> contentNames) {
        Set<String> completedBlocks = new HashSet<>(Arrays.asList(
                "Basic words", "Basic sentences", "Introduce yourself"
        ));

        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setOpaque(false);

        // TODO: for ( ContentBlock block : contentBlocks ) {
        for (String name : contentNames) {
            boolean isCompleted = completedBlocks.contains(name);
            RoundedLabel label = new RoundedLabel(name);

            //TODO: block.isCompleted();
            if (isCompleted) {
                label.setLabelBackground(new Color(214, 245, 214));
                label.setLabelBorderColor(new Color(180, 230, 180));
            } else {
                label.setLabelBackground(new Color(245, 245, 245));
                label.setLabelBorderColor(new Color(200, 200, 200));
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                Color original = label.getLabelBackground();

                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        label.setLabelBackground(new Color(230, 250, 255));
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        label.setLabelBackground(original);
                    }

                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        //TODO: new QuestionStructure(block);
                        //dispose();
                    }
                });
            }

            column.add(label);
            column.add(Box.createVerticalStrut(12));
        }

        return column;
    }
}
