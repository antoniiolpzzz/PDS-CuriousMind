package com.pds.curiousmind.view.home.components;

import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;


public class JsonChooserWindow extends JDialog {

    private final StyledButton openButton;
    private File selectedJsonFile;


    public JsonChooserWindow(JFrame parent) {
        super(parent, "Create Course", true);

        setLayout(new BorderLayout());
        setSize(480, 320);
        setResizable(false);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(Box.createVerticalStrut(2));
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.BLACK);
        mainPanel.add(separator);
        mainPanel.add(Box.createVerticalStrut(20));



        add(mainPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("CREATE COURSE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        String courseIconPath = "icons/button/more.png"; // Path to the course icon
        title.setIcon(loadIcon(courseIconPath, 28, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(Box.createVerticalStrut(5));

        JLabel importTitle = new JLabel("Import Json", SwingConstants.CENTER);
        importTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        importTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(importTitle);

        mainPanel.add(Box.createVerticalStrut(30));

        openButton = new StyledButton("Open file explorer", Color.BLACK, Color.WHITE);
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        // Button to open file chooser dialog
        openButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedJsonFile = fileChooser.getSelectedFile();
                openButton.setText(selectedJsonFile.getName());
            }
        });
        mainPanel.add(openButton);

        mainPanel.add(Box.createVerticalStrut(20));
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setForeground(Color.BLACK);
        mainPanel.add(separator1);

        // Panel for Accept button aligned to the right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        StyledButton acceptButton = new StyledButton("Accept", Color.WHITE, Color.BLACK);
        acceptButton.addActionListener(e -> {
            if (selectedJsonFile == null || !selectedJsonFile.getName().toLowerCase().endsWith(".json")) {
                JOptionPane.showMessageDialog(this, "Please, select a valid JSON file.");
            } else {
                try {
                    //TODO: controller.createCourseFromJson(selectedJsonFile);
                    JOptionPane.showMessageDialog(this, "Course created successfully!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error creating course: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        StyledButton cancelButton = new StyledButton("Cancel", Color.WHITE, Color.BLACK);
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(250));
        buttonPanel.add(acceptButton);
        cancelButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(Box.createHorizontalStrut(60));
        acceptButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}