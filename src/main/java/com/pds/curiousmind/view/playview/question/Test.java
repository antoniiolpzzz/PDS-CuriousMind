package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.components.SectionTitle;
import com.pds.curiousmind.view.home.course.CourseDashboard;
import com.pds.curiousmind.view.home.stats.components.RoundedProgressBar;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;


public class Test extends JFrame {

    //TODO: This should receive only the content block.
    public Test(String title, String iconPath, String statement, java.util.List<String> options) {
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
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        JLabel logoutLabel = new JLabel("Exit", loadIcon("icons/button/logout.jpg", 20, 20), JLabel.LEFT);
        logoutLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setOpaque(false);
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color original = logoutLabel.getForeground();
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new CourseDashboard(title, iconPath);
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
        JLabel homeTitle = new JLabel(title, SwingConstants.LEFT);
        homeTitle.setFont(new Font("SansSerif", Font.BOLD, 35));
        homeTitle.setForeground(Color.BLACK);
        homeTitle.setIcon(loadIcon(iconPath, 24, 24));
        headerPanel.add(homeTitle, BorderLayout.WEST);
        rightPanel.add(headerPanel);
        rightPanel.add(Box.createVerticalStrut(20));

        // Section title for the content block
        //TODO: Reucuperate the name of the content block from the content block object
        rightPanel.add(sectionTitle("--Name of the content block--"));

        //Progress bar related with de number of questions answered

        rightPanel.add(Box.createVerticalStrut(10));
        JProgressBar progressBar = new RoundedProgressBar(getProgressForCourse());
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        progressBar.setForeground(new Color(76, 175, 80)); // Green color
        progressBar.setBackground(new Color(245, 245, 245));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(progressBar);
        rightPanel.add(Box.createVerticalStrut(10));




        // Statement label
        JLabel statementLabel = new JLabel("<html><div style='text-align: center; width: 100%'>" + statement + "</div></html>");
        statementLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        statementLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statementLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        rightPanel.add(statementLabel);
        rightPanel.add(Box.createVerticalStrut(10));

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(false);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ButtonGroup group = new ButtonGroup();
        for (String option : options) {
            JToggleButton optionButton = new JToggleButton(option);
            optionButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
            optionButton.setOpaque(true);
            optionButton.setBackground(new Color(245, 245, 245));
            optionButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            optionButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
            optionButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
            optionButton.setHorizontalAlignment(SwingConstants.LEFT);
            group.add(optionButton);
            optionsPanel.add(optionButton);
            optionsPanel.add(Box.createVerticalStrut(10));
        }

        rightPanel.add(optionsPanel);
        rightPanel.add(Box.createVerticalStrut(10));

        // Submit button
        StyledButton submitButton = new StyledButton("Submit", Color.BLACK, Color.WHITE);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            //TODO: Handle the submission logic here
            JOptionPane.showMessageDialog(this, "Test submitted successfully!");
        });
        rightPanel.add(submitButton);
        rightPanel.add(Box.createVerticalStrut(20));

        setVisible(true);
    }

    private int getProgressForCourse() {
        //TODO: Recuperate the progress of the content block related with the number of questions answered
        return 30; // Example value, this should be dynamic based on actual progress

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test(
            "German",
            "icons/course/german.png",
                "If you are hungry and want some crisps, how would you order them?",
            Arrays.asList("A.   Kuchen", "B.   Kartoffelchips", "C.   Kopfsalat", "D.   Lachs")
        ));
    }


}
