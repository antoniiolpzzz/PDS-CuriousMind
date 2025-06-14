package com.pds.curiousmind.view.playview.question;

import com.pds.curiousmind.view.common.BackgroundPanel;
import com.pds.curiousmind.view.common.RoundedPanel;
import com.pds.curiousmind.view.common.StyledButton;
import com.pds.curiousmind.view.home.components.SectionTitle;
import com.pds.curiousmind.view.home.course.CourseDashboard;
import com.pds.curiousmind.view.home.stats.components.RoundedProgressBar;
import com.pds.curiousmind.view.playview.question.components.CreateHeader;

import javax.swing.*;
import java.awt.*;
import static com.pds.curiousmind.view.home.components.StrategyButton.createStrategyButton;


public class FlashCard extends JFrame {

        public static JPanel createFlashCard(java.util.List<String> options) {

            JPanel strategyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
            strategyPanel.setOpaque(false);

            final String[] selectedStrategy = {null};
            final java.util.List<JButton> strategyButtons = new java.util.ArrayList<>();

            //TODO: Recuperate the options of the question and use them to create the button.

            strategyPanel.add(createStrategyButton("Zwiebel", "icons/course/cebolla.png", selectedStrategy, strategyButtons, true));
            strategyPanel.add(createStrategyButton("Karotte", "icons/course/zanahoria.png", selectedStrategy, strategyButtons, true));
            strategyPanel.add(createStrategyButton("Apfel", "icons/course/comida-sana.png", selectedStrategy, strategyButtons, true));


            return strategyPanel;
        }

    }


//Show the options

