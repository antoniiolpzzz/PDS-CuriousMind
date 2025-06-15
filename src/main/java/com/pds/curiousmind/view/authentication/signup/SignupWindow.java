package com.pds.curiousmind.view.authentication.signup;

import javax.swing.*;
import java.awt.*;

import com.pds.curiousmind.view.authentication.components.*;
import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;

import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

public class SignupWindow extends JFrame {
    private JTextField fullNameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox showPasswordBox1, showPasswordBox2;

    public SignupWindow() {
        // Configuración de la ventana
        setTitle("CuriousMind - Sign up");
        setMinimumSize(new Dimension(1300, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        BackgroundPanel basePanel = new BackgroundPanel("icons/background/background.jpg");
        basePanel.setLayout(new BorderLayout());
        setContentPane(basePanel);

        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("CuriousMind");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        topBar.add(titleLabel, BorderLayout.NORTH);
        topBar.add(Box.createHorizontalStrut(500));

        JLabel welcomeLabel = new JLabel("WELCOME!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topBar.add(welcomeLabel, BorderLayout.SOUTH);

        JPanel navButtons = new JPanel();
        navButtons.setOpaque(false);
        navButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        NavigationBar navBar = new NavigationBar("Sign up");
        basePanel.add(navBar, BorderLayout.NORTH);
        topBar.add(navButtons, BorderLayout.CENTER);
        basePanel.add(topBar);

        // Panel derecho para el formulario de registro
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        // Panel redondeado para el formulario
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);
        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(370, 0));

        // Título del formulario de registro
        JLabel signupTitle = new JLabel("Sign up");
        signupTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        signupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(signupTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Campos del formulario
        fullNameField = new JTextField();
        fullNameField.setBorder(BorderFactory.createTitledBorder("Full Name"));
        fullNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(fullNameField);
        rightPanel.add(Box.createVerticalStrut(15));

        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightPanel.add(emailField);
        rightPanel.add(Box.createVerticalStrut(15));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        Dimension fixedFieldSize = new Dimension(160, 40);
        passwordField.setMaximumSize(fixedFieldSize);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        confirmPasswordField.setMaximumSize(fixedFieldSize);

        JPanel passwordPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordField);
        passwordPanel.add(confirmPasswordField);

        // Panel de opciones para mostrar la contraseña
        showPasswordBox1 = new JCheckBox("Show password");
        showPasswordBox1.setOpaque(false);
        showPasswordBox1.setForeground(Color.GRAY);
        showPasswordBox1.addActionListener(e ->
                passwordField.setEchoChar(showPasswordBox1.isSelected() ? (char) 0 : '\u2022'));

        showPasswordBox2 = new JCheckBox("Show password");
        showPasswordBox2.setOpaque(false);
        showPasswordBox2.setForeground(Color.GRAY);
        showPasswordBox2.addActionListener(e ->
                confirmPasswordField.setEchoChar(showPasswordBox2.isSelected() ? (char) 0 : '\u2022'));

        passwordPanel.add(showPasswordBox1);
        passwordPanel.add(showPasswordBox2);
        rightPanel.add(passwordPanel);
        rightPanel.add(Box.createVerticalStrut(25));

        // Botón de creación de cuenta
        StyledButton createButton = new StyledButton("Create account", Color.BLACK, Color.WHITE);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> {
            if (checkFields(fullNameField.getText(), emailField.getText(), passwordField, confirmPasswordField))
            {
                //TODO: Controller logic to handle signup
                // if (controller.signup(fullNameField.getText(), emailField.getText(), passwordField.getPassword())) {
                {
                    dispose();
                    new LoginWindow();
                }

            }
        });

        // Botón de inicio de sesión
        StyledButton backButton = new StyledButton("Log in", new Color(240, 240, 240), Color.BLACK);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            dispose();
            new LoginWindow();
        });


        // Panel vertical con botones y "Or"
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setOpaque(false);

// Botón crear cuenta
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(createButton);
        buttonsPanel.add(Box.createVerticalStrut(15));

// Label "Or"
        JLabel orLabel = new JLabel("Or", SwingConstants.CENTER);
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLabel.setForeground(Color.GRAY);
        buttonsPanel.add(orLabel);
        buttonsPanel.add(Box.createVerticalStrut(15));

// Botón volver al login
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(backButton);

// Contenedor centrado
        JPanel centeredButtonsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centeredButtonsWrapper.setOpaque(false);
        centeredButtonsWrapper.add(buttonsPanel);

// Añadir glue para empujar hacia abajo y después los botones
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(centeredButtonsWrapper);


        pack();
        setVisible(true);
    }


    public boolean checkFields(String fullName, String email, JPasswordField password, JPasswordField confirmPassword) {
        if (fullName.isEmpty() || email.isEmpty() || password.getPassword().length == 0 || confirmPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
            return false;
        }
        if (!new String(password.getPassword()).equals(new String(confirmPassword.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupWindow::new);
    }

}
