package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;

public class AboutPopUp extends JDialog {
    public AboutPopUp(JFrame parent) {
        super(parent, "About Us", true);
        setUndecorated(false);
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // Fondo gris para toda la ventana

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del contenido
        JLabel title = new JLabel("Who are CuriousMinds?", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        JPanel photosPanel = new JPanel(new GridLayout(1, 3, 15, 10));
        photosPanel.setOpaque(false);
        panel.add(photosPanel);

        addPerson(photosPanel, "Javier", "icons/about/javier.png");
        addPerson(photosPanel, "Antonio", "icons/about/antonio.png");
        addPerson(photosPanel, "Mercedes", "icons/about/mercedes.png");

        panel.add(Box.createVerticalStrut(10)); // Espacio antes del separador
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setForeground(Color.BLACK);
        panel.add(separator1);

        JLabel description = new JLabel("Computer Engineering students from University of Murcia", SwingConstants.CENTER);
        description.setFont(new Font("SansSerif", Font.PLAIN, 16));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Menos espacio arriba
        panel.add(description);
    }

    private void addPerson(JPanel panel, String name, String iconPath) {
        JPanel personPanel = new JPanel();
        personPanel.setOpaque(false);
        personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.Y_AXIS));
        personPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel photo = new JLabel(loadIcon(iconPath, 130, 130));
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
