
package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;



public class CourseItemPanel extends JPanel {
    public CourseItemPanel(String name, String iconPath, Runnable onClick) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setOpaque(false);

        JButton courseBtn = new JButton(name);
        courseBtn.setFocusPainted(false);
        courseBtn.setBackground(new Color(230, 230, 230));
        courseBtn.setIcon(loadIcon(iconPath, 20, 20));
        courseBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        courseBtn.setPreferredSize(new Dimension(150, 35));
        addHoverEffect(courseBtn);

        courseBtn.addActionListener(e -> onClick.run());
        add(courseBtn);

        JButton shareBtn = new JButton(loadIcon("icons/button/share.png", 18, 18));
        shareBtn.setPreferredSize(new Dimension(35, 35));
        shareBtn.setFocusPainted(false);
        addHoverEffect(shareBtn);
        add(shareBtn);

        // TODO: Implement the share functionality to save to Json file of the course
        shareBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save course name");
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