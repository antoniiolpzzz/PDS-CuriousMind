package com.pds.curiousmind.view.playview.question.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.stats.UserWindow;
import com.pds.curiousmind.view.home.stats.components.RoundedProgressBar;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;

public class CreateHeader {


    public static JPanel createHeader(RegisteredCourse course, String indication, String statement, String blockName) {


        Controller controller = Controller.INSTANCE;


        JPanel rightPanel = new JPanel();
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel homeTitle = new JLabel(course.getName(), SwingConstants.LEFT);
        homeTitle.setFont(new Font(FONT_NAME, Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setIcon(loadIcon(course.getImageURL(), 24, 24));
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(10));


        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userPanel.setOpaque(false);


        // Section title for the content block
        rightPanel.add(sectionTitle(blockName));
        rightPanel.add(Box.createVerticalStrut(10));


        //Progress bar related with de number of questions answered

        JProgressBar progressBar = new RoundedProgressBar(controller.getBlockProgress());
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
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


        return rightPanel;
    }

}