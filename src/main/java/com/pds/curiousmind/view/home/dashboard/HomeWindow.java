package com.pds.curiousmind.view.home.dashboard;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class HomeWindow extends JFrame {

    private final RoundedPanel rightPanel;

    public HomeWindow() {
        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel basePanel = new BackgroundPanel("icons/background/background.jpg");
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

        // Top bar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        JLabel appTitle = new JLabel("CuriousMind");
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        appTitle.setForeground(Color.WHITE);
        topPanel.add(appTitle);
        basePanel.add(topPanel, BorderLayout.NORTH);

        // Bottom left "Log out"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        JLabel logoutLabel = new JLabel("Log out");
        logoutLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setOpaque(false);
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = logoutLabel.getForeground();
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose(); // or new LoginWindow();
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

        rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);

        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(950, 0));

        // Title
        JLabel title = new JLabel("Home");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(title);
        rightPanel.add(Box.createVerticalStrut(10));

        // Sections
        rightPanel.add(sectionTitle("Your courses"));
        JPanel rowMyCourses = createCourseRowSection(Arrays.asList(
                new String[]{"German", "icons/course/german.png"},
                new String[]{"Modern History", "icons/course/history.png"},
                new String[]{"Java Script", "icons/course/js.png"}
        ));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new MyCoursesScrollPanel(rowMyCourses));
        rightPanel.add(Box.createVerticalStrut(20));

        rightPanel.add(sectionTitle("New course"));
        JPanel rowNewCourses = createCourseRowSection(Arrays.asList(
                new String[]{"Languages", "icons/course/languages.png"},
                new String[]{"Sciences", "icons/course/sciences.png"},
                new String[]{"Grammar", "icons/course/grammar.png"},
                new String[]{"Music", "icons/course/music.png"},
                new String[]{"Programming", "icons/course/programming.png"},
                new String[]{"History", "icons/course/history.png"},
                new String[]{"Sciences", "icons/course/sciences.png"},
                new String[]{"Grammar", "icons/course/grammar.png"},
                new String[]{"Music", "icons/course/music.png"},
                new String[]{"Programming", "icons/course/programming.png"}
        ));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new NewCoursesScrollPanel(rowNewCourses));
        rightPanel.add(Box.createVerticalStrut(20));


        // Create course
        rightPanel.add(sectionTitle("Create your course"));
        rightPanel.add(Box.createVerticalStrut(15));
        StyledButton createButton = new StyledButton("+   Import Json", Color.WHITE, Color.BLACK);
        createButton.setPreferredSize(new Dimension(200, 40));
        createButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addHoverEffect(createButton);
        createButton.addActionListener(e -> new JsonChooserWindow(this));
        rightPanel.add(createButton);

        setVisible(true);
    }

    private JPanel sectionTitle(String text) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.BLACK);
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(label);
        return panel;
    }

    private JPanel createCourseRowSection(List<String[]> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15)); // 0 filas, 4 columnas, con separaciones
        row.setOpaque(false);

        for (String[] d : courseData) {
            row.add(new CourseItemPanel(d[0], d[1], () -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(row);
                new CourseStrategyWindow(topFrame, d[0], d[1]);
            }));
        }

        return row;
    }



    private void addHoverEffect(JButton button) {
        Color original = button.getBackground();
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(original.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(original);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeWindow::new);
    }

}
