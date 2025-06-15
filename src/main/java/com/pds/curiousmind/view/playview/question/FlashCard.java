package com.pds.curiousmind.view.playview.question;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import static com.pds.curiousmind.view.common.ImageButton.createImageButton;

public class FlashCard extends JFrame {

    public static class FlashCardResult {
        public final JPanel panel;
        private final String[] selectedCard;

        public FlashCardResult(JPanel panel, String[] selectedCard) {
            this.panel = panel;
            this.selectedCard = selectedCard;
        }

        public String getAnswer() {
            return selectedCard[0];
        }
    }

    public static FlashCardResult createFlashCard(List<String> options) {
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        cardPanel.setOpaque(false);

        final String[] selectedCard = {null};
        final List<JButton> cardButtons = new ArrayList<>();

        // TODO: Usa las opciones para crear los botones dinámicamente
        cardPanel.add(createImageButton("Zwiebel", "icons/course/cebolla.png", selectedCard, cardButtons, true));
        cardPanel.add(createImageButton("Karotte", "icons/course/zanahoria.png", selectedCard, cardButtons, true));
        cardPanel.add(createImageButton("Apfel", "icons/course/comida-sana.png", selectedCard, cardButtons, true));

        return new FlashCardResult(cardPanel, selectedCard);
    }
}