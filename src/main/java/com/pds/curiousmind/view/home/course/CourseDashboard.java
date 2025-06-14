package com.pds.curiousmind.view.home.course;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;
import com.pds.curiousmind.view.home.course.components.ContentBlockRow;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.home.course.components.ContentBlockRow.createContentColumnSection;

public class CourseDashboard extends JFrame {

    //TODO: This should receive Course course
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
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        JLabel logoutLabel = new JLabel("Home", loadIcon("icons/button/home.jpg", 20, 20), JLabel.LEFT);
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

        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setPreferredSize(new Dimension(950, 0));
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
        basePanel.add(rightWrapper, BorderLayout.EAST);

        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));

        // Header
        JLabel homeTitle = new JLabel(title);
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setIcon(loadIcon(iconPath, 24, 24));
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setOpaque(false);
        rightPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel sectionWrapper = new JPanel(new BorderLayout());
        sectionWrapper.setOpaque(false);
        sectionWrapper.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        // CONTENT BLOCKS SECTION
        sectionWrapper.add(sectionTitle("Content blocks"), BorderLayout.CENTER);
        centerPanel.add(sectionWrapper, BorderLayout.NORTH);

        JPanel scrollContent = new JPanel();
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
        scrollContent.setOpaque(false);
        scrollContent.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        //TODO: controller get the content blocks from the course
        // contentNames = controller.getContentBlocks(courseId);

        java.util.List<String> contentNames = java.util.Arrays.asList(
                "Basic words",
                "Basic sentences",
                "Introduce yourself",
                "Food Vocabulary",
                "Talk to your friends",
                "Describe people",
                "Talk about animals"
        );
        scrollContent.add(Box.createVerticalStrut(20));
        scrollContent.add(createContentColumnSection(contentNames));
        scrollContent.add(Box.createVerticalStrut(20));

        JScrollPane scrollPane = new JScrollPane(scrollContent);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseDashboard("German", "icons/course/german.png"));
    }
}
