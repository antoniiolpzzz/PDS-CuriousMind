package com.pds.curiousmind.view.common;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.home.course.CourseDashboard;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

/**
 * BackgroundComponent provides a utility method to create a JPanel with a background image,
 * a top bar showing the application title, and a configurable bottom bar with an action label.
 * The action label changes behavior depending on the specified type (e.g. exit, home, logout).
 */
public class BackgroundComponent {

    /** Singleton controller instance */
    static Controller controller = Controller.INSTANCE;

    /**
     * Creates a JPanel with a background image and adds top and bottom components.
     * The bottom component contains an action label that performs different behaviors
     * based on the 'type' parameter.
     *
     * @param parentFrame the parent JFrame which will be disposed on action label click (can be null)
     * @param user the current user (used for home navigation)
     * @param course the current registered course (used for exit action)
     * @param type the type of action label ("exit", "home", "logout", or other)
     * @return a JPanel configured with background image, top app title, and bottom action label
     */
    public static JPanel createBackground(JFrame parentFrame, User user, RegisteredCourse course, String type) {

        JPanel basePanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        basePanel.setLayout(new BorderLayout());

        // Top panel with the application title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        JLabel appTitle = new JLabel(APP_TITLE);
        appTitle.setFont(new Font(FONT_NAME, Font.BOLD, 30));
        appTitle.setForeground(Color.WHITE);
        topPanel.add(appTitle);
        basePanel.add(topPanel, BorderLayout.NORTH);

        // Bottom panel with an action label depending on 'type'
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);

        String labelText;
        Icon labelIcon;
        Runnable onClickAction;
        Font labelFont = new Font(FONT_NAME, Font.PLAIN, 20);

        // Determine label text, icon and action based on type
        switch (type) {
            case "exit":
                labelText = "Exit";
                labelIcon = loadIcon(ICON_LOGOUT, 20, 20);
                onClickAction = () -> {
                    new CourseDashboard(course);
                    controller.endGame();
                };
                break;
            case "home":
                labelText = "Home";
                labelIcon = loadIcon(ICON_HOME, 20, 20);
                onClickAction = () -> new HomeWindow(user);
                break;
            case "logout":
                labelText = "Logout";
                labelIcon = loadIcon(ICON_LOGOUT, 20, 20);
                onClickAction = LoginWindow::new;
                break;
            default:
                labelText = "Welcome";
                labelIcon = loadIcon("icons/button/mano.png", 40, 40);
                labelFont = new Font(FONT_NAME, Font.BOLD, 35);
                onClickAction = () -> {};
                break;
        }

        // Create the clickable label with icon and add mouse interaction effects
        JLabel actionLabel = new JLabel(labelText, labelIcon, JLabel.LEFT);
        actionLabel.setFont(labelFont);
        actionLabel.setForeground(Color.WHITE);
        actionLabel.setOpaque(false);
        actionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        actionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            final Color original = actionLabel.getForeground();

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onClickAction.run();
                if (parentFrame != null) {
                    parentFrame.dispose();
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                actionLabel.setForeground(new Color(150, 150, 150));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                actionLabel.setForeground(original);
            }
        });

        bottomPanel.add(actionLabel);
        basePanel.add(bottomPanel, BorderLayout.SOUTH);

        return basePanel;
    }
}
