
package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class JsonChooserWindow extends JDialog {

    private JButton openButton;

    public JsonChooserWindow(JFrame parent) {
        super(parent, "JSON", true);

        setLayout(new BorderLayout());
        setSize(420, 240);
        setResizable(false);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("CREATE COURSE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(Box.createVerticalStrut(5));

        JLabel importTitle = new JLabel("Import Json", SwingConstants.CENTER);
        importTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        importTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(importTitle);

        mainPanel.add(Box.createVerticalStrut(30));

        openButton = new JButton("Open file explorer");
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        openButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                openButton.setText(selectedFile.getName());
            }
        });
        mainPanel.add(openButton);

        mainPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        JButton acceptButton = new JButton("Accept");
        buttonPanel.add(acceptButton);
        acceptButton.addActionListener(e -> {
            if (openButton.getText().equals("Open file explorer") || !openButton.getText().toLowerCase().endsWith(".json")) {
                JOptionPane.showMessageDialog(this, "Please, select JSON file.");
            } else {
                System.out.println("Selected File: " + openButton.getText());
                //TODO: Controller functionality to handle the JSON file and create the course
                dispose();
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
        buttonPanel.add(acceptButton);
        cancelButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(Box.createHorizontalStrut(60));
        acceptButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}