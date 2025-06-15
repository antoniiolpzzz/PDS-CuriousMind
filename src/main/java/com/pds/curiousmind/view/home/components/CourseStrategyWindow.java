package com.pds.curiousmind.view.home.components;


import com.pds.curiousmind.view.common.StyledButton;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.common.ImageButton.createImageButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class CourseStrategyWindow extends JDialog {

    // Static map for course descriptions
    //TODO: Receive real courses with their details
    private static final Map<String, String> courseDescriptions = new HashMap<>() {{
        put("German", "Learn basic to advanced German language skills.");
        put("Modern History", "Explore major events from the 19th and 20th centuries.");
        put("Java Script", "Master JavaScript for dynamic web development.");
        put("Languages", "Study different languages around the world.");
        put("Sciences", "Understand core scientific concepts and discoveries.");
        put("Grammar", "Improve your grammar and writing skills.");
        put("Music", "Explore music theory, instruments, and history.");
        put("Programming", "Learn to code in different programming languages.");
        put("History", "Dive into historical events and civilizations.");
    }};

    public CourseStrategyWindow(JFrame parent, String courseName, String courseIconPath) { //TODO: Receive a course object
        super(parent, "Select Strategy", true);

        // Set up window properties and focus behavior
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        setLayout(new BorderLayout());
        setSize(480, 320);
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
        //TODO: courseName = course.getName ();
        JLabel title = new JLabel(courseName);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setIcon(loadIcon(courseIconPath, 28, 28));
        titlePanel.add(title);
        mainPanel.add(titlePanel);

        mainPanel.add(Box.createVerticalStrut(3));

        // Display course description
        //TODO: description = course.getDescription();
        String desc = courseDescriptions.getOrDefault(courseName, "Course description not available.");
        JLabel descriptionArea = new JLabel(desc);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        mainPanel.add(descriptionArea);

        mainPanel.add(Box.createVerticalStrut(10));

        // Panel for strategy selection buttons
        JPanel strategyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        JLabel stratLabel = new JLabel("Strategy");
        stratLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        stratLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(stratLabel);
        strategyPanel.setOpaque(false);
        // Track the selected strategy and buttons
        final String[] selectedStrategy = {null};
        final java.util.List<JButton> strategyButtons = new java.util.ArrayList<>();
        strategyPanel.add(createImageButton("Sequential", "icons/strategy/sequential.png", selectedStrategy, strategyButtons,false));
        strategyPanel.add(createImageButton("Random", "icons/strategy/random.png", selectedStrategy, strategyButtons,false));
        strategyPanel.add(createImageButton("Sp. Repetition", "icons/strategy/repetition.png", selectedStrategy, strategyButtons,false));
        mainPanel.add(strategyPanel);

        // Bottom panel for Accept/Cancel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        StyledButton acceptButton = new StyledButton("Accept", Color.WHITE, Color.BLACK);
        StyledButton cancelButton = new StyledButton("Cancel", Color.WHITE, Color.BLACK);

        acceptButton.addActionListener(e -> {
            if (selectedStrategy[0] == null) {
                JOptionPane.showMessageDialog(null, "Please select a strategy.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));

            } else {
                dispose();
                //TODO: Controller functionality to handle the selected strategy and create registerCourse
                // controller.createRegisterCourse(course, selectedStrategy[0]);
                JOptionPane.showMessageDialog(null, "Course registered with strategy: " + selectedStrategy[0], "Successful", JOptionPane.INFORMATION_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(250));
        buttonPanel.add(acceptButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}

