package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import com.pds.curiousmind.view.common.StyledButton;
import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;

public class CourseItemPanel extends JPanel {
    public CourseItemPanel(String name, String iconPath, Runnable onClick) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setOpaque(false);

        // Create the main course button with icon and style
        StyledButton courseBtn = new StyledButton(name, new Color(230, 230, 230), Color.BLACK);
        courseBtn.setIcon(loadIcon(iconPath, 20, 20));
        courseBtn.setPreferredSize(new Dimension(150, 35));
        courseBtn.setMaximumSize(new Dimension(150, 35));
        courseBtn.addActionListener(e -> onClick.run());
        add(courseBtn);

        //SHARE BUTTON

        //TODO: Implement the functionality to save a couse to Json file
        StyledButton shareBtn = new StyledButton("", Color.WHITE, Color.BLACK);
        shareBtn.setIcon(loadIcon("icons/button/share.png", 18, 18));
        shareBtn.setPreferredSize(new Dimension(35, 35));
        shareBtn.setFocusPainted(false);
        addHoverEffect(shareBtn);
        add(shareBtn);

        shareBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save course name");
            // TODO: Implement controller functionality to get the course name and return a Json File
            // File jsonFile = controller.getJsonFromCourse(name);
            fileChooser.setSelectedFile(new java.io.File(name + ".txt"));
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileToSave)) {
                    writer.write(name);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
                }
            }
        });
    }

}