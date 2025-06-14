package com.pds.curiousmind.view.playview.question;


import javax.swing.*;
import java.awt.*;


public class Translation {

    public static JTextField createTranslationSection() {

        //TODO: This should receive the options and show them for the user to select.
        JTextField answerField = new JTextField();
        answerField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        answerField.setFont(new Font("SansSerif", Font.PLAIN, 18));

        return answerField;
    }

}
