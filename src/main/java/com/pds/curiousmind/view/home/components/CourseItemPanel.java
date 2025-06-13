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
        JButton courseBtn = new JButton(name);
        courseBtn.setFocusPainted(false);
        courseBtn.setBackground(new Color(230, 230, 230));
        courseBtn.setIcon(loadIcon(iconPath, 20, 20));
        courseBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        courseBtn.setPreferredSize(new Dimension(150, 35));
        addHoverEffect(courseBtn);

        // Handle click event for the course button
        courseBtn.addActionListener(e -> onClick.run());
        add(courseBtn);

        // Create the share button with icon
        StyledButton shareBtn = new StyledButton("", Color.WHITE, Color.BLACK);
        shareBtn.setIcon(loadIcon("icons/button/share.png", 18, 18));
        shareBtn.setPreferredSize(new Dimension(35, 35));
        shareBtn.setFocusPainted(false);
        addHoverEffect(shareBtn);
        add(shareBtn);


        // Handle click event for the share button and save course name to file
        shareBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save course name");
            // TODO: Implement the share functionality to save to Json file of the course
            fileChooser.setSelectedFile(new java.io.File(name + ".txt"));
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileToSave)) {
                    writer.write(name);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
        });
    }

}