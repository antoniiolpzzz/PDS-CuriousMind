// Ventana emergente para ver curso y seleccionar estrategia
package com.pds.curiousmind.view.home.components;


import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CourseStrategyWindow extends JDialog {
    private final String courseName;
    private final String courseIconPath;

    private static final Map<String, String> courseDescriptions = new HashMap<>() {{
        put("German", "Learn basic to advanced German language skills.");
        put("Modern History", "Explore major events from the 19th and 20th centuries.");
        put("Java Script", "Master JavaScript for dynamic web development.");
        put("Languages", "Study different languages around the world.");
        put("Sciences", "Understand core scientific concepts and discoveries.");
        put("Grammar", "Improve your grammar and writing skills.");
        put("Music", "Explore music theory, instruments, and history.");
        put("Programming", "Learn to code in different programming languages.");
        put("History", "Dive into historical events and civilizations.");
    }};

    public CourseStrategyWindow(JFrame parent, String courseName, String courseIconPath) {
        super(parent, "Select Strategy", true);
        this.courseName = courseName;
        this.courseIconPath = courseIconPath;

        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        setLayout(new BorderLayout());
        setSize(480, 320);
        setResizable(false);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Título con icono
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        JLabel title = new JLabel(courseName);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setIcon(loadIcon(courseIconPath, 28, 28));
        titlePanel.add(title);
        mainPanel.add(titlePanel);

        mainPanel.add(Box.createVerticalStrut(3));

        // Descripción
        String desc = courseDescriptions.getOrDefault(courseName, "Course description not available.");
        JLabel descriptionArea = new JLabel(desc);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        mainPanel.add(descriptionArea);

        mainPanel.add(Box.createVerticalStrut(40));

        // Estrategias


        JPanel strategyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        JLabel stratLabel = new JLabel("Strategy");
        stratLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        stratLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(stratLabel);
        strategyPanel.setOpaque(false);
        strategyPanel.add(createStrategyButton("Sequential", "icons/strategy/sequential.png"));
        strategyPanel.add(createStrategyButton("Random", "icons/strategy/random.png"));
        strategyPanel.add(createStrategyButton("Spaced Repetition", "icons/strategy/repetition.png"));

        mainPanel.add(strategyPanel);

        // Panel de botones abajo a la derecha
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        StyledButton acceptButton = new StyledButton("Accept", Color.WHITE, Color.BLACK);
        StyledButton cancelButton = new StyledButton("Cancel", Color.WHITE, Color.BLACK);

        acceptButton.addActionListener(e -> {
            if (stratLabel == null) { //TODO: Check if a strategy is selected
                JOptionPane.showMessageDialog(this, "Please, select a strategy.");
            } else {
                dispose();
                //TODO: Controller functionality to handle the selected strategy and cretate registerCourse
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(acceptButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);



    }

    private JPanel createStrategyButton(String name, String iconPath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JButton button = new JButton(loadIcon(iconPath, 40, 40));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        addHoverEffect(button);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));

        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
        panel.add(label);

        button.addActionListener(e -> {
            addHoverEffect(button);
        });



        return panel;
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
            System.err.println("No se encontró el icono: " + path);
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(url);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
