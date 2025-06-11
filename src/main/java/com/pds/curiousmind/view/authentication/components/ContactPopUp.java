package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;

public class ContactPopUp extends JDialog {
    public ContactPopUp(JFrame parent) {
        super(parent, "Contact", true);
        setUndecorated(false);
        setSize(400, 220);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        RoundedPanel panel = new RoundedPanel(40);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.LIGHT_GRAY);
        add(panel, BorderLayout.CENTER);

        JLabel title = new JLabel("CONTACT", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        JLabel phone = new JLabel("Phone Number: 645729425");
        phone.setFont(new Font("SansSerif", Font.BOLD, 16));
        phone.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(phone);

        JLabel email = new JLabel("Email: CouriousMind@gmail.com");
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(email);
    }
}
