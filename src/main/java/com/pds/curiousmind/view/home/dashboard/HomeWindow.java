package com.pds.curiousmind.view.home.dashboard;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.components.*;
import com.pds.curiousmind.view.home.stats.UserWindow;
import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.course.CourseDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;


public class HomeWindow extends JFrame {

    public HomeWindow() {
        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Set up the main background panel
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

        // Bottom left log out label
        //TODO: Añadir un icono de log out
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        JLabel logoutLabel = new JLabel("Log out", loadIcon("icons/button/logout.jpg", 20, 20), JLabel.LEFT);
        logoutLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setOpaque(false);
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = logoutLabel.getForeground();
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
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

        // Right panel setup for main content
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

        // Header with user info and home title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel homeTitle = new JLabel("Home", SwingConstants.LEFT);
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userPanel.setOpaque(false);

        // User panel with title and icon
        JLabel title = new JLabel("Javier");
        title.setFont(new Font("SansSerif", Font.PLAIN, 16));
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(80, 35));
        title.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new UserWindow();
                dispose();
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                title.setBackground(new Color(50, 50, 50));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                title.setBackground(Color.BLACK);
            }
        });
        ImageIcon icon = loadIcon("icons/button/user.png", 35, 35);
        Image image = icon.getImage();
        JLabel iconLabel = new JLabel(new ImageIcon(image));
        iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new UserWindow();
                dispose();
            }
        });
        userPanel.add(title);
        userPanel.add(iconLabel);
        headerPanel.add(userPanel, BorderLayout.EAST);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));

        // Add course sections and scroll panels

        //TODO: Controller has to recuperate the registered courses from the database
        rightPanel.add(sectionTitle("Your courses"));
        JPanel rowMyCourses = createCourseRowSection(Arrays.asList(
                new String[]{"German", "icons/course/german.png"},
                new String[]{"Modern History", "icons/course/history.png"},
                new String[]{"Java Script", "icons/course/js.png"}
        ));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new CoursesScrollPanel(rowMyCourses));
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(sectionTitle("New course"));

        //TODO: Controller has to recuperate the all created courses from the database
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
        rightPanel.add(new CoursesScrollPanel(rowNewCourses));
        rightPanel.add(Box.createVerticalStrut(20));

        // Section for creating a new course
        rightPanel.add(sectionTitle("Create your course"));
        rightPanel.add(Box.createVerticalStrut(15));
        JPanel createBtnPanel = new JPanel();
        createBtnPanel.setLayout(new BoxLayout(createBtnPanel, BoxLayout.X_AXIS));
        createBtnPanel.setOpaque(false);
        createBtnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        createBtnPanel.add(Box.createHorizontalStrut(15));
        StyledButton createButton = new StyledButton("+   Import Json", Color.WHITE, Color.BLACK);
        createButton.setPreferredSize(new Dimension(200, 40));
        createButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addHoverEffect(createButton);
        createButton.addActionListener(e -> new JsonChooserWindow(this));
        createBtnPanel.add(createButton);
        rightPanel.add(createBtnPanel);

        setVisible(true);
    }

    // Helper to create a row of course panels
    private JPanel createCourseRowSection(List<String[]> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15));
        row.setOpaque(false);
        for (String[] d : courseData) {
            row.add(new CourseItemPanel(d[0], d[1], () -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(row);
                // Only allow opening CourseStrategyWindow if user is not registered
                if (!isRegisteredCourse(d[0])) {
                    new CourseStrategyWindow(topFrame, d[0], d[1]);
                } else {
                    dispose();
                    new CourseDashboard(d[0], d[1]);
                }
            }));
        }
        return row;
    }

     // Method to check if the user is registered in a course
    //TODO: Check if the user is registered in the course
    private boolean isRegisteredCourse(String courseName) {

        List<String> enrolledCourses = Arrays.asList("German", "Modern History", "Java Script");
        return enrolledCourses.contains(courseName);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeWindow::new);
    }

}
