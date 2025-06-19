package com.pds.curiousmind.view.home.stats;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;
import com.pds.curiousmind.view.home.stats.components.CourseWithProgressPanel;
import com.pds.curiousmind.view.home.stats.components.StatsBlock;
import com.pds.curiousmind.view.home.course.CourseDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;

/**
 * Represents the user profile window in the CuriousMind application.
 * <p>
 * This window displays the user's greeting message, level, list of registered courses,
 * and personal usage statistics such as best streak, usage days, completed courses, and time spent.
 * </p>
 * It uses a custom layout with a background and dynamically loads user-specific data
 * through the {@link Controller}.
 *
 * @author
 */
public class UserWindow extends JFrame {

    private static final Controller controller = Controller.INSTANCE;

    /**
     * Constructs the {@code UserWindow} and initializes all UI components.
     *
     * @param user The current logged-in user whose data will be displayed.
     */
    public UserWindow(User user) {

        setTitle("CuriousMind - Profile");
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // BACKGROUND PANEL
        JPanel basePanel = createBackground(this, user, null, "home");
        setContentPane(basePanel);

        // Right panel setup
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);

        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(950, 0));

        // HEADER SECTION
        JLabel homeTitle = new JLabel("Hello " + user.getUsername() + "!");
        String iconPath = controller.getUserPhoto();
        homeTitle.setIcon(loadIcon(iconPath, 30, 30));
        homeTitle.setFont(new Font(FONT_NAME, Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int level = controller.getUserLevel();
        JLabel levelLabel = new JLabel("LEVEL: " + level);
        levelLabel.setFont(new Font(FONT_NAME, Font.BOLD, 22));
        levelLabel.setForeground(new Color(80, 80, 80));
        levelLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        headerPanel.add(levelLabel, BorderLayout.EAST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));

        // COURSES SECTION
        List<RegisteredCourse> myCourses = controller.getRegisteredCourses();
        JPanel rowMyCourses = createCourseRowSection(myCourses);
        rightPanel.add(sectionTitle("Your courses"));
        JScrollPane courseScroll = new JScrollPane(rowMyCourses);
        courseScroll.setPreferredSize(new Dimension(880, 230));
        courseScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        courseScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        courseScroll.setBackground(Color.WHITE);
        courseScroll.setBorder(null);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(courseScroll);
        rightPanel.add(Box.createVerticalStrut(10));

        // STATS SECTION
        Stat stats = controller.getUserStats();

        rightPanel.add(sectionTitle("Your stats"));
        rightPanel.add(Box.createVerticalStrut(10));

        JPanel statsWrapper = new JPanel();
        statsWrapper.setLayout(new BoxLayout(statsWrapper, BoxLayout.Y_AXIS));
        statsWrapper.setOpaque(false);

        // Row 1
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        row1.setOpaque(false);
        row1.add(new StatsBlock("Best streak", stats.getBestStreak(), ICON_STREAK));
        row1.add(new StatsBlock("Days of use", stats.getEntries().size(), ICON_DAY));

        // Row 2
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        row2.setOpaque(false);
        row2.add(new StatsBlock("Completed Courses", stats.getCompletedCourses(), ICON_COURSES));
        row2.add(new StatsBlock("Time of use (mins)", (int) ((stats.getTimeSpent())/60), ICON_TIME));

        statsWrapper.add(row1);
        statsWrapper.add(Box.createVerticalStrut(10));
        statsWrapper.add(row2);

        rightPanel.add(statsWrapper);

        setVisible(true);
    }

    /**
     * Creates the panel displaying the user's registered courses,
     * each with a visual progress indicator and access to the course dashboard.
     *
     * @param courseData The list of courses registered by the user.
     * @return A JPanel containing all course panels.
     */
    private JPanel createCourseRowSection(List<RegisteredCourse> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 0, -5));
        row.setOpaque(false);

        for (RegisteredCourse rc : courseData) {
            int progress = (int) Math.round(getProgressForCourse(rc));
            row.add(new CourseWithProgressPanel(rc, progress, () -> {
                dispose();
                new CourseDashboard(rc);
            }));
        }
        return row;
    }

    /**
     * Returns the progress of a given course.
     *
     * @param course The course to evaluate.
     * @return A double representing the course progress (typically between 0 and 100).
     */
    private double getProgressForCourse(RegisteredCourse course) {
        return course.getProgress();
    }
}
