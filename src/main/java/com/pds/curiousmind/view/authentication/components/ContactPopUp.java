package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.GlobalConstants.POPUP_HEIGHT;
import static com.pds.curiousmind.view.common.GlobalConstants.POPUP_WIDTH;

/**
 * ContactPopUp displays contact information for CuriousMind.
 * It shows a dialog with phone and email details.
 */
public class ContactPopUp extends JDialog {

    /**
     * Constructs the ContactPopUp dialog.
     * Sets up the layout and displays contact details.
     *
     * @param parent The parent JFrame for modal positioning.
     */
    public ContactPopUp(JFrame parent) {
        super(parent, "Contact", true);
        setUndecorated(false);
        setSize(POPUP_WIDTH, POPUP_HEIGHT); // Default width and height
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("How to contact CuriousMind", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        JLabel phone = new JLabel("Phone Number: 645729425",
                resizeIcon("icons/contact/phone.png", 32, 32), JLabel.LEFT);
        phone.setFont(new Font("SansSerif", Font.BOLD, 16));
        phone.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(phone);

        JLabel email = new JLabel("Email: CuriousMind@gmail.com",
                resizeIcon("icons/contact/mail.png", 32, 32), JLabel.LEFT);
        email.setFont(new Font("SansSerif", Font.BOLD, 16));
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(email);
    }

    /**
     * Loads and scales an image icon from the given path.
     *
     * @param path The resource path to the image.
     * @param width The desired width.
     * @param height The desired height.
     * @return The scaled ImageIcon.
     */
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}