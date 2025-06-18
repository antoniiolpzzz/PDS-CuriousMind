package com.pds.curiousmind.view.authentication.signup;

import javax.swing.*;
import java.awt.*;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.authentication.components.*;
import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.dashboard.HomeWindow;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.*;


public class SignupWindow extends JFrame {
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JCheckBox showPasswordBox1;
    private JCheckBox showPasswordBox2;
    private static final Controller controller = Controller.INSTANCE;

    public SignupWindow() {
        setTitle("CuriousMind - Sign up");
        setMinimumSize(new Dimension(1300, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        BackgroundPanel basePanel = new BackgroundPanel(BACKGROUND_IMAGE_PATH);
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel(APP_TITLE);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        topBar.add(titleLabel, BorderLayout.NORTH);
        topBar.add(Box.createHorizontalStrut(500));

        JLabel welcomeLabel = new JLabel(SIGNUP_WELCOME);
        welcomeLabel.setFont(new Font(FONT_NAME, Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topBar.add(welcomeLabel, BorderLayout.SOUTH);

        JPanel navButtons = new JPanel();
        navButtons.setOpaque(false);
        navButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        NavigationBar navBar = new NavigationBar(SIGNUP_LABEL);
        basePanel.add(navBar, BorderLayout.NORTH);
        topBar.add(navButtons, BorderLayout.CENTER);
        basePanel.add(topBar);

        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);
        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(370, 0));

        JLabel signupTitle = new JLabel(SIGNUP_LABEL);
        signupTitle.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        signupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(signupTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        fullNameField = new JTextField();
        fullNameField.setBorder(BorderFactory.createTitledBorder("Full Name"));
        fullNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(fullNameField);
        rightPanel.add(Box.createVerticalStrut(15));

        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createVerticalStrut(15));

        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(emailField);
        rightPanel.add(Box.createVerticalStrut(15));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(PASSWORD_LABEL));
        Dimension fixedFieldSize = new Dimension(160, 40);
        passwordField.setMaximumSize(fixedFieldSize);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        confirmPasswordField.setMaximumSize(fixedFieldSize);

        confirmPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    signUp();
                }
            }
        });

        JPanel passwordPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordField);
        passwordPanel.add(confirmPasswordField);

        showPasswordBox1 = new JCheckBox(SHOW_PASSWORD_LABEL);
        showPasswordBox1.setOpaque(false);
        showPasswordBox1.setForeground(Color.GRAY);
        showPasswordBox1.addActionListener(e ->
                passwordField.setEchoChar(showPasswordBox1.isSelected() ? (char) 0 : '\u2022'));

        showPasswordBox2 = new JCheckBox(SHOW_PASSWORD_LABEL);
        showPasswordBox2.setOpaque(false);
        showPasswordBox2.setForeground(Color.GRAY);
        showPasswordBox2.addActionListener(e ->
                confirmPasswordField.setEchoChar(showPasswordBox2.isSelected() ? (char) 0 : '\u2022'));

        passwordPanel.add(showPasswordBox1);
        passwordPanel.add(showPasswordBox2);
        rightPanel.add(passwordPanel);
        rightPanel.add(Box.createVerticalStrut(25));

        StyledButton createButton = new StyledButton("Create account", Color.BLACK, Color.WHITE);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> {
            signUp();
        });

        StyledButton backButton = new StyledButton(LOGIN_LABEL, new Color(240, 240, 240), Color.BLACK);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            dispose();
            new LoginWindow();
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setOpaque(false);

        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(createButton);
        buttonsPanel.add(Box.createVerticalStrut(15));

        JLabel orLabel = new JLabel("Or", SwingConstants.CENTER);
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLabel.setForeground(Color.GRAY);
        buttonsPanel.add(orLabel);
        buttonsPanel.add(Box.createVerticalStrut(15));

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(backButton);

        JPanel centeredButtonsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centeredButtonsWrapper.setOpaque(false);
        centeredButtonsWrapper.add(buttonsPanel);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(centeredButtonsWrapper);

        pack();
        setVisible(true);
    }

    public void signUp() {
        if (checkFields(fullNameField.getText(), usernameField.getText(), emailField.getText(), passwordField, confirmPasswordField)) {
            if (controller.signUp(
                    fullNameField.getText(),
                    usernameField.getText(),
                    emailField.getText(),
                    new String(passwordField.getPassword())
            )) {
                dispose();
                new LoginWindow();
            } else {
                JOptionPane.showMessageDialog(null, "Error creating account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            }
        }
    }

    public boolean checkFields(String fullName, String username, String email, JPasswordField password, JPasswordField confirmPassword) {
        if (fullName.isEmpty() || username.isEmpty()  || email.isEmpty() || password.getPassword().length == 0 || confirmPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            return false;
        }
        if (!new String(password.getPassword()).equals(new String(confirmPassword.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            return false;
        }
        if (!new String(password.getPassword()).matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            JOptionPane.showMessageDialog(null, "Incorrect Password, Password must have Capital letters, letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            return false;
        }
        if (!email.matches("^[^@\\s]+@[^@\\s]+$")) {
            JOptionPane.showMessageDialog(null, "The email format is name@domain.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
            return false;
        }


        return true;
    }
}