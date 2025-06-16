package com.pds.curiousmind.view.home.course.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.common.RoundedLabel;
import com.pds.curiousmind.view.playview.question.components.QuestionStructure;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for creating a column of content block rows for a course.
 * Each row represents a RegisteredContentBlock with interactive behavior.
 */
public class ContentBlockRow {

    private static final Controller controller = Controller.INSTANCE;

    /**
     * Creates a vertical column panel containing all content blocks for a course.
     * Each block is displayed as a RoundedLabel, with different styles for completed and pending blocks.
     * Pending blocks are clickable and open the question view.
     *
     * @param parentFrame The parent JFrame to be disposed when a block is clicked.
     * @param course The registered course containing the content blocks.
     * @param contentBlocks The list of RegisteredContentBlock to display.
     * @return JPanel containing the column of content block rows.
     */
    public static JPanel createContentColumnSection(JFrame parentFrame, RegisteredCourse course, java.util.List<RegisteredContentBlock> contentBlocks) {

        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setOpaque(false);

        for ( RegisteredContentBlock block : contentBlocks ) {
            // Create a label for each content block
            RoundedLabel label = new RoundedLabel(block.getName());

            if (block.isCompleted()) {
                // Style for completed blocks
                label.setLabelBackground(new Color(214, 245, 214));
                label.setLabelBorderColor(new Color(180, 230, 180));
            } else {
                // Style and interactivity for pending blocks
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

                    /**
                     * Handles click event to open the question view for the selected block.
                     */
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        // TODO: Use controller to initialize the game manager for the block
                        Question question = block.getQuestions().get(3);
                        new QuestionStructure(course, question, block.getName(), block.getDifficulty());
                        parentFrame.dispose();
                    }
                });
            }

            column.add(label);
            column.add(Box.createVerticalStrut(12));
        }

        return column;
    }
}