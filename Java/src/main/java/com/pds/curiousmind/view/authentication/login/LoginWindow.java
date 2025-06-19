package com.pds.curiousmind.view.authentication.login;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.authentication.signup.SignupWindow;
import com.pds.curiousmind.view.authentication.components.*;
import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

/**
 * LoginWindow is the main window for user authentication.
 * It provides the UI for logging in and navigating to the sign-up window.
 */
public class LoginWindow extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JCheckBox showPasswordCheckBox;

    private final Controller controller = Controller.INSTANCE;

    /**
     * Constructs the login window, sets up the UI components and event listeners.
     */
    public LoginWindow() {
        // Window configuration
        setTitle("CuriousMind - Log in");
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Background panel with image
        BackgroundPanel basePanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

        // Top panel with navigation buttons and title
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Application title
        JLabel titleLabel = new JLabel(APP_TITLE);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        topBar.add(titleLabel, BorderLayout.NORTH);
        topBar.add(Box.createHorizontalStrut(500));

        // Welcome label
        JLabel welcomeLabel = new JLabel(LOGIN_WELCOME);
        welcomeLabel.setFont(new Font(FONT_NAME, Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topBar.add(welcomeLabel, BorderLayout.SOUTH);

        JPanel navButtons = new JPanel();
        navButtons.setOpaque(false);
        navButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        NavigationBar navBar = new NavigationBar(LOGIN_LABEL);
        basePanel.add(navBar, BorderLayout.NORTH);
        topBar.add(navButtons, BorderLayout.WEST);
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
        JLabel loginTitle = new JLabel(LOGIN_LABEL, SwingConstants.CENTER);
        loginTitle.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, loginTitle.getPreferredSize().height));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);

        rightPanel.add(loginTitle);

        loginTitle.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(loginTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Username and password fields
        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createVerticalStrut(10));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(PASSWORD_LABEL));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createVerticalStrut(10));

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    login();
                }
            }
        });

        showPasswordCheckBox = new JCheckBox(SHOW_PASSWORD_LABEL);
        showPasswordCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.setForeground(Color.GRAY);
        showPasswordCheckBox.addActionListener((ActionEvent e) -> {
            passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? (char) 0 : '\u2022');
        });
        rightPanel.add(showPasswordCheckBox);
        rightPanel.add(Box.createVerticalGlue());

        rightPanel.add(Box.createVerticalGlue());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setOpaque(false);

        /**
         * Handles the login button action.
         * If credentials are valid, opens the HomeWindow; otherwise, shows an error dialog.
         */
        StyledButton loginButton = new StyledButton(LOGIN_LABEL, Color.BLACK, Color.WHITE);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            login();
        });
        buttonsPanel.add(loginButton);
        buttonsPanel.add(Box.createVerticalStrut(15));

        // Label "Or"
        JLabel orLabel = new JLabel("Or", SwingConstants.CENTER);
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLabel.setForeground(Color.GRAY);
        buttonsPanel.add(orLabel);
        buttonsPanel.add(Box.createVerticalStrut(15));

        /**
         * Handles the sign up button action.
         * Opens the SignupWindow for new user registration.
         */
        StyledButton signupButton = new StyledButton(SIGNUP_LABEL, new Color(240, 240, 240), Color.BLACK);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.addActionListener(e -> {
            new SignupWindow();
            dispose();
        });
        buttonsPanel.add(signupButton);

        // Centered container for buttons
        JPanel centeredButtonsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centeredButtonsWrapper.setOpaque(false);
        centeredButtonsWrapper.add(buttonsPanel);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(centeredButtonsWrapper);

        pack();
        setVisible(true);
    }


    public void login() {
        if (checkFields(usernameField.getText(), passwordField)) {
            if (controller.logIn(usernameField.getText(), new String(passwordField.getPassword()))) {
                User user = controller.getCurrentUser();
                dispose();
                new HomeWindow(user);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            }
        }
    }


    /**
     * Checks if the username and password fields are filled.
     * Shows an error dialog if any field is empty.
     *
     * @param username The entered username.
     * @param password The password field.
     * @return true if both fields are filled, false otherwise.
     */
    public boolean checkFields(String username, JPasswordField password) {
        if (username.isEmpty() || password.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            return false;
        }
        return true;
    }

    /**
     * Shows the login window in a static, convenient way.
     */
    public static void showLogin() {
        SwingUtilities.invokeLater(LoginWindow::new);
    }

    /**
     * Main method to launch the login window.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        showLogin();
    }
}