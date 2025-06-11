package com.pds.curiousmind.view.authentication.login;

import com.pds.curiousmind.view.authentication.signup.SignupWindow;
import com.pds.curiousmind.view.authentication.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;

    public LoginWindow() {
        setTitle("CuriousMind - Log in");
        setMinimumSize(new Dimension(1200, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        BackgroundPanel basePanel = new BackgroundPanel("icons/background/login.jpg");
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

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

        JLabel loginTitle = new JLabel("Log in");
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(loginTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username or email"));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createVerticalStrut(20));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createVerticalStrut(10));

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
        rightPanel.add(Box.createVerticalStrut(25));

        StyledButton loginButton = new StyledButton("Log in", Color.BLACK, Color.WHITE);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            if (checkFields(usernameField.getText(), passwordField))
            {
                //TODO: Controler check password and username
                //if(controler.checkFields(usernameField,passwordField))
                {
                    dispose();
                    //new HomeWindow();
                }

            }

        });
        rightPanel.add(loginButton);

        rightPanel.add(Box.createVerticalStrut(15));
        JLabel orLabel = new JLabel("Or", SwingConstants.CENTER);
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLabel.setForeground(Color.GRAY);
        rightPanel.add(orLabel);
        rightPanel.add(Box.createVerticalStrut(15));

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }

    public boolean checkFields(String username, JPasswordField password) {
        if (username.isEmpty() || password.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
