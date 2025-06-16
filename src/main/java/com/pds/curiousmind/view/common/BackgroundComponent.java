package com.pds.curiousmind.view.common;

import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.home.course.CourseDashboard;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import java.awt.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

public class BackgroundComponent {

    public static JPanel createBackground(JFrame parentFrame, User user, RegisteredCourse course, String type) {

        JPanel basePanel = new BackgroundPanel("icons/background/background.jpg");
        basePanel.setLayout(new BorderLayout());
        // Top bar with app title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        JLabel appTitle = new JLabel("CuriousMind");
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        appTitle.setForeground(Color.WHITE);
        topPanel.add(appTitle);
        basePanel.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        String labelText;
        Icon labelIcon;
        Runnable onClickAction;
        Font labelFont = new Font("SansSerif", Font.PLAIN, 20);

        switch (type) {
            case "exit":
                labelText = "Exit";
                labelIcon = loadIcon("icons/button/logout.jpg", 20, 20);
                onClickAction = () -> new CourseDashboard(course);
                break;
            case "home":
                labelText = "Home";
                labelIcon = loadIcon("icons/button/home.jpg", 20, 20);
                onClickAction = () -> new HomeWindow(user);
                break;
            case "logout":
                labelText = "Logout";
                labelIcon = loadIcon("icons/button/logout.jpg", 20, 20);
                onClickAction = LoginWindow::new;
                break;
            default:
                labelText = "Welcome";
                labelIcon = loadIcon("icons/button/mano.png", 40, 40);
                labelFont = new Font("SansSerif", Font.BOLD, 35);
                onClickAction = () -> {};
                break;
        }

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
