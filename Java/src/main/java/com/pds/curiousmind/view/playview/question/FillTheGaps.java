package com.pds.curiousmind.view.playview.question;

import javax.swing.*;
import java.awt.*;

/**
 * This class provides UI components for "Fill in the Gaps" type questions,
 * where users are required to enter a short free-text answer into a text field.
 */
public class FillTheGaps {

    // *****************************************************************************************
    // **************************** FILL THE GAPS FUNCTIONS ************************************
    // *****************************************************************************************

    /**
     * Encapsulates the result of generating the gap section, including
     * the UI panel and the text field containing the user's answer.
     */
    public static class GapSectionResult {
        /** The panel containing the input field for the gap question. */
        public final JPanel panel;

        /** The text field where the user types their answer. */
        public final JTextField answerField;

        /**
         * Constructs a GapSectionResult with the specified panel and answer field.
         *
         * @param panel the panel that holds the input field
         * @param answerField the text field for user input
         */
        public GapSectionResult(JPanel panel, JTextField answerField) {
            this.panel = panel;
            this.answerField = answerField;
        }

        /**
         * Retrieves the text entered by the user.
         *
         * @return the input string from the text field
         */
        public String getAnswer() {
            return answerField.getText();
        }
    }

    /**
     * Creates a gap section with a single text input field for the user to type an answer.
     *
     * @return a {@link GapSectionResult} containing the UI panel and the input field
     */
    public static GapSectionResult createGapSection() {
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        optionsPanel.setOpaque(false);

        JTextField answerField = new JTextField();
        answerField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        answerField.setPreferredSize(new Dimension(830, 40));

        optionsPanel.add(answerField);

        return new GapSectionResult(optionsPanel, answerField);
    }
}
