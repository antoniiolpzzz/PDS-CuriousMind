package com.pds.curiousmind.view.home.dashboard;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.components.*;
import com.pds.curiousmind.view.home.stats.UserWindow;

import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.home.components.CourseRowSection.createCourseRowSection;
import static com.pds.curiousmind.view.home.components.CourseRowSection.createRegisteredCourseRowSection;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class represents the main home window of the CuriousMind application.
 * <p>
 * It displays:
 * <ul>
 *     <li>A personalized greeting and user profile icon</li>
 *     <li>The list of courses the user is currently enrolled in</li>
 *     <li>A section to discover new courses</li>
 *     <li>An option to import or create a custom course</li>
 * </ul>
 * Clicking the username or profile picture navigates to the {@link UserWindow} with stats and profile details.
 *
 * @see User
 * @see RegisteredCourse
 * @see Course
 * @see UserWindow
 * @see JsonChooserWindow
 * @author
 */
public class HomeWindow extends JFrame {

    private static final Controller controller = Controller.INSTANCE;

    /**
     * Constructs the home window for the given user, initializing all components and layout.
     *
     * @param user The current logged-in user. If null, a guest view is shown.
     */
    public HomeWindow(User user) {

        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Set the background panel
        JPanel basePanel = createBackground(this, null, null, "logout");
        setContentPane(basePanel);

        // Right content panel with rounded layout
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);
        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(950, 0));

        // Header section: Title and user profile info
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel homeTitle = new JLabel("Home", SwingConstants.LEFT);
        homeTitle.setFont(new Font(FONT_NAME, Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userPanel.setOpaque(false);

        String username = (user != null && user.getUsername() != null) ? user.getUsername() : "Invitado";
        StyledButton name = new StyledButton(username, Color.BLACK, Color.WHITE);
        name.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setPreferredSize(new Dimension(120, 35));
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new UserWindow(user);
                dispose();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                name.setBackground(new Color(50, 50, 50));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                name.setBackground(Color.BLACK);
            }
        });

        String iconPath = controller.getUserPhoto();
        ImageIcon icon = loadIcon(iconPath, 35, 35);
        assert icon != null;
        Image image = icon.getImage();
        JLabel iconLabel = new JLabel(new ImageIcon(image));
        iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new UserWindow(user);
                dispose();
            }
        });

        userPanel.add(name);
        userPanel.add(iconLabel);
        headerPanel.add(userPanel, BorderLayout.EAST);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));

        // Section: Your registered courses
        List<RegisteredCourse> myCourses = controller.getRegisteredCourses();
        JPanel rowMyCourses = createRegisteredCourseRowSection(this, myCourses);
        rightPanel.add(sectionTitle("Your courses"));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new CoursesScrollPanel(rowMyCourses));
        rightPanel.add(Box.createVerticalStrut(20));

        // Section: All available courses
        rightPanel.add(sectionTitle("New course"));
        List<Course> allCourses = controller.getAllCourses();
        JPanel rowNewCourses = createCourseRowSection(this, allCourses, user);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new CoursesScrollPanel(rowNewCourses));
        rightPanel.add(Box.createVerticalStrut(20));

        // Section: Create or import a custom course
        rightPanel.add(sectionTitle("Create your course"));
        rightPanel.add(Box.createVerticalStrut(10));
        JPanel createBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        createBtnPanel.setOpaque(false);

        StyledButton createButton = new StyledButton("+", Color.WHITE, Color.BLACK);
        createButton.addActionListener(e -> {
            new JsonChooserWindow(this, user);
            dispose();
        });

        createBtnPanel.add(Box.createHorizontalStrut(5));
        createBtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        createBtnPanel.add(createButton);
        rightPanel.add(createBtnPanel);

        setVisible(true);
    }
}
