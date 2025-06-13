package com.pds.curiousmind.view.authentication.login;

import com.pds.curiousmind.view.authentication.signup.SignupWindow;
import com.pds.curiousmind.view.authentication.components.*;
import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JCheckBox showPasswordCheckBox;

    public LoginWindow() {
        // Window configuration
        setTitle("CuriousMind - Log in");
        setMinimumSize(new Dimension(1300, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Background panel with image
        BackgroundPanel basePanel = new BackgroundPanel("icons/background/background.jpg");
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

        // Top panel with navigation buttons
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Application title
        JLabel titleLabel = new JLabel("CuriousMind");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        topBar.add(titleLabel, BorderLayout.NORTH);
        topBar.add(Box.createHorizontalStrut(500));

        // Welcome label
        JLabel welcomeLabel = new JLabel("WELCOME BACK!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topBar.add(welcomeLabel, BorderLayout.SOUTH);

        // Right-aligned buttons
        JPanel navButtons = new JPanel();
        navButtons.setOpaque(false);
        navButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        NavigationBar navBar = new NavigationBar("Log in");
        basePanel.add(navBar, BorderLayout.NORTH);
        topBar.add(navButtons, BorderLayout.CENTER);
        basePanel.add(topBar);

        // Right panel for the login form
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        // Rounded panel for the form content
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);

        // Add the right panel to the base panel
        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(370, 0));

        // Login form title
        JLabel loginTitle = new JLabel("Log in");
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(loginTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Username and password fields
        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username or email"));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createVerticalStrut(10));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createVerticalStrut(10));

        // Options panel to show password
        JPanel optionsPanel = new JPanel(new BorderLayout());
        optionsPanel.setOpaque(false);
        showPasswordCheckBox = new JCheckBox("Show password");
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.setForeground(Color.GRAY);
        showPasswordCheckBox.addActionListener((ActionEvent e) -> {
            passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? (char) 0 : '\u2022');
        });
        optionsPanel.add(showPasswordCheckBox, BorderLayout.WEST);
        rightPanel.add(optionsPanel);
        rightPanel.add(Box.createVerticalStrut(130));

        // Login button
        StyledButton loginButton = new StyledButton("Log in", Color.BLACK, Color.WHITE);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            if (checkFields(usernameField.getText(), passwordField))
            {
                //TODO: Controler check password and username
                //if(controler.checkFields(usernameField,passwordField))
                {
                    dispose();
                    new HomeWindow();
                }

            }

        });
        rightPanel.add(loginButton);

        // Spacing and "Or" label
        rightPanel.add(Box.createVerticalStrut(15));
        JLabel orLabel = new JLabel("Or", SwingConstants.CENTER);
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLabel.setForeground(Color.GRAY);
        rightPanel.add(orLabel);
        rightPanel.add(Box.createVerticalStrut(15));

        // Sign up button
        StyledButton signupButton = new StyledButton("Sign up", new Color(240, 240, 240), Color.BLACK);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupButton.addActionListener(e -> {
            new SignupWindow();
            dispose();
        });
        rightPanel.add(signupButton);

        pack();
        setVisible(true);
    }

    public boolean checkFields(String username, JPasswordField password) {
        if (username.isEmpty() || password.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
