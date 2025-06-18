package com.pds.curiousmind.view.home.stats.components;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.home.components.CourseItemPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A custom panel that displays a registered course item along with a progress bar.
 * <p>
 * The course item is shown at the top, and the progress bar appears below,
 * indicating the user's progress in that course.
 * </p>
 * This component is used in the user's profile view to provide a quick overview
 * of course completion.
 *
 * @see RegisteredCourse
 * @see CourseItemPanel
 * @see RoundedProgressBar
 * @author
 */
public class CourseWithProgressPanel extends JPanel {

    /**
     * Constructs a {@code CourseWithProgressPanel} with the given registered course,
     * progress percentage, and an action to be triggered on click.
     *
     * @param rc       The registered course to be displayed.
     * @param progress The user's progress in the course (0–100).
     * @param onClick  A {@code Runnable} action to execute when the course item is clicked.
     */
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
