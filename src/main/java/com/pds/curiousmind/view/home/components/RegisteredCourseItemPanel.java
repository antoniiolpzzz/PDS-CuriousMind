package com.pds.curiousmind.view.home.components;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

public class RegisteredCourseItemPanel extends JPanel {

    public RegisteredCourseItemPanel(RegisteredCourse rc, Runnable onClick) { //TODO: recevive a course object
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setOpaque(false);

        Course course = rc.getCourse();

        // Create the main course button with icon and style
        StyledButton courseBtn = new StyledButton(course.getName(), new Color(230, 230, 230), Color.BLACK);
        courseBtn.setIcon(loadIcon(course.getImageURL(), 20, 20));
        courseBtn.setPreferredSize(new Dimension(150, 35));
        courseBtn.setMaximumSize(new Dimension(150, 35));
        courseBtn.addActionListener(e -> onClick.run());
        add(courseBtn);

        //SHARE BUTTON

        //TODO: Implement the functionality to save a course to Json file
        StyledButton shareBtn = new StyledButton("", Color.WHITE, Color.BLACK);
        shareBtn.setIcon(loadIcon("icons/button/share.png", 18, 18));
        shareBtn.setPreferredSize(new Dimension(35, 35));
        shareBtn.setFocusPainted(false);
        addHoverEffect(shareBtn);
        add(shareBtn);

        setBackground(Color.WHITE);

        shareBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save course name");
            //TODO: Implement controller functionality to get the course name and return a Json File
            // File jsonFile = controller.getJsonFromCourse(course);
            fileChooser.setSelectedFile(new java.io.File(course.getName() + ".txt"));
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileToSave)) {
                    writer.write(course.getName());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
                }
            }
        });


    }

}
