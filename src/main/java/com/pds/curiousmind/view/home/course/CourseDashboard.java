package com.pds.curiousmind.view.home.course;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;


public class CourseDashboard extends JFrame {

    public CourseDashboard(String title, String iconPath) {
        setTitle("CuriousMind - Course Dashboard");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel basePanel = new BackgroundPanel("icons/background/background.jpg");
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

        // Top bar with app title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        JLabel appTitle = new JLabel("CuriousMind");
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        appTitle.setForeground(Color.WHITE);
        topPanel.add(appTitle);
        basePanel.add(topPanel, BorderLayout.NORTH);

        // Bottom home label
        //TODO: Añadir un icono de home
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        JLabel logoutLabel = new JLabel("Home");
        logoutLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setOpaque(false);
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = logoutLabel.getForeground();
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new HomeWindow();
                dispose();
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                logoutLabel.setForeground(new Color(150, 150, 150));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                logoutLabel.setForeground(original);
            }
        });
        bottomPanel.add(logoutLabel);
        basePanel.add(bottomPanel, BorderLayout.SOUTH);


        // Right panel setup
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);

        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(950, 0));

        // Header
        JLabel homeTitle = new JLabel(title); // Usar el parámetro title
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setIcon(loadIcon(iconPath, 20, 20)); // Usar el parámetro iconPath
        homeTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel);

        // Courses section
        //TODO: Recuperate the course Content Blocks

        // Content blocks section (vertical, clickable, hover effect)
        rightPanel.add(sectionTitle("Content blocks"));
        java.util.List<String> contentNames = java.util.Arrays.asList(
            "Introduction",
            "Grammar",
            "Vocabulary",
            "Exercises",
            "Listening Practice",
            "Speaking Practice",
            "Reading Comprehension",
            "Writing Practice"
        );
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(createContentColumnSection(contentNames));
        rightPanel.add(Box.createVerticalStrut(10));

        setVisible(true);
    }

    // Creates a vertical column of clickable content blocks with hover effect
    private JPanel createContentColumnSection(java.util.List<String> contentNames) {
        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setOpaque(false);
        column.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (String name : contentNames) {
            JLabel label = new JLabel(name);
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setOpaque(true);
            label.setBackground(new Color(235, 235, 235)); // Fondo gris claro
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            label.setPreferredSize(new Dimension(Integer.MAX_VALUE, 35));
            label.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear a la izquierda
            // Hover effect
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                Color originalBg = label.getBackground();
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    label.setBackground(new Color(220, 235, 255));
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    label.setBackground(originalBg);
                }
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    // TODO: Handle content block click, open a new window with questions
                }
            });
            column.add(label);
            column.add(Box.createVerticalStrut(10));
        }
        return column;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseDashboard("German", "icons/course/german.png"));
    }
}
