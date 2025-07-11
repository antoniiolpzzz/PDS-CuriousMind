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
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

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

        List<RegisteredContentBlock> sortedBlocks = new ArrayList<RegisteredContentBlock>(contentBlocks);

        sortedBlocks.sort(Comparator.comparingInt(block ->
                switch (block.getDifficulty().toString()) {
                    case "EASY" -> 0;
                    case "MEDIUM" -> 1;
                    case "HARD" -> 2;
                    default -> 3;
                }
        ));

        for (RegisteredContentBlock block : sortedBlocks) {
            RoundedLabel label = new RoundedLabel(block.getName());

            String iconPath;
            switch (block.getDifficulty().toString()) {
                case "EASY":
                    iconPath = ICON_STAR1;
                    label.setIcon(loadIcon(iconPath, 20, 20));
                    break;
                case "MEDIUM":
                    iconPath = ICON_STAR2;
                    label.setIcon(loadIcon(iconPath, 40, 20));
                    break;
                case "HARD":
                    iconPath = ICON_STAR3;
                    label.setIcon(loadIcon(iconPath, 60, 20));
                    break;
                default:
                    iconPath = ICON_MORE;
                    label.setIcon(loadIcon(iconPath, 20, 20));
                    break;
            }


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
                        Question question = controller.initializeGameManager(course, block);
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