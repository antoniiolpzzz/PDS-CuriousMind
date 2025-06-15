package com.pds.curiousmind.view.playview.question.components;


import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.implementation.FillTheGap;
import com.pds.curiousmind.model.question.option.ImageOption;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.course.CourseDashboard;
import com.pds.curiousmind.view.playview.question.FillTheGaps;
import com.pds.curiousmind.view.playview.question.FlashCard;
import com.pds.curiousmind.view.playview.question.Test;
import com.pds.curiousmind.view.playview.question.Translation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.playview.question.FillTheGaps.createGapSection;
import static com.pds.curiousmind.view.playview.question.FlashCard.createFlashCard;
import static com.pds.curiousmind.view.playview.question.Test.createTestPanel;
import static com.pds.curiousmind.view.playview.question.Translation.createTranslationSection;
import static com.pds.curiousmind.view.playview.question.components.CreateHeader.createHeader;
import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;
import static java.sql.DriverManager.println;


public class QuestionStructure extends JFrame {


    private static final int PUNTOS = 100;  //TODO: The points should depend on the content block difficulty

    private FillTheGaps.GapSectionResult gapResult;
    private Translation.TranslationSectionResult translationResult;
    private FlashCard.FlashCardResult flashCardResult;
    private Test.TestPanelResult testResult;
    private static final Controller controller = Controller.INSTANCE;

    public QuestionStructure(RegisteredCourse course, Question question) {

        String indication = question.getIndication();
        String statement = question.getStatement();
        List<Option> options = question.getOptions();
        String type = question.getType();


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
        JPanel basePanel =  createBackground(this, null,course, "exit");
        setContentPane(basePanel);

        // RIGHT PANEL
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightPanel.setPreferredSize(new Dimension(950, 0));
        basePanel.add(rightPanel, BorderLayout.EAST);

        // COMMON HEADER
        rightPanel.add(createHeader(course.getName(), course.getImageURL(), indication, statement));

        //Gap for the user to fill with the answer
        switch (type) {
            case "FillTheGap" -> {
                gapResult = FillTheGaps.createGapSection();
                rightPanel.add(gapResult.panel);
            }
            case "Translate" -> {
                translationResult = createTranslationSection(options);
                rightPanel.add(translationResult.panel);
            }
            case "FlashCard" -> {
                options.forEach(option -> {
                    if (option instanceof ImageOption imageOption) {
                        System.out.println("Option: " + imageOption.getLabel() + ", Image: " + imageOption.getImageURL());
                    } else {
                        System.out.println("Option: " + option.getLabel() + ", Image: none");
                    }
                });
                flashCardResult = createFlashCard(options);
                rightPanel.add(flashCardResult.panel);
            }
            case "Test" -> {
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
                case "FillTheGap" -> submittedAnswer = gapResult != null ? gapResult.getAnswer() : "";
                case "Translate" -> submittedAnswer = translationResult != null ? translationResult.getAnswer() : "";
                case "FlashCard" -> submittedAnswer = flashCardResult != null ? flashCardResult.getAnswer() : "";
                case "Test" -> submittedAnswer = testResult != null ? testResult.getAnswer() : "";
            }
            System.out.println("Answer submitted: " + submittedAnswer);

            if (controller.validateAnswer(question, submittedAnswer)) {
                JOptionPane.showMessageDialog(null, "Correct answer!", "Success", JOptionPane.INFORMATION_MESSAGE, loadIcon("icons/pet/feliz.png", 60, 60));
                dispose();
                Question nextQuestion = controller.getNextQuestion();
                if (nextQuestion == null) {
                    controller.endGame();
                    JOptionPane.showMessageDialog(null, "Congratulations! You have completed the content block.", "Game Over", JOptionPane.INFORMATION_MESSAGE, loadIcon("icons/pet/feliz.png", 60, 60));
                    controller.addExperiencePoints(PUNTOS);
                    new CourseDashboard(course);
                    dispose();
                }
                assert nextQuestion != null;
                new QuestionStructure(
                        course,
                        nextQuestion
                );
            } else {
                controller.addFailedQuestion(question);
                JOptionPane.showMessageDialog(null, "Incorrect answer. The correct answer was: " + question.getCorrectAnswer(), "Error", JOptionPane.ERROR_MESSAGE, loadIcon("icons/pet/enfadado.png", 60, 60));
                dispose();
                Question nextQuestion = controller.getNextQuestion();
                new QuestionStructure(
                        course,
                        nextQuestion
                );
            }


        });
        rightPanel.add(submitButton);

        // Espacio inferior para que no toque el borde
        rightPanel.add(Box.createVerticalStrut(10));


        setVisible(true);
    }

    public static void main(String[] args) {

        RegisteredCourse course = controller.getCurrentRegisteredCourse();

        //Crear una lista de tipo Option para la prueba

        Question question = new FillTheGap(
                "Complete the sentence:",
                "The ___ is the satellite of the Earth.",
                "moon"
        );


        SwingUtilities.invokeLater(() -> new com.pds.curiousmind.view.playview.question.components.QuestionStructure(
                course,
                question
        ));
    }



}
