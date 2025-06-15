package com.pds.curiousmind.view.playview.question;

                import javax.swing.*;
                import java.awt.*;
                import java.util.ArrayList;
                import java.util.List;

                public class Translation {

                    public static class TranslationSectionResult {
                        public final JPanel panel;
                        private final List<String> selectedWords;

                        public TranslationSectionResult(JPanel panel, List<String> selectedWords) {
                            this.panel = panel;
                            this.selectedWords = selectedWords;
                        }

                        public String getAnswer() {
                            return String.join(" ", selectedWords);
                        }
                    }

                    public static TranslationSectionResult createTranslationSection() {
                        JPanel translationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
                        translationPanel.setOpaque(false);

                        JPanel answerInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                        answerInnerPanel.setBackground(new Color(240, 240, 240));
                        answerInnerPanel.setOpaque(true);

                        JScrollPane scrollPane = new JScrollPane(answerInnerPanel,
                                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollPane.setPreferredSize(new Dimension(830, 60));
                        scrollPane.setBorder(BorderFactory.createEmptyBorder());

                        translationPanel.add(scrollPane);

                        List<String> wordOptions = List.of("Me", "llamo", "Pepito", "Hola", "el", "niño", "es", "muy", "listo", "y", "simpático");
                        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                        optionsPanel.setOpaque(false);
                        optionsPanel.setPreferredSize(new Dimension(830, 100));

                        List<JButton> optionButtons = new ArrayList<>();
                        List<String> selectedWords = new ArrayList<>();

                        for (String word : wordOptions) {
                            JButton wordButton = new JButton(word);
                            wordButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
                            wordButton.setFocusPainted(false);
                            wordButton.setBackground(Color.WHITE);
                            wordButton.setForeground(Color.BLACK);
                            wordButton.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
                            wordButton.setPreferredSize(new Dimension(90, 35));

                            optionButtons.add(wordButton);

                            wordButton.addActionListener(e -> {
                                wordButton.setVisible(false);

                                JButton selectedButton = new JButton(word);
                                selectedButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
                                selectedButton.setFocusPainted(false);
                                selectedButton.setBackground(new Color(220, 220, 220));
                                selectedButton.setForeground(Color.BLACK);
                                selectedButton.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 1));
                                selectedButton.setPreferredSize(new Dimension(90, 35));

                                selectedWords.add(word);

                                selectedButton.addActionListener(evt -> {
                                    answerInnerPanel.remove(selectedButton);
                                    wordButton.setVisible(true);
                                    selectedWords.remove(word);
                                    answerInnerPanel.revalidate();
                                    answerInnerPanel.repaint();
                                });

                                answerInnerPanel.add(selectedButton);
                                answerInnerPanel.revalidate();
                                answerInnerPanel.repaint();
                            });

                            optionsPanel.add(wordButton);
                        }

                        translationPanel.add(optionsPanel);

                        return new TranslationSectionResult(translationPanel, selectedWords);
                    }
                }