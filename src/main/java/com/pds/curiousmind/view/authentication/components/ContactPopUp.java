package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;

public class ContactPopUp extends JDialog {
    public ContactPopUp(JFrame parent) {
        super(parent, "Contact", true);
        setUndecorated(false);
        setSize(500, 220);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor del contenido

        JLabel title = new JLabel("How to contact CuriousMinds", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));


        JLabel phone = new JLabel("Phone Number: 645729425",
                resizeIcon("icons/contact/phone.png", 32, 32), JLabel.LEFT);
        phone.setFont(new Font("SansSerif", Font.BOLD, 16));
        phone.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(phone);

        JLabel email = new JLabel("Email: CouriousMind@gmail.com",
                resizeIcon("icons/contact/mail.png", 32, 32), JLabel.LEFT);
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(email);
    }

    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
