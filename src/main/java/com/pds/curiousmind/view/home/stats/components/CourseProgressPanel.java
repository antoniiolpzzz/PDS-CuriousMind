package com.pds.curiousmind.view.home.stats.components;

import com.pds.curiousmind.view.home.components.CourseItemPanel;

import javax.swing.*;
import java.awt.*;

public class CourseProgressPanel extends JPanel {

    public CourseProgressPanel(String title, String iconPath, int progress, Runnable onClick) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(200, 90));

        CourseItemPanel courseItem = new CourseItemPanel(title, iconPath, onClick);
        add(courseItem, BorderLayout.NORTH);

        JProgressBar progressBar = new RoundedProgressBar(progress);
        add(progressBar, BorderLayout.SOUTH);
    }
}
