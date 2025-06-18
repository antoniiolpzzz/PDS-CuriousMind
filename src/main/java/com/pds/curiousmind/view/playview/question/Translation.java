package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.model.question.option.Option;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.pds.curiousmind.view.common.GlobalConstants.*;

/**
 * This class provides utility methods to create interactive UI components
 * for translation-based questions. It allows users to select words to form a translated sentence.
 */
public class Translation {

    // *****************************************************************************************
    // **************************** TRANSLATION FUNCTIONS **************************************
    // *****************************************************************************************

    /**
     * Encapsulates the result of creating a translation section, including
     * the UI panel and the list of selected words.
     */
    public static class TranslationSectionResult {
        /** The main panel containing the translation UI components. */
        public final JPanel panel;

        /** List of words selected by the user to form the translation. */
        private final List<String> selectedWords;

        /**
         * Constructs a TranslationSectionResult with the specified panel and selected words.
         *
         * @param panel the UI panel containing the translation section
         * @param selectedWords the list of words selected by the user
         */
        public TranslationSectionResult(JPanel panel, List<String> selectedWords) {
            this.panel = panel;
            this.selectedWords = selectedWords;
        }

        /**
         * Returns the final user-constructed answer by joining the selected words with spaces.
         *
         * @return the user's translation as a single string
         */
        public String getAnswer() {
            return String.join(" ", selectedWords);
        }
    }

    /**
     * Creates a new translation section UI component where users can select words
     * from a randomized list to form a sentence.
     *
     * @param options the list of {@link Option} objects representing possible word choices
     * @return a {@link TranslationSectionResult} containing the panel and selected words
     */
    public static TranslationSectionResult createTranslationSection(List<Option> options) {

        JPanel translationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        translationPanel.setOpaque(false);

        JPanel answerInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        answerInnerPanel.setBackground(new Color(240, 240, 240));
        answerInnerPanel.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(answerInnerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(830, 60));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        translationPanel.add(scrollPane);

        // Shuffle options to randomize word order
        List<Option> shuffledOptions = new ArrayList<>(options);
        Collections.shuffle(shuffledOptions);

        List<String> wordOptions = shuffledOptions.stream()
                .map(Option::getLabel)
                .toList();

        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        optionsPanel.setOpaque(false);
        optionsPanel.setPreferredSize(new Dimension(830, 100));

        List<JButton> optionButtons = new ArrayList<>();
        List<String> selectedWords = new ArrayList<>();

        // Create buttons for each word option
        for (String word : wordOptions) {
            JButton wordButton = new JButton(word);
            wordButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
            wordButton.setFocusPainted(false);
            wordButton.setBackground(Color.WHITE);
            wordButton.setForeground(Color.BLACK);
            wordButton.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
            wordButton.setPreferredSize(new Dimension(90, 35));

            optionButtons.add(wordButton);

            // Handle word selection
            wordButton.addActionListener(e -> {
                wordButton.setVisible(false);

                JButton selectedButton = new JButton(word);
                selectedButton.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
                selectedButton.setFocusPainted(false);
                selectedButton.setBackground(new Color(220, 220, 220));
                selectedButton.setForeground(Color.BLACK);
                selectedButton.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 1));
                selectedButton.setPreferredSize(new Dimension(90, 35));

                selectedWords.add(word);

                // Handle deselection of a word
                selectedButton.addActionListener(evt -> {
                    answerInnerPanel.remove(selectedButton);
                    wordButton.setVisible(true);
                    selectedWords.remove(word);
                    answerInnerPanel.revalidate();
                    answerInnerPanel.repaint();
                });

                answerInnerPanel.add(selectedButton);
                answerInnerPanel.revalidate();
                answerInnerPanel.repaint();
            });

            optionsPanel.add(wordButton);
        }

        translationPanel.add(optionsPanel);

        return new TranslationSectionResult(translationPanel, selectedWords);
    }
}
