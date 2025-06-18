package com.pds.curiousmind.view.playview.question.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.home.stats.components.RoundedProgressBar;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;

/**
 * Utility class for creating the common header section in the question view.
 * <p>
 * This header displays:
 * - The course name and image.
 * - The remaining lives as icons.
 * - The name of the current content block.
 * - The progress bar indicating progress through the block.
 * - The question's instruction and statement.
 */
public class CreateHeader {

    /**
     * Creates and returns a JPanel containing the header section used
     * in question views.
     *
     * @param course    The course the user is currently playing.
     * @param indication The instruction text for the current question.
     * @param statement  The question's main statement.
     * @param blockName  The name of the current content block.
     * @return A JPanel containing the structured header.
     */
    public static JPanel createHeader(RegisteredCourse course, String indication, String statement, String blockName) {
        Controller controller = Controller.INSTANCE;

        // Base panel setup
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        // Header with course name and life icons
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel homeTitle = new JLabel(course.getName(), SwingConstants.LEFT);
        homeTitle.setFont(new Font(FONT_NAME, Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setIcon(loadIcon(controller.downloadImageFromUrl(course.getImageURL()), 24, 24));
        headerPanel.add(homeTitle, BorderLayout.WEST);

        // Lives display
        int lifes = controller.getLivesLeft();
        JLabel iconLabel = null;
        switch (lifes) {
            case 1 -> iconLabel = new JLabel(loadIcon(ICON_LIFE1, 24, 24));
            case 2 -> iconLabel = new JLabel(loadIcon(ICON_LIFE2, 34, 24));
            case 3 -> iconLabel = new JLabel(loadIcon(ICON_LIFE3, 50, 24));
            case 4 -> iconLabel = new JLabel(loadIcon(ICON_LIFE4, 60, 24));
            case 5 -> iconLabel = new JLabel(loadIcon(ICON_LIFE5, 70, 24));
        }
        if (iconLabel != null) {
            headerPanel.add(iconLabel, BorderLayout.EAST);
        }

        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(10));

        // Block name section
        rightPanel.add(sectionTitle(blockName));
        rightPanel.add(Box.createVerticalStrut(10));

        // Progress bar for question progress within the block
        JProgressBar progressBar = new RoundedProgressBar(controller.getBlockProgress());
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        progressBar.setForeground(new Color(76, 175, 80)); // Green
        progressBar.setBackground(new Color(245, 245, 245));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(progressBar);
        rightPanel.add(Box.createVerticalStrut(5));

        // Instruction/indication label
        JLabel statementLabel = new JLabel("<html><div style='text-align: center; width: 100%'>" + indication + "</div></html>");
        statementLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        statementLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statementLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        rightPanel.add(statementLabel);
        rightPanel.add(Box.createVerticalStrut(5));

        // Main question statement label
        JLabel questionLabel = new JLabel("<html><div style='text-align: center; width: 100%'>" + statement + "</div></html>");
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        rightPanel.add(questionLabel);

        return rightPanel;
    }
}
