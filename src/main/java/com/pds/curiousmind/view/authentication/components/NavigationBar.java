package com.pds.curiousmind.view.authentication.components;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.awt.event.MouseEvent;
        import static com.pds.curiousmind.view.common.GlobalConstants.*;


/**
         * NavigationBar provides a navigation panel with links to "About Us", "Contact", and the current page.
         * It highlights the selected section and opens pop-ups for About and Contact.
         */
        public class NavigationBar extends JPanel {
            private final JLabel aboutUsLabel;
            private final JLabel contactLabel;
            private final JLabel current;
            private JLabel selectedLabel;

            /**
             * Constructs the NavigationBar with the specified current page highlighted.
             *
             * @param currentPage The name of the current page to highlight.
             */
            public NavigationBar(String currentPage) {
                setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
                setOpaque(false);

                add(Box.createHorizontalStrut(500));

                aboutUsLabel = createNavLabel("About Us");
                contactLabel = createNavLabel("Contact");
                current = createNavLabel(String.format("%s", currentPage));

                add(aboutUsLabel);
                add(contactLabel);
                add(current);

                // Show AboutPopUp when "About Us" is clicked
                aboutUsLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new AboutPopUp((JFrame) SwingUtilities.getWindowAncestor(NavigationBar.this)).setVisible(true);
                    }
                });

                // Show ContactPopUp when "Contact" is clicked
                contactLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new ContactPopUp((JFrame) SwingUtilities.getWindowAncestor(NavigationBar.this)).setVisible(true);
                    }
                });

                setSelected(currentPage);
            }

            /**
             * Creates a navigation label with mouse listeners for hover and selection effects.
             *
             * @param text The label text.
             * @return The configured JLabel.
             */
            private JLabel createNavLabel(String text) {
                JLabel label = new JLabel(text);
                label.setForeground(Color.LIGHT_GRAY);
                label.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                label.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        if (label != selectedLabel)
                            label.setForeground(Color.WHITE);
                    }

                    public void mouseExited(MouseEvent e) {
                        if (label != selectedLabel)
                            label.setForeground(Color.LIGHT_GRAY);
                    }

                    public void mouseClicked(MouseEvent e) {
                        setSelected(text);
                    }
                });

                return label;
            }

            /**
             * Highlights the selected navigation label and resets the previous one.
             *
             * @param text The text of the label to select.
             */
            private void setSelected(String text) {
                if (selectedLabel != null) {
                    selectedLabel.setForeground(Color.LIGHT_GRAY);
                    selectedLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
                }

                if (text.equalsIgnoreCase("About Us")) {
                    selectedLabel = aboutUsLabel;
                } else if (text.equalsIgnoreCase("Contact")) {
                    selectedLabel = contactLabel;
                } else {
                    selectedLabel = current;
                }

                selectedLabel.setForeground(Color.WHITE);
                selectedLabel.setFont(new Font(FONT_NAME, Font.BOLD, 14));
                // You can add underline or bottom border here for visual effect.
            }
        }