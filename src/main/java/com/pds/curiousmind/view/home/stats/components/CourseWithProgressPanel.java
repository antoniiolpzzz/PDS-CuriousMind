package com.pds.curiousmind.view.home.stats.components;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.home.components.CourseItemPanel;

import javax.swing.*;
import java.awt.*;

// This class displays a course item with a progress bar below it.
public class CourseWithProgressPanel extends JPanel {

    public CourseWithProgressPanel(RegisteredCourse rc, int progress, Runnable onClick) {
        setLayout(new BorderLayout(0, 0));
        setOpaque(false);
        Course course = rc.getCourse();
        CourseItemPanel courseItem = new CourseItemPanel(course, onClick);
        courseItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        progressPanel.setOpaque(false);
        JProgressBar progressBar = new RoundedProgressBar(progress);
        progressBar.setPreferredSize(new Dimension(150, 15));
        progressPanel.add(progressBar);

        add(courseItem, BorderLayout.NORTH);
        add(progressPanel, BorderLayout.CENTER);
    }
}
