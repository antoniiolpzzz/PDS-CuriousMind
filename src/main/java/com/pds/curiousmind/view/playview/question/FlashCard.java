package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.model.question.option.ImageOption;
import com.pds.curiousmind.model.question.option.Option;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import static com.pds.curiousmind.view.common.ImageButton.createImageButton;

public class FlashCard {

    public static class FlashCardResult {
        public final JPanel panel;
        private final String[] selectedLabel;

        public FlashCardResult(JPanel panel, String[] selectedLabel) {
            this.panel = panel;
            this.selectedLabel = selectedLabel;
        }

        public String getAnswer() {
            return selectedLabel[0];
        }
    }

    public static FlashCardResult createFlashCard(List<Option> options) {
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        cardPanel.setOpaque(false);

        final String[] selectedLabel = {null};
        final ArrayList<JButton> cardButtons = new ArrayList<>();

        for (Option option : options) {
            String label = option.getLabel();
            String imgUrl = (option instanceof ImageOption imageOption) ? imageOption.getImageURL() : "icons/button/history.jpg";
            JPanel buttonPanel = createImageButton(label, imgUrl, selectedLabel, cardButtons, true);
            cardPanel.add(buttonPanel);
        }

        // Añadir listeners para resaltar el botón seleccionado
        for (JButton button : cardButtons) {
            button.addActionListener(e -> {
                selectedLabel[0] = button.getText();
                for (JButton btn : cardButtons) {
                    btn.setBackground(Color.WHITE);
                }
                button.setBackground(new Color(230, 250, 255));
            });
        }

        return new FlashCardResult(cardPanel, selectedLabel);
    }
}