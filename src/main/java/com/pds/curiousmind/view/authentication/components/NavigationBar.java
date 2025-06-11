package com.pds.curiousmind.view.authentication.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class NavigationBar extends JPanel {
    private final JLabel aboutUsLabel;
    private final JLabel contactLabel;
    private final JLabel current;
    private JLabel selectedLabel;

    public NavigationBar(String currentPage) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        setOpaque(false);

        add(Box.createHorizontalStrut(500)); // Espaciado entre logo y opciones

        aboutUsLabel = createNavLabel("About Us");
        contactLabel = createNavLabel("Contact");
        current = createNavLabel(String.format("%s", currentPage));

        add(aboutUsLabel);
        add(contactLabel);
        add(current);

        aboutUsLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new AboutPopUp((JFrame) SwingUtilities.getWindowAncestor(NavigationBar.this)).setVisible(true);
            }
        });

        contactLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ContactPopUp((JFrame) SwingUtilities.getWindowAncestor(NavigationBar.this)).setVisible(true);
            }
        });

        setSelected(currentPage);
    }

    private JLabel createNavLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
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

    private void setSelected(String text) {
        if (selectedLabel != null) {
            selectedLabel.setForeground(Color.LIGHT_GRAY);
            selectedLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }

        if (text.equalsIgnoreCase("About Us")) {
            selectedLabel = aboutUsLabel;
        } else if (text.equalsIgnoreCase("Contact")) {
            selectedLabel = contactLabel;
        } else {
            selectedLabel = current;
        }

        selectedLabel.setForeground(Color.WHITE);
        selectedLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Aquí podrías agregar un subrayado o una línea inferior si lo deseas visualmente.
    }
}
