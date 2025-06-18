package com.pds.curiousmind.view.playview.question.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.implementation.FillTheGap;
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
import java.util.Objects;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;
import static com.pds.curiousmind.view.playview.question.FlashCard.createFlashCard;
import static com.pds.curiousmind.view.playview.question.components.CreateHeader.createHeader;
import static com.pds.curiousmind.view.common.BackgroundComponent.createBackground;

/**
 * A JFrame that displays and manages different types of questions (Fill the Gap,
 * Translation, FlashCard, Test) for a given course and difficulty level.
 *
 * It handles rendering the UI, capturing user input, validating answers,
 * and navigating between questions or completing the game.
 */
public class QuestionStructure extends JFrame {

    private FillTheGaps.GapSectionResult gapResult;
    private Translation.TranslationSectionResult translationResult;
    private FlashCard.FlashCardResult flashCardResult;
    private Test.TestPanelResult testResult;
    private static final Controller controller = Controller.INSTANCE;

    /**
     * Constructs and initializes the question view based on the question type.
     *
     * @param course the registered course the user is currently playing
     * @param question the current question to be displayed
     * @param blockName the name of the current content block
     * @param difficulty the difficulty level of the current content block
     */
    public QuestionStructure(RegisteredCourse course, Question question, String blockName, Difficulty difficulty) {

        String indication = question.getIndication();
        String statement = question.getStatement();
        List<Option> options = question.getOptions();
        String type = question.getType();

        setTitle("CuriousMind - Home");
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Ensure the window regains focus
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        // Create and apply background panel
        JPanel basePanel = createBackground(this, null, course, "exit");
        setContentPane(basePanel);

        // Main question display area
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightPanel.setPreferredSize(new Dimension(950, 0));
        basePanel.add(rightPanel, BorderLayout.EAST);

        // Header with instruction, statement, and block name
        rightPanel.add(createHeader(course, indication, statement, blockName));

        // Dynamically add the question UI depending on type
        switch (type) {
            case "FillTheGap" -> {
                gapResult = FillTheGaps.createGapSection();
                rightPanel.add(gapResult.panel);
            }
            case "Translate" -> {
                translationResult = Translation.createTranslationSection(options);
                rightPanel.add(translationResult.panel);
            }
            case "FlashCard" -> {
                flashCardResult = createFlashCard(options);
                rightPanel.add(flashCardResult.panel);
            }
            case "Test" -> {
                testResult = Test.createTestPanel(options);
                rightPanel.add(testResult.panel);
            }
            case null, default -> {
                JOptionPane.showMessageDialog(null, "Unknown question type: " + type,
                        "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_ANGRY, 60, 60));
                return;
            }
        }

        // Submit button to validate answer
        rightPanel.add(Box.createVerticalGlue());
        StyledButton submitButton = new StyledButton(SUBMIT_LABEL, Color.BLACK, Color.WHITE);
        submitButton.setFont(new Font(FONT_NAME, Font.BOLD, 18));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton.addActionListener(e -> {
            String submittedAnswer = "";

            switch (type) {
                case "FillTheGap" -> submittedAnswer = gapResult != null ? gapResult.getAnswer() : "";
                case "Translate" -> submittedAnswer = translationResult != null ? translationResult.getAnswer() : "";
                case "FlashCard" -> submittedAnswer = flashCardResult != null ? flashCardResult.getAnswer() : "";
                case "Test" -> submittedAnswer = testResult != null ? testResult.getAnswer() : "";
            }

            // If answer is provided, validate it
            if (!Objects.equals(submittedAnswer, "")) {
                if (controller.validateAnswer(question, submittedAnswer)) {
                    JOptionPane.showMessageDialog(null, "Correct answer!", "Success",
                            JOptionPane.INFORMATION_MESSAGE, loadIcon(ICON_HAPPY, 60, 60));
                    Question nextQuestion = controller.getNextQuestion();

                    if (nextQuestion == null) {
                        controller.completeContentBlock(difficulty);
                        controller.endGame();
                        JOptionPane.showMessageDialog(null, "Congratulations! You have completed the content block.",
                                "Game Over", JOptionPane.INFORMATION_MESSAGE, loadIcon(ICON_COMPLETE, 60, 60));
                        new CourseDashboard(course);
                        dispose();
                    } else {
                        dispose();
                        new QuestionStructure(course, nextQuestion, blockName, difficulty);
                    }

                } else {
                    controller.addFailedQuestion(question);
                    JOptionPane.showMessageDialog(null,
                            "Incorrect answer. The correct answer was: " + question.getCorrectAnswer(),
                            "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_FAIL, 60, 60));
                    Question nextQuestion = controller.getNextQuestion();

                    if (nextQuestion == null) {
                        controller.endGame();
                        JOptionPane.showMessageDialog(null, "Oups! You don't have lives left.",
                                "Game Over", JOptionPane.INFORMATION_MESSAGE, loadIcon(ICON_FAIL, 60, 60));
                        new CourseDashboard(course);
                        dispose();
                    } else {
                        dispose();
                        new QuestionStructure(course, nextQuestion, blockName, difficulty);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select an option.",
                        "Error", JOptionPane.ERROR_MESSAGE, loadIcon(ICON_FAIL, 60, 60));
            }
        });

        rightPanel.add(submitButton);
        rightPanel.add(Box.createVerticalStrut(10));

        setVisible(true);
    }
}
