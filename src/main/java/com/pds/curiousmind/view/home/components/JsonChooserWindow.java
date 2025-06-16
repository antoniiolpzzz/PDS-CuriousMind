package com.pds.curiousmind.view.home.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;


public class JsonChooserWindow extends JDialog {

    private final StyledButton openButton;
    private File selectedJsonFile;
    private static final Controller controller = Controller.INSTANCE;



    public JsonChooserWindow(JFrame parent, User user) {
        super(parent, "Create Course", true);

        setLayout(new BorderLayout());
        setSize(POPUP_WIDTH, POPUP_HEIGHT);
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
        title.setFont(new Font(FONT_NAME, Font.BOLD, 22));
        String courseIconPath = ICON_MORE; // Path to the course icon
        title.setIcon(loadIcon(courseIconPath, 28, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(Box.createVerticalStrut(5));

        JLabel importTitle = new JLabel("Import Json", SwingConstants.CENTER);
        importTitle.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        importTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(importTitle);

        mainPanel.add(Box.createVerticalStrut(30));

        openButton = new StyledButton("Open file explorer", Color.BLACK, Color.WHITE);
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
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
        StyledButton acceptButton = new StyledButton(ACCEPT_LABEL, Color.WHITE, Color.BLACK);
        acceptButton.addActionListener(e -> {
            if (selectedJsonFile == null || !selectedJsonFile.getName().toLowerCase().endsWith(".json")) {
                JOptionPane.showMessageDialog(null, "Please select a valid JSON file. ", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));

            } else {
                try {
                    if (controller.createCourseFromJson(selectedJsonFile) != null) {
                        JOptionPane.showMessageDialog(null, "¡Curso creado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE, loadIcon(ICON_COMPLETADO, 60, 60));
                        dispose();
                        new HomeWindow(user);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al crear el curso.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
                        dispose();
                        new HomeWindow(user);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error creating course: "+ ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));

                    ex.printStackTrace();
                }
            }
        });
        StyledButton cancelButton = new StyledButton(CANCEL_LABEL, Color.WHITE, Color.BLACK);
        cancelButton.addActionListener(e -> {
            dispose();
            new HomeWindow(user);
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
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