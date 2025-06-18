package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.view.common.StyledButton;
import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;

/**
 * CourseItemPanel represents a UI component for displaying a course item.
 * It shows the course name as a styled button with an icon,
 * and includes a share button to export the course data as a JSON file.
 */
public class CourseItemPanel extends JPanel {

    /** Singleton controller instance */
    private static final Controller controller = Controller.INSTANCE;

    /**
     * Constructs a CourseItemPanel with a course button and a share button.
     * The course button triggers the provided onClick runnable when clicked.
     * The share button opens a file chooser to save the course data as a JSON file.
     *
     * @param course the Course object to display
     * @param onClick the action to perform when the course button is clicked
     */
    public CourseItemPanel(Course course, Runnable onClick) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setOpaque(false);

        // Create the main course button with icon and style
        StyledButton courseBtn = new StyledButton(course.getName(), new Color(230, 230, 230), Color.BLACK);
        courseBtn.setIcon(loadIcon(course.getImageURL(), 20, 20));
        courseBtn.setPreferredSize(new Dimension(150, 35));
        courseBtn.setMaximumSize(new Dimension(150, 35));
        courseBtn.addActionListener(e -> onClick.run());
        add(courseBtn);

        // SHARE BUTTON
        StyledButton shareBtn = new StyledButton("", Color.WHITE, Color.BLACK);
        shareBtn.setIcon(loadIcon(ICON_SHARE, 18, 18));
        shareBtn.setPreferredSize(new Dimension(35, 35));
        shareBtn.setFocusPainted(false);
        addHoverEffect(shareBtn);
        add(shareBtn);

        setBackground(Color.WHITE);

        // Action listener for sharing course data as a JSON file
        shareBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save file as JSON");

            // Propose a default filename with .json extension
            fileChooser.setSelectedFile(new java.io.File(course.getName() + ".json"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File destinationFile = fileChooser.getSelectedFile();

                // Ensure the file has a .json extension
                if (!destinationFile.getName().toLowerCase().endsWith(".json")) {
                    destinationFile = new java.io.File(destinationFile.getAbsolutePath() + ".json");
                }

                // Get the JSON file from the controller
                File jsonFile = controller.getJsonFromCourse(course);

                try {
                    // Copy the JSON file to the user-selected destination
                    Files.copy(jsonFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error saving the file: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE,
                            loadIcon(ICON_ANGRY, 60, 60)
                    );
                }
            }
        });

    }

}
