package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.view.authentication.login.LoginWindow;
import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;
import static com.pds.curiousmind.view.home.components.SectionTitle.sectionTitle;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.playview.question.components.CreateHeader.createHeader;
import static com.pds.curiousmind.view.playview.question.components.QuestionBackground.createBackground;


public class Translation extends JFrame {

    //TODO: This should receive only the content block.
    public Translation(String title, String iconPath, String indication, String statement, java.util.List<String> options) {
        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // BACKGROUND PANEL
        JPanel basePanel = createBackground(title,iconPath);
        setContentPane(basePanel);

        // RIGHT PANEL
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

        // COMMON HEADER
        rightPanel.add(createHeader(title, iconPath, indication, statement));

        //TODO: show the options to translate the statement

        // SUBMIT BUTTON
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Translation(
                "German",
                "icons/course/german.png",
                "Choose the correct translation for the following statement:",
                "Wenn ich morgens aufstehe, frühstücke ich Kaffee und Kekse",
                Arrays.asList("A.   Kuchen", "B.   Kartoffelchips", "C.   Kopfsalat", "D.   Lachs")
        ));
    }


}
