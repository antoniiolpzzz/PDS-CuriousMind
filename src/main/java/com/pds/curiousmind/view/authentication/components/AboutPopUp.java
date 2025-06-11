package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;

public class AboutPopUp extends JDialog {
    public AboutPopUp(JFrame parent) {
        super(parent, "About Us", true);
        setUndecorated(false);
        setSize(450, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        RoundedPanel panel = new RoundedPanel(40);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        add(panel, BorderLayout.CENTER);

        JLabel title = new JLabel("ABOUT US", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        JPanel photosPanel = new JPanel(new GridLayout(1, 3, 15, 10));
        photosPanel.setOpaque(false);
        panel.add(photosPanel);

        addPerson(photosPanel, "Javier", "icons/javier.png");
        addPerson(photosPanel, "Antonio", "icons/antonio.png");
        addPerson(photosPanel, "Mercedes", "icons/mercedes.png");
    }

    private void addPerson(JPanel panel, String name, String iconPath) {
        JPanel personPanel = new JPanel();
        personPanel.setOpaque(false);
        personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.Y_AXIS));
        personPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel photo = new JLabel(loadIcon(iconPath, 70, 70));
        photo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        photo.setAlignmentX(Component.CENTER_ALIGNMENT);
        personPanel.add(photo);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        personPanel.add(nameLabel);

        panel.add(personPanel);
    }

    private ImageIcon loadIcon(String path, int width, int height) {
        var url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            System.err.println("No se encontró el icono: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
