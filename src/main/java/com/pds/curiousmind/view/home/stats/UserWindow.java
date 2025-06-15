package com.pds.curiousmind.view.home.stats;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;
import com.pds.curiousmind.view.home.stats.components.CourseWithProgressPanel;
import com.pds.curiousmind.view.home.stats.components.StatsBlock;
import com.pds.curiousmind.view.home.course.CourseDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.CourseRowSection.createCourseRowSection;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;


public class UserWindow extends JFrame {

    public UserWindow() {  // TODO: receive user

        setTitle("CuriousMind - Profile");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // BACKGROUND PANEL
        JPanel basePanel = createBackground("","", "home");
        setContentPane(basePanel);


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

        // HEADER SECTION
        // TODO: JLabel homeTitle = new JLabel("Hello" + user.getName() + "!");
        // TODO: Stirng iconPath = user.getIconPath());
        String iconPath = "icons/button/user.png";
        JLabel homeTitle = new JLabel(" Hello Javier!");
        homeTitle.setIcon(loadIcon(iconPath, 30, 30));
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // TODO: JLabel levelLabel = new JLabel("LEVEL:" + user.getLevel()));
        JLabel levelLabel = new JLabel("LEVEL: 5300");
        levelLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        levelLabel.setForeground(new Color(80, 80, 80));
        levelLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        headerPanel.add(levelLabel, BorderLayout.EAST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));


        // COURSES SECTION
        //TODO: Controller has to recuperate the registered courses from the user
        // myCourses = controller.getRegisteredCourses(user.getId());
        // JPanel rowMyCourses = createCourseRowSection(myCourses);
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

        JPanel statsWrapper = new JPanel();
        statsWrapper.setLayout(new BoxLayout(statsWrapper, BoxLayout.Y_AXIS));
        statsWrapper.setOpaque(false);

        // Fila 1
        // TODO: Recuperate the stats from the user
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        row1.setOpaque(false);
        row1.add(new StatsBlock("Best streak", "15 days", "icons/stat/streak.jpg"));
        row1.add(new StatsBlock("Days of use", "63 days", "icons/stat/days.jpg"));

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        row2.setOpaque(false);
        row2.add(new StatsBlock("Completed courses", "2 courses", "icons/stat/courses.jpg"));
        row2.add(new StatsBlock("Time of use", "15 min/day", "icons/stat/time.jpg"));

        // Añadir al panel
        statsWrapper.add(row1);
        statsWrapper.add(Box.createVerticalStrut(10));
        statsWrapper.add(row2);

        rightPanel.add(statsWrapper);


        setVisible(true);
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
