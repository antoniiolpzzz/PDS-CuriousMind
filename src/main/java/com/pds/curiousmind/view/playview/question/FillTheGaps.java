package com.pds.curiousmind.view.playview.question;

        import javax.swing.*;
        import java.awt.*;

        public class FillTheGaps {

            public static class GapSectionResult {
                public final JPanel panel;
                public final JTextField answerField;

                public GapSectionResult(JPanel panel, JTextField answerField) {
                    this.panel = panel;
                    this.answerField = answerField;
                }

                // Método para obtener la respuesta como String
                public String getAnswer() {
                    return answerField.getText();
                }
            }

            public static GapSectionResult createGapSection() {
                JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
                optionsPanel.setOpaque(false);

                JTextField answerField = new JTextField();
                answerField.setFont(new Font("SansSerif", Font.PLAIN, 18));
                answerField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                answerField.setPreferredSize(new Dimension(830, 40));

                optionsPanel.add(answerField);

                return new GapSectionResult(optionsPanel, answerField);
            }
        }