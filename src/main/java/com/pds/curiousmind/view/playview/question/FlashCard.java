package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.model.question.option.ImageOption;
import com.pds.curiousmind.model.question.option.Option;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.ImageButton.createImageButton;

/**
 * This class provides the functionality to create an interactive flashcard-based
 * selection panel, where users choose one option represented by an image and label.
 */
public class FlashCard {

    /**
     * Encapsulates the result of creating the flashcard panel, including
     * the UI component and the selected answer.
     */
    public static class FlashCardResult {
        /** The panel containing the image-based option buttons. */
        public final JPanel panel;

        /** An array storing the label of the selected flashcard. */
        private final String[] selectedLabel;

        /**
         * Constructs a FlashCardResult with the given panel and selection reference.
         *
         * @param panel the panel containing flashcard options
         * @param selectedLabel an array holding the label of the selected option
         */
        public FlashCardResult(JPanel panel, String[] selectedLabel) {
            this.panel = panel;
            this.selectedLabel = selectedLabel;
        }

        /**
         * Retrieves the selected label from the flashcard panel.
         *
         * @return the label of the selected option, or {@code null} if none is selected
         */
        public String getAnswer() {
            return selectedLabel[0];
        }
    }

    /**
     * Creates a flashcard-style panel where each option is displayed as an image with a label.
     * Users can select one of the cards, and the selection is visually highlighted.
     *
     * @param options a list of {@link Option} instances; may include {@link ImageOption} for image display
     * @return a {@link FlashCardResult} containing the UI panel and the selected label reference
     */
    public static FlashCardResult createFlashCard(List<Option> options) {
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        cardPanel.setOpaque(false);

        final String[] selectedLabel = {null};  // Holds the currently selected option label
        final ArrayList<JButton> cardButtons = new ArrayList<>();  // Stores card buttons for UI updates

        // Create a button for each option, with optional image if available
        for (Option option : options) {
            String label = option.getLabel();
            String imgUrl = (option instanceof ImageOption imageOption) ? imageOption.getImageURL() : ICON_FAIL;
            JPanel buttonPanel = createImageButton(label, imgUrl, selectedLabel, cardButtons, true);
            cardPanel.add(buttonPanel);
        }

        // Add listeners to highlight selected card and update selection
        for (JButton button : cardButtons) {
            button.addActionListener(e -> {
                selectedLabel[0] = button.getText();
                for (JButton btn : cardButtons) {
                    btn.setBackground(Color.WHITE);  // Reset all buttons
                }
                button.setBackground(new Color(230, 250, 255));  // Highlight selected button
            });
        }

        return new FlashCardResult(cardPanel, selectedLabel);
    }
}
