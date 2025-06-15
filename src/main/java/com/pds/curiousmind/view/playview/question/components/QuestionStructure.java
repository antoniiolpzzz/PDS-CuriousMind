package com.pds.curiousmind.view.playview.question.components;


import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.playview.question.FillTheGaps;
import com.pds.curiousmind.view.playview.question.FlashCard;
import com.pds.curiousmind.view.playview.question.Test;
import com.pds.curiousmind.view.playview.question.Translation;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.playview.question.FillTheGaps.createGapSection;
import static com.pds.curiousmind.view.playview.question.FlashCard.createFlashCard;
import static com.pds.curiousmind.view.playview.question.Test.createTestPanel;
import static com.pds.curiousmind.view.playview.question.Translation.createTranslationSection;
import static com.pds.curiousmind.view.playview.question.components.CreateHeader.createHeader;
import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static java.sql.DriverManager.println;


public class QuestionStructure extends JFrame {

    private FillTheGaps.GapSectionResult gapResult;
    private Translation.TranslationSectionResult translationResult;
    private FlashCard.FlashCardResult flashCardResult;
    private Test.TestPanelResult testResult;

    //private final Controller controller = Controller.INSTANCE;

    //TODO: This should receive a the atributes of the question and the current course
    public QuestionStructure(String title, String iconPath, String indication, String statement, String type) {


        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(1300, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        // BACKGROUND PANEL
        JPanel basePanel =  createBackground(this, title,iconPath, "exit");
        setContentPane(basePanel);

        // RIGHT PANEL
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
                gapResult = FillTheGaps.createGapSection();
                rightPanel.add(gapResult.panel);
            }
            case "Translation" -> {
                translationResult = Translation.createTranslationSection();
                rightPanel.add(translationResult.panel);
            }
            case "FlashCard" -> {
                java.util.List<String> options = java.util.Arrays.asList("Option 1", "Option 2", "Option 3");
                flashCardResult = FlashCard.createFlashCard(options);
                rightPanel.add(flashCardResult.panel);
            }
            case "Test" -> {
                java.util.List<String> options = java.util.Arrays.asList("Option 1", "Option 2", "Option 3");
                testResult = Test.createTestPanel(options);
                rightPanel.add(testResult.panel);
            }
            case null, default -> {
                JOptionPane.showMessageDialog(null, "Unknown question type: " + type, "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
                return;
            }
        }

        //SUBMIT BUTTON

        rightPanel.add(Box.createVerticalGlue());
        StyledButton submitButton = new StyledButton("Submit", Color.BLACK, Color.WHITE);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String submittedAnswer = "";
            switch (type) {
                case "FillTheGaps" -> submittedAnswer = gapResult != null ? gapResult.getAnswer() : "";
                case "Translation" -> submittedAnswer = translationResult != null ? translationResult.getAnswer() : "";
                case "FlashCard" -> submittedAnswer = flashCardResult != null ? flashCardResult.getAnswer() : "";
                case "Test" -> submittedAnswer = testResult != null ? testResult.getAnswer() : "";
            }
            System.out.println("Answer submitted: " + submittedAnswer);
            /*
            if (controller.validateAnswer(question, submittedAnswer)) {
                JOptionPane.showMessageDialog(null, "Correct answer!", "Success", JOptionPane.INFORMATION_MESSAGE, loadIcon("icons/pet/feliz.png", 60, 60));
                dispose();
                Question question = controller.getNextQuestion();
                if (question == null) {
                    controller.endGame();
                    JOptionPane.showMessageDialog(null, "Congratulations! You have completed the content block.", "Game Over", JOptionPane.INFORMATION_MESSAGE, loadIcon("icons/pet/feliz.png", 60, 60));
                    controller.addExperiencePoints(PUNTOS);
                    new DashboardView(course);
                    dispose();
                }
                new QuestionStructure(
                        curse,
                        question.getIndication(),
                        question.getStatement(),
                        question.getType()
                );
            } else {
                //TODO: controller.addFailedQuestion(question);
                JOptionPane.showMessageDialog(null, "Incorrect answer. Try again.", "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
                dispose();
                Question question = controller.getNextQuestion();
                new QuestionStructure(
                        curse,
                        question.getIndication(),
                        question.getStatement(),
                        question.getType()
                );
            }
            */

        });
        rightPanel.add(submitButton);

        // Espacio inferior para que no toque el borde
        rightPanel.add(Box.createVerticalStrut(10));


        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new com.pds.curiousmind.view.playview.question.components.QuestionStructure(
                "German",
                "icons/course/german.png",
                "Chose the correct answer",
                "Witch is the onion?",
                "Translation"
        ));
    }



}
