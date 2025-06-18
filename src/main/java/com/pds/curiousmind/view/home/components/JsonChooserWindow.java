package com.pds.curiousmind.view.home.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

/**
 * This class represents a dialog window that allows users to import a course
 * by selecting a JSON or YAML file from the file system.
 * <p>
 * It validates the file type, invokes the controller to create the course,
 * and displays appropriate success or error messages.
 */
public class JsonChooserWindow extends JDialog {

    private final StyledButton openButton;
    private File selectedJsonFile;
    private static final Controller controller = Controller.INSTANCE;

    /**
     * Constructs a new JsonChooserWindow dialog.
     *
     * @param parent The parent frame to center this dialog on.
     * @param user   The current user, used for navigation and logic.
     */
    public JsonChooserWindow(JFrame parent, User user) {
        super(parent, "Create Course", true);

        setLayout(new BorderLayout());
        setSize(POPUP_WIDTH, POPUP_HEIGHT);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(parent);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(Box.createVerticalStrut(2));
        mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        mainPanel.add(Box.createVerticalStrut(20));

        add(mainPanel, BorderLayout.CENTER);

        // Title
        JLabel title = new JLabel("CREATE COURSE", SwingConstants.CENTER);
        title.setFont(new Font(FONT_NAME, Font.BOLD, 22));
        title.setIcon(loadIcon(ICON_MORE, 28, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(Box.createVerticalStrut(5));

        // Subtitle
        JLabel importTitle = new JLabel("Import Json", SwingConstants.CENTER);
        importTitle.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        importTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(importTitle);

        mainPanel.add(Box.createVerticalStrut(30));

        // Open file button
        openButton = new StyledButton("Open file explorer", Color.BLACK, Color.WHITE);
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));

        // File chooser action
        openButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();

            // Crear y aplicar el filtro de archivos JSON
            FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("JSON files (*.json)", "json");
            fileChooser.setFileFilter(jsonFilter);

            // Opcional: desactivar todos los archivos (para que no puedan elegir "All Files")
            fileChooser.setAcceptAllFileFilterUsed(false);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedJsonFile = fileChooser.getSelectedFile();
                openButton.setText(selectedJsonFile.getName());
            }
        });
        mainPanel.add(openButton);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // Bottom panel with action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        // Accept button
        StyledButton acceptButton = new StyledButton(ACCEPT_LABEL, Color.WHITE, Color.BLACK);
        acceptButton.addActionListener(e -> {
            if (selectedJsonFile == null ||
                    (!selectedJsonFile.getName().toLowerCase().endsWith(".json") &&
                            !selectedJsonFile.getName().toLowerCase().endsWith(".yaml"))) {
                JOptionPane.showMessageDialog(null,
                        "Please select a valid JSON or YAML file.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE,
                        loadIcon(ICON_ANGRY, 60, 60));
            } else {
                try {
                    if (controller.createCourseFromJson(selectedJsonFile) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Course created successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE,
                                loadIcon(ICON_COMPLETE, 60, 60));
                        dispose();
                        new HomeWindow(user);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error creating course.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE,
                                loadIcon(ICON_ANGRY, 60, 60));
                        dispose();
                        new HomeWindow(user);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error creating course: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE,
                            loadIcon(ICON_ANGRY, 60, 60));
                    ex.printStackTrace();
                }
            }
        });

        // Cancel button
        StyledButton cancelButton = new StyledButton(CANCEL_LABEL, Color.WHITE, Color.BLACK);
        cancelButton.addActionListener(e -> {
            dispose();
            new HomeWindow(user);
        });

        // Add buttons to the panel
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(acceptButton);

        cancelButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        acceptButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonPanel.setBackground(Color.WHITE);

        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
