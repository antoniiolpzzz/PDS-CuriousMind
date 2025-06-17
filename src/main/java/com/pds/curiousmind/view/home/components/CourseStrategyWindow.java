package com.pds.curiousmind.view.home.components;


import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.common.ImageButton.createImageButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class CourseStrategyWindow extends JDialog {

    private static final Controller controller = Controller.INSTANCE;


    public CourseStrategyWindow(JFrame parent, Course course, User user) {
        super(parent, "Select Strategy", true);

        // Set up window properties and focus behavior
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        setLayout(new BorderLayout());
        setSize(POPUP_WIDTH, POPUP_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(parent);

        // Main panel for course info and strategy selection
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        String courseName = course.getName ();
        JLabel title = new JLabel(courseName);
        title.setFont(new Font(FONT_NAME, Font.BOLD, 22));
        title.setIcon(loadIcon(course.getImageURL(), 28, 28));
        titlePanel.add(title);
        mainPanel.add(titlePanel);

        mainPanel.add(Box.createVerticalStrut(3));

        // Display course description
        String description = course.getDescription();
        JLabel descriptionArea = new JLabel(description);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setFont(new Font(FONT_NAME, Font.PLAIN, 13));
        mainPanel.add(descriptionArea);

        mainPanel.add(Box.createVerticalStrut(10));

        // Panel for strategy selection buttons
        JPanel strategyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        JLabel stratLabel = new JLabel("Select strategy:");
        stratLabel.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        stratLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(stratLabel);
        strategyPanel.setOpaque(false);
        // Track the selected strategy and buttons
        final String[] selectedStrategy = {null};
        final java.util.List<JButton> strategyButtons = new java.util.ArrayList<>();
        strategyPanel.add(createImageButton(StrategyType.SEQUENTIAL.toString(), ICON_STRATEGY_SEQUENTIAL, selectedStrategy, strategyButtons,false));
        strategyPanel.add(createImageButton(StrategyType.SHUFFLED.toString(), ICON_STRATEGY_RANDOM, selectedStrategy, strategyButtons,false));
        strategyPanel.add(createImageButton(StrategyType.SPACED_REPETITION.toString(), ICON_STRATEGY_REPETITON, selectedStrategy, strategyButtons,false));
        mainPanel.add(strategyPanel);

        // Bottom panel for Accept/Cancel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        StyledButton acceptButton = new StyledButton(ACCEPT_LABEL, Color.WHITE, Color.BLACK);
        StyledButton cancelButton = new StyledButton(CANCEL_LABEL, Color.WHITE, Color.BLACK);

        acceptButton.addActionListener(e -> {
            if (selectedStrategy[0] == null) {
                JOptionPane.showMessageDialog(null, "Please select a strategy.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));

            } else {
                dispose();
                controller.createRegisteredCourse(course, StrategyType.valueOf(selectedStrategy[0]));
                JOptionPane.showMessageDialog(null, "Course registered with strategy: " + selectedStrategy[0], "Successful", JOptionPane.INFORMATION_MESSAGE, loadIcon("icons/pet/feliz.png", 60, 60));
                dispose();
                new HomeWindow(user);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(acceptButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}

