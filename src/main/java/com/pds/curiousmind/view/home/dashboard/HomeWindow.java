package com.pds.curiousmind.view.home.dashboard;

import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.components.*;
import com.pds.curiousmind.view.home.stats.UserWindow;

import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static com.pds.curiousmind.view.home.components.CourseRowSection.createCourseRowSection;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;


public class HomeWindow extends JFrame {

    public HomeWindow(User user) { //TODO: receive user
        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1300, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // BACKGROUND PANEL
        JPanel basePanel = createBackground(this,"","", "logout");
        setContentPane(basePanel);

        // Right panel setup for main content
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

        // Header with user info and home title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel homeTitle = new JLabel("Home", SwingConstants.LEFT);
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userPanel.setOpaque(false);

        // User panel with title and icon
        //TODO: StyledButton name = new StyledButton(user.getName(), Color.BLACK, Color.WHITE);
        StyledButton name = new StyledButton("Javier", Color.BLACK, Color.WHITE);
        name.setFont(new Font("SansSerif", Font.PLAIN, 16));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setPreferredSize(new Dimension(80, 35));
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new UserWindow();
                // TODO: new UserWindow(user);
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
        String iconPath = "icons/button/user.png"; //TODO: iconpath = user.getIconPath()
        ImageIcon icon = loadIcon(iconPath, 35, 35);
        assert icon != null;
        Image image = icon.getImage();
        JLabel iconLabel = new JLabel(new ImageIcon(image));
        iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new UserWindow();
                // TODO: new UserWindow(user);
                dispose();
            }
        });
        userPanel.add(name);
        userPanel.add(iconLabel);
        headerPanel.add(userPanel, BorderLayout.EAST);
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));

        // YOUR COURSES SECTION

        //TODO: Controller has to recuperate the registered courses from the user
        // myCourses = controller.getRegisteredCourses(user);
        // JPanel rowMyCourses = createCourseRowSection(this, myCourses);
        rightPanel.add(sectionTitle("Your courses"));
        JPanel rowMyCourses = createCourseRowSection(this, Arrays.asList(
                new String[]{"German", "icons/course/german.png"},
                new String[]{"Modern History", "icons/course/history.png"},
                new String[]{"Java Script", "icons/course/js.png"}
        ));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new CoursesScrollPanel(rowMyCourses));
        rightPanel.add(Box.createVerticalStrut(20));


        // ALL COURSES SECTION

        //TODO: Controller has to recuperate all the courses from the database
        // allCourses = controller.getAllCourses();
        // JPanel rowNewCourses = createCourseRowSection(this, allCourses);
        rightPanel.add(sectionTitle("New course"));
        JPanel rowNewCourses = createCourseRowSection(this, Arrays.asList(
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

        // IMPORT COURSE SECTION

        rightPanel.add(sectionTitle("Create your course"));
        rightPanel.add(Box.createVerticalStrut(10));
        JPanel createBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        createBtnPanel.setOpaque(false); // o blanco si lo prefieres

        StyledButton createButton = new StyledButton("+", Color.WHITE, Color.BLACK);
        createButton.addActionListener(e -> new JsonChooserWindow(this));

        createBtnPanel.add(Box.createHorizontalStrut(5));
        createBtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        createBtnPanel.add(createButton);
        rightPanel.add(createBtnPanel);


        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeWindow::new);
    }

}
