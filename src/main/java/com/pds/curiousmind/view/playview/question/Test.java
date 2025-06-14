package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;


import javax.swing.*;
import java.awt.*;



public class Test extends JFrame {


    public static JPanel createTestPanel(java.util.List<String> options) {
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0)); // alineación izquierda y separación vertical
        optionsPanel.setOpaque(false);

        ButtonGroup group = new ButtonGroup();

        for (String option : options) {
            JToggleButton optionButton = new JToggleButton(option);
            optionButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
            optionButton.setBackground(new Color(245, 245, 245));
            optionButton.setHorizontalAlignment(SwingConstants.LEFT);

            // Asignamos tamaño fijo para que ocupe todo el ancho del rightPanel (950 px menos márgenes)
            optionButton.setPreferredSize(new Dimension(830, 40)); // 950 - 30(left) - 30(right)
            group.add(optionButton);
            optionsPanel.add(optionButton);
        }

        return optionsPanel;
    }




}
