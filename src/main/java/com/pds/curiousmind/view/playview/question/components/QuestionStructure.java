package com.pds.curiousmind.view.playview.question.components;


import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static com.pds.curiousmind.view.playview.question.FillTheGaps.createGapSection;
import static com.pds.curiousmind.view.playview.question.FlashCard.createFlashCard;
import static com.pds.curiousmind.view.playview.question.Test.createTestPanel;
import static com.pds.curiousmind.view.playview.question.Translation.createTranslationSection;
import static com.pds.curiousmind.view.playview.question.components.CreateHeader.createHeader;
import static com.pds.curiousmind.view.playview.question.components.QuestionBackground.createBackground;


public class QuestionStructure extends JFrame {

    //TODO: This should receive only the content block.
    public QuestionStructure(String title, String iconPath, String indication, String statement, String type) {
        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // BACKGROUND PANEL
        JPanel basePanel = createBackground(title,iconPath);
        setContentPane(basePanel);

        // RIGHT PANEL
        // Panel blanco directamente en la derecha
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightPanel.setPreferredSize(new Dimension(950, 0));
        basePanel.add(rightPanel, BorderLayout.EAST);

        // COMMON HEADER
        rightPanel.add(createHeader(title, iconPath, indication, statement));

        //Gap for the user to fill with the answer
        switch (type) {
            case "FillTheGaps" -> {
                JTextField answerField = createGapSection();
                rightPanel.add(answerField);
            }
            case "Translation" -> {
                JTextField answerField = createTranslationSection();
                rightPanel.add(answerField);
            }
            case "FlashCard" -> {
                //TODO: FlashCard should receive the options of the question.
                java.util.List<String> options = java.util.Arrays.asList("Option 1", "Option 2", "Option 3");
                JPanel flashCardPanel = createFlashCard(options);
                rightPanel.add(flashCardPanel);
            }
            case "Test" -> {
                //TODO: Test should receive the options of the question.
                java.util.List<String> options = java.util.Arrays.asList("Option 1", "Option 2", "Option 3");
                JPanel testPanel = createTestPanel(options);
                rightPanel.add(testPanel);
            }
            case null, default -> {
                JOptionPane.showMessageDialog(this, "Unknown question type: " + type, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Añade espacio flexible antes del botón para empujarlo hacia abajo
        rightPanel.add(Box.createVerticalGlue());

        StyledButton submitButton = new StyledButton("Submit", Color.BLACK, Color.WHITE);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            //TODO: Handle the submission logic here
            JOptionPane.showMessageDialog(this, "Test submitted successfully!");
        });
        rightPanel.add(submitButton);

        // Espacio inferior para que no toque el borde
        rightPanel.add(Box.createVerticalStrut(10));


        setVisible(true);
    }

    private int getProgressForCourse() {
        //TODO: Recuperate the progress of the content block related with the number of questions answered
        return 30; // Example value, this should be dynamic based on actual progress

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new com.pds.curiousmind.view.playview.question.components.QuestionStructure(
                "German",
                "icons/course/german.png",
                "Chose the correct answer",
                "Witch is the onion?",
                "Test" // Change this to "Translation", "FlashCard", or "Test" to test other types
        ));
    }



}
