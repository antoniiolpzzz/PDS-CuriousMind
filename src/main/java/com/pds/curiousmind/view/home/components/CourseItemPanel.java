
package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

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

        JButton shareBtn = new JButton(loadIcon("icons/share.png", 18, 18));
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

    private void addHoverEffect(JButton button) {
        Color original = button.getBackground();
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(original.brighter());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(original);
            }
        });
    }

    private ImageIcon loadIcon(String path, int width, int height) {
        URL url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            System.err.println("Icon not found: " + path);
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(url);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}