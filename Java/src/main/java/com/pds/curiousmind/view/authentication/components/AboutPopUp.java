package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.GlobalConstants.*;

/**
 * AboutPopUp displays information about the creators of CuriousMind.
 * It shows a dialog with team member photos and a brief description.
 */
public class AboutPopUp extends JDialog {

    /**
     * Constructs the AboutPopUp dialog.
     * Sets up the layout, team member photos, and description.
     *
     * @param parent The parent JFrame for modal positioning.
     */
    public AboutPopUp(JFrame parent) {
        super(parent, "About Us", true);
        setUndecorated(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(POPUP_WIDTH, POPUP_HEIGHT);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("Who is CuriousMind?", SwingConstants.CENTER);
        title.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        JPanel photosPanel = new JPanel(new GridLayout(1, 3, 15, 10));
        photosPanel.setOpaque(false);
        panel.add(photosPanel);

        addPerson(photosPanel, "Javier", "icons/about/javier.png");
        addPerson(photosPanel, "Antonio", "icons/about/antonio.png");
        addPerson(photosPanel, "Mercedes", "icons/about/mercedes.png");

        panel.add(Box.createVerticalStrut(10));
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setForeground(Color.BLACK);
        panel.add(separator1);

        JLabel description = new JLabel("Computer Engineering students from University of Murcia", SwingConstants.CENTER);
        description.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        panel.add(description);
    }

    /**
     * Adds a team member's photo and name to the given panel.
     *
     * @param panel The panel to add the person to.
     * @param name The name of the person.
     * @param iconPath The path to the person's photo.
     */
    private void addPerson(JPanel panel, String name, String iconPath) {
        JPanel personPanel = new JPanel();
        personPanel.setOpaque(false);
        personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.Y_AXIS));
        personPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel photo = new JLabel(loadIcon(iconPath, 130, 130));
        photo.setAlignmentX(Component.CENTER_ALIGNMENT);
        personPanel.add(photo);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font(FONT_NAME, Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        personPanel.add(nameLabel);

        panel.add(personPanel);
    }

    /**
     * Loads and scales an image icon from the given path.
     *
     * @param path The resource path to the image.
     * @param width The desired width.
     * @param height The desired height.
     * @return The scaled ImageIcon, or null if not found.
     */
    private ImageIcon loadIcon(String path, int width, int height) {
        var url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            System.err.println("Icon not found: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}