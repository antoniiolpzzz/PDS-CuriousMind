package com.pds.curiousmind.view.playview.question.components;

import com.pds.curiousmind.view.home.stats.components.RoundedProgressBar;

import javax.swing.*;
        import java.awt.*;
        import java.util.List;

import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;

public class CreateHeader {

    public static JPanel createHeader(String title, String iconPath, String indication, String statement) {

        JPanel rightPanel = new JPanel();
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel homeTitle = new JLabel(title, SwingConstants.LEFT);
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setIcon(loadIcon(iconPath, 24, 24));
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(10));


        // Section title for the content block
        //TODO: Reucuperate the name of the content block from the content block object
        rightPanel.add(sectionTitle("--Name of the content block--"));
        rightPanel.add(Box.createVerticalStrut(10));


        //Progress bar related with de number of questions answered

        JProgressBar progressBar = new RoundedProgressBar(getProgressForCourse());
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        progressBar.setForeground(new Color(76, 175, 80)); // Green color
        progressBar.setBackground(new Color(245, 245, 245));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(progressBar);
        rightPanel.add(Box.createVerticalStrut(5));


        // Statement label
        JLabel statementLabel = new JLabel("<html><div style='text-align: center; width: 100%'>" + indication + "</div></html>");
        statementLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        statementLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statementLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        rightPanel.add(statementLabel);
        rightPanel.add(Box.createVerticalStrut(5));

        // Statement label for the question
        JLabel questionLabel = new JLabel("<html><div style='text-align: center; width: 100%'>" + statement + "</div></html>");
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        rightPanel.add(questionLabel);
        rightPanel.add(Box.createVerticalStrut(5));

        return rightPanel;
    }

    private static int getProgressForCourse() {
        //TODO: Recuperate the progress of the content block related with the number of questions answered
        return 30; // Example value, this should be dynamic based on actual progress

    }
}