package com.pds.curiousmind.view.playview.question;


import javax.swing.*;
import java.awt.*;


public class FillTheGaps {

    //TODO: This should receive only the content block.
    public static JTextField createGapSection() {

        JTextField answerField = new JTextField();
        answerField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        answerField.setFont(new Font("SansSerif", Font.PLAIN, 18));

        return answerField;
    }

}
