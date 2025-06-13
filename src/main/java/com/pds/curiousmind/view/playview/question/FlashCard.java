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
import java.util.Arrays;
import static com.pds.curiousmind.view.home.components.StrategyButton.createStrategyButton;
import static com.pds.curiousmind.view.playview.question.components.CreateHeader.createHeader;
import static com.pds.curiousmind.view.playview.question.components.QuestionBackground.createBackground;


public class FlashCard extends JFrame {

    //TODO: This should receive only the content block.
    public FlashCard(String title, String iconPath, String indication, String statement, java.util.List<String> options) {
        setTitle("CuriousMind - FlashCard");
        setMinimumSize(new Dimension(1200, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // BACKGROUND PANEL
        JPanel basePanel = createBackground(title,iconPath);
        setContentPane(basePanel);

        // RIGHT PANEL
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
        RoundedPanel rightPanel = new RoundedPanel(30);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        rightWrapper.add(rightPanel, BorderLayout.CENTER);
        basePanel.add(rightWrapper, BorderLayout.EAST);
        rightWrapper.setPreferredSize(new Dimension(950, 0));

        // COMMON HEADER
        rightPanel.add(createHeader(title, iconPath,indication, statement));

        //Show the options

        JPanel strategyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        strategyPanel.setOpaque(false);
        // Track the selected strategy and buttons
        final String[] selectedStrategy = {null};
        final java.util.List<JButton> strategyButtons = new java.util.ArrayList<>();
        //TODO: Recuperate the options of the question.
        strategyPanel.add(createStrategyButton("Zwiebel", "icons/course/cebolla.png", selectedStrategy, strategyButtons, true));
        strategyPanel.add(createStrategyButton("Karotte", "icons/course/zanahoria.png", selectedStrategy, strategyButtons, true));
        strategyPanel.add(createStrategyButton("Apfel", "icons/course/comida-sana.png", selectedStrategy, strategyButtons, true));
        rightPanel.add(strategyPanel);


        // Submit button
        StyledButton submitButton = new StyledButton("Submit", Color.BLACK, Color.WHITE);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            //TODO: Handle the submission logic here
            JOptionPane.showMessageDialog(this, "Test submitted successfully!");
        });
        rightPanel.add(submitButton);
        rightPanel.add(Box.createVerticalStrut(5));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashCard(
                "German",
                "icons/course/german.png",
                "Choose the correct option",
                "Witches of the following is an opnion?",
                Arrays.asList("A.   Kuchen", "B.   Kartoffelchips", "C.   Kopfsalat", "D.   Lachs")
        ));
    }


}
