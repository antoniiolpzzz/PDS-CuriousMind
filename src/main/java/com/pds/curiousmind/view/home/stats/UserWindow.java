package com.pds.curiousmind.view.home.stats;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.components.*;
import com.pds.curiousmind.view.home.stats.components.CourseProgressPanel;
import com.pds.curiousmind.view.home.stats.components.CourseWithProgressPanel;
import com.pds.curiousmind.view.home.stats.components.StatsBlock;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;


public class UserWindow extends JFrame {

    private final RoundedPanel rightPanel;

    public UserWindow() {

        setTitle("CuriousMind - Profile");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel basePanel = new BackgroundPanel("icons/background/background.jpg");
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

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

        // Header
        JLabel homeTitle = new JLabel("Hello Javier!");
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));

        // Courses section
        //TODO: Recuperate the user's registered courses from the database
        rightPanel.add(sectionTitle("Your courses"));
        JPanel coursePanel = createCourseRowSection(Arrays.asList(
                new String[]{"German", "icons/course/german.png"},
                new String[]{"Modern History", "icons/course/history.png"},
                new String[]{"Java Script", "icons/course/js.png"}
        ));
        JScrollPane courseScroll = new JScrollPane(coursePanel);
        courseScroll.setPreferredSize(new Dimension(880, 230));
        courseScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        courseScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        courseScroll.setBorder(null);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(courseScroll);
        rightPanel.add(Box.createVerticalStrut(10));

        // Stats section
        rightPanel.add(sectionTitle("Your stats"));
        rightPanel.add(Box.createVerticalStrut(10));

        JPanel statsGrid = new JPanel(new GridLayout(2, 2, 30, 20));
        statsGrid.setOpaque(false);
        statsGrid.setMaximumSize(new Dimension(880, 200));

        //TODO: Recuperate the user's stats from the database
        statsGrid.add(new StatsBlock("Best streak", "15 days", "icons/stat/streak.jpg"));
        statsGrid.add(new StatsBlock("Days of use", "63 days", "icons/stat/days.jpg"));
        statsGrid.add(new StatsBlock("Completed courses", "2 courses", "icons/stat/courses.jpg"));
        statsGrid.add(new StatsBlock("Time of use", "15 min/day", "icons/stat/time.jpg"));

        rightPanel.add(statsGrid);

        setVisible(true);
    }


    private JPanel createCourseRowSection(List<String[]> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, -5));
        row.setOpaque(false);

        for (String[] d : courseData) {
            row.add(new CourseWithProgressPanel(d[0], d[1], getProgressForCourse(d[0]), () -> {
                //TODO: Open course dashboard with the content blocks
            }));

        }
        return row;
    }

    private int getProgressForCourse(String courseName) {
        //TODO: Recuperate the course progress from the database
        return switch (courseName) {
            case "German" -> 30;
            case "Modern History" -> 80;
            case "Java Script" -> 50;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserWindow::new);
    }
}
