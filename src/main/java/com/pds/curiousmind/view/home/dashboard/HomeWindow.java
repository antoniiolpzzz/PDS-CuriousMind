package com.pds.curiousmind.view.home.dashboard;

import com.pds.curiousmind.view.common.*;
import com.pds.curiousmind.view.home.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class HomeWindow extends JFrame {

    private final RoundedPanel rightPanel;

    public HomeWindow() {
        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1200, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.BLACK);
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        JLabel appTitle = new JLabel("CuriousMind");
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 26));
        appTitle.setForeground(Color.WHITE);
        topPanel.add(appTitle);
        basePanel.add(topPanel, BorderLayout.NORTH);


        // Panel inferior alineado a la izquierda
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);

        // Label "Log out" con estilo de botón
        JLabel logoutLabel = new JLabel("Log out");
        logoutLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setOpaque(false); // Sin fondo
        logoutLabel.setBorder(null);  // Sin borde
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = logoutLabel.getForeground();
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                //new LoginWindow();
                dispose();
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                logoutLabel.setForeground(new Color(150, 150, 150)); // Cambia a gris al hacer hover
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                logoutLabel.setForeground(original);
            }
        });

        bottomPanel.add(logoutLabel);
        basePanel.add(bottomPanel, BorderLayout.SOUTH);

        //BackgroundPanel basePanel = new BackgroundPanel("icons/A3B80D76-7857-4DF3-8E9B-77619D7A2D56_1_105_c.jpeg");

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

        JLabel title = new JLabel("Home");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(title);

        rightPanel.add(Box.createVerticalStrut(10));

        addSection("Your courses", Arrays.asList(
                new String[]{"German", "icons/german.png"},
                new String[]{"Modern History", "icons/history.png"},
                new String[]{"Java Script", "icons/js.png"}
        ));

        addSection("New course", Arrays.asList(
                new String[]{"Languages", "icons/languages.png"},
                new String[]{"Sciences", "icons/sciences.png"},
                new String[]{"Grammar", "icons/grammar.png"},
                new String[]{"Music", "icons/music.png"},
                new String[]{"Programming", "icons/programming.png"},
                new String[]{"History", "icons/history.png"}
        ));

        rightPanel.add(sectionTitle("Create your course"));
        rightPanel.add(Box.createVerticalStrut(5));
        JButton createButton = new JButton("+   Import Json");
        createButton.setPreferredSize(new Dimension(200, 40));
        createButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addHoverEffect(createButton);

        createButton.addActionListener(e -> {
            new JsonChooserWindow(this);
        });
        rightPanel.add(createButton);

        setVisible(true);
    }

    private void addSection(String section, List<String[]> data) {
        rightPanel.add(sectionTitle(section));

        JPanel row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        row.setOpaque(false);

        for (String[] d : data) {
            row.add(new CourseItemPanel(d[0], d[1], () -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(rightPanel);
                new CourseStrategyWindow(topFrame, d[0], d[1]);
            }));
        }

        rightPanel.add(row);
        rightPanel.add(Box.createVerticalStrut(10));
    }

    private JPanel sectionTitle(String text) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.BLACK);
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(label);
        return panel;
    }

    private void addHoverEffect(JButton button) {
        Color original = button.getBackground();
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(original.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(original);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeWindow::new);
    }
}
