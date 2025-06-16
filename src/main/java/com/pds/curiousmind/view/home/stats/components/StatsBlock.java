package com.pds.curiousmind.view.home.stats.components;

        import com.pds.curiousmind.view.common.RoundedLabel;

        import javax.swing.*;
        import java.awt.*;

        import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

        /**
         * StatsBlock is a custom JPanel that displays a statistic with a title, value, and icon.
         * It uses rounded labels for a modern UI appearance.
         */
        public class StatsBlock extends JPanel {

            /**
             * Constructs a StatsBlock with the specified title, value, and icon.
             * Sets up the layout and styles for the title and value labels.
             *
             * @param title The title of the statistic.
             * @param value The numeric value to display.
             * @param iconPath The path to the icon image.
             */
            public StatsBlock(String title, int value, String iconPath) {
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                setOpaque(false);

                RoundedLabel titleLabel = new RoundedLabel("  " + title); // space for icon separation
                titleLabel.setLabelBackground(new Color(230, 230, 230));
                titleLabel.setLabelBorderColor(new Color(200, 200, 200));
                titleLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 13));
                titleLabel.setMaximumSize(new Dimension(200, 30));
                titleLabel.setPreferredSize(new Dimension(200, 30));
                titleLabel.setIcon(loadIcon(iconPath, 18, 18));

                RoundedLabel valueLabel = new RoundedLabel(String.valueOf(value));
                valueLabel.setLabelBackground(new Color(0, 200, 80));
                valueLabel.setLabelBorderColor(new Color(0, 180, 70));
                valueLabel.setForeground(Color.WHITE);
                valueLabel.setFont(new Font(FONT_NAME, Font.BOLD, 14));
                valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
                valueLabel.setMaximumSize(new Dimension(200, 30));
                valueLabel.setPreferredSize(new Dimension(200, 30));

                add(titleLabel);
                add(Box.createVerticalStrut(5));
                add(valueLabel);
            }
        }