package com.pds.curiousmind.view.playview.question;


import javax.swing.*;
import java.awt.*;


public class FillTheGaps {

    //TODO: This should receive only the content block.
    public static JPanel createGapSection() {
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0)); // alineación izquierda y 30px margen
        optionsPanel.setOpaque(false);

        JTextField answerField = new JTextField();
        answerField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        answerField.setPreferredSize(new Dimension(830, 40)); // Ajusta al ancho disponible

        optionsPanel.add(answerField);

        return optionsPanel;
    }


}
