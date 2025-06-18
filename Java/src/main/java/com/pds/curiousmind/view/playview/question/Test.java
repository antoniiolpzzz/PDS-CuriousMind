package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import static com.pds.curiousmind.view.common.GlobalConstants.*;

/**
 * This class provides functionality to generate a test panel where users can select
 * one answer from multiple options using toggle buttons.
 */
public class Test extends JFrame {

    // *****************************************************************************************
    // **************************** TEST FUNCTIONS **************************** //
    // *****************************************************************************************

    /**
     * Encapsulates the result of generating the test panel, including the
     * rendered panel and the selected answer.
     */
    public static class TestPanelResult {
        /** The panel containing all the option buttons. */
        public final JPanel panel;

        /** Array used to store the selected option (first element only is used). */
        private final String[] selectedOption;

        /**
         * Constructs a TestPanelResult with the given panel and selected option reference.
         *
         * @param panel the UI panel containing the options
         * @param selectedOption a single-element array holding the selected answer
         */
        public TestPanelResult(JPanel panel, String[] selectedOption) {
            this.panel = panel;
            this.selectedOption = selectedOption;
        }

        /**
         * Retrieves the selected answer from the test panel.
         *
         * @return the label of the selected option, or {@code null} if none is selected
         */
        public String getAnswer() {
            return selectedOption[0];
        }
    }

    /**
     * Creates a panel with multiple choice options where only one can be selected at a time.
     * The options are rendered as toggle buttons.
     *
     * @param options a list of {@link Option} objects representing the possible answers
     * @return a {@link TestPanelResult} containing the panel and the selected answer reference
     */
    public static TestPanelResult createTestPanel(List<Option> options) {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);

        ButtonGroup group = new ButtonGroup();  // Ensures only one toggle can be selected at a time
        final String[] selectedOption = {null}; // Stores the selected answer

        for (Option option : options) {
            JToggleButton optionButton = new JToggleButton(option.getLabel());
            optionButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
            optionButton.setBackground(new Color(245, 245, 245));
            optionButton.setHorizontalAlignment(SwingConstants.LEFT);
            optionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            optionButton.setPreferredSize(new Dimension(830, 40));

            group.add(optionButton);
            optionsPanel.add(optionButton);

            // When this button is selected, update the selected option
            optionButton.addActionListener(e -> selectedOption[0] = option.getLabel());
        }

        return new TestPanelResult(optionsPanel, selectedOption);
    }
}
