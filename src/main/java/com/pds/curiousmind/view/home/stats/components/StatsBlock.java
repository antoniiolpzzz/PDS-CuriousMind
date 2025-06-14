package com.pds.curiousmind.view.home.stats.components;

import com.pds.curiousmind.view.common.RoundedLabel;

import javax.swing.*;
import java.awt.*;

import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

public class StatsBlock extends JPanel {

    public StatsBlock(String title, String value, String iconPath) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        // Título con icono y fondo gris
        RoundedLabel titleLabel = new RoundedLabel("  " + title); // espacio para separarlo del icono
        titleLabel.setLabelBackground(new Color(230, 230, 230));
        titleLabel.setLabelBorderColor(new Color(200, 200, 200));
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        titleLabel.setMaximumSize(new Dimension(200, 30));
        titleLabel.setPreferredSize(new Dimension(200, 30));
        titleLabel.setIcon(loadIcon(iconPath, 18, 18));

        // Valor con fondo verde
        RoundedLabel valueLabel = new RoundedLabel(value);
        valueLabel.setLabelBackground(new Color(0, 200, 80));
        valueLabel.setLabelBorderColor(new Color(0, 180, 70));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setMaximumSize(new Dimension(200, 30));
        valueLabel.setPreferredSize(new Dimension(200, 30));

        add(titleLabel);
        add(Box.createVerticalStrut(5));
        add(valueLabel);
    }
}
