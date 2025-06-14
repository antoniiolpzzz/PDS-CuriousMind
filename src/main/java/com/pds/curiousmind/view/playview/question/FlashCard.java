package com.pds.curiousmind.view.playview.question;

import javax.swing.*;
import java.awt.*;
import static com.pds.curiousmind.view.common.ImageButton.createImageButton;


public class FlashCard extends JFrame {

        public static JPanel createFlashCard(java.util.List<String> options) {

            JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
            cardPanel.setOpaque(false);

            final String[] selectedCard = {null};
            final java.util.List<JButton> cardButtons = new java.util.ArrayList<>();

            //TODO: Recuperate the options of the question and use them to create the button.

            cardPanel.add(createImageButton("Zwiebel", "icons/course/cebolla.png", selectedCard, cardButtons, true));
            cardPanel.add(createImageButton("Karotte", "icons/course/zanahoria.png", selectedCard, cardButtons, true));
            cardPanel.add(createImageButton("Apfel", "icons/course/comida-sana.png", selectedCard, cardButtons, true));


            return cardPanel;
        }

    }


//Show the options

