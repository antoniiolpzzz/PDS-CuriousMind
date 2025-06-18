package com.pds.curiousmind.view.home.course;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.common.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.home.course.components.ContentBlockRow.createContentColumnSection;

/**
 * CourseDashboard is the main window for displaying a course's details and its content blocks.
 * It sets up the UI layout, including the course header and a scrollable list of content blocks.
 */
public class CourseDashboard extends JFrame {

    private static final Controller controller = Controller.INSTANCE;

    /**
     * Constructs the CourseDashboard window for a given course.
     * Initializes the UI components, including the background, header, and content block list.
     *
     * @param course The registered course to display.
     */
    public CourseDashboard(RegisteredCourse course) {
        setTitle("CuriousMind - Course Dashboard");
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        User user = controller.getCurrentUser();

        // BACKGROUND PANEL
        JPanel basePanel = createBackground(this,user, null, "home");
        setContentPane(basePanel);

        // RIGHT PANEL SETUP FOR MAIN CONTENT
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

        // HEADER SECTION

        String iconPath = controller.downloadImageFromUrl(course.getImageURL());
        JLabel homeTitle = new JLabel(course.getName());
        homeTitle.setFont(new Font(FONT_NAME, Font.BOLD, 35));
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

        List<RegisteredContentBlock> contenblocks = course.getRegisteredContentBlocks();

        scrollContent.add(Box.createVerticalStrut(20));
        scrollContent.add(createContentColumnSection(this, course, contenblocks));
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

}