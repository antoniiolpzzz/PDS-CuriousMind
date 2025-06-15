
                        package com.pds.curiousmind.view.playview.question;

                        import com.pds.curiousmind.model.question.option.Option;
                        import com.pds.curiousmind.view.common.RoundedPanel;
                        import com.pds.curiousmind.view.common.StyledButton;

                        import javax.swing.*;
                        import java.awt.*;
                        import java.util.List;
                        import java.util.ArrayList;

                        public class Test extends JFrame {

                            public static class TestPanelResult {
                                public final JPanel panel;
                                private final String[] selectedOption;

                                public TestPanelResult(JPanel panel, String[] selectedOption) {
                                    this.panel = panel;
                                    this.selectedOption = selectedOption;
                                }

                                public String getAnswer() {
                                    return selectedOption[0];
                                }
                            }

                            public static TestPanelResult createTestPanel(List<Option> options) {
                                JPanel optionsPanel = new JPanel();
                                optionsPanel.setOpaque(false);

                                ButtonGroup group = new ButtonGroup();
                                final String[] selectedOption = {null};

                                for (Option option : options) {
                                    JToggleButton optionButton = new JToggleButton(option.getLabel());
                                    optionButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
                                    optionButton.setBackground(new Color(245, 245, 245));
                                    optionButton.setHorizontalAlignment(SwingConstants.LEFT);
                                    optionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    optionButton.setPreferredSize(new Dimension(830, 40));
                                    group.add(optionButton);
                                    optionsPanel.add(optionButton);

                                    optionButton.addActionListener(e -> selectedOption[0] = option.getLabel());
                                }

                                return new TestPanelResult(optionsPanel, selectedOption);
                            }
                        }