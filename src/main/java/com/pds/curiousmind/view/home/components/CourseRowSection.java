package com.pds.curiousmind.view.home.components;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.view.home.course.CourseDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * CourseRowSection provides static methods to create JPanel sections
 * that display courses or registered courses in a grid layout.
 * Each course item is represented by a CourseItemPanel.
 */
public class CourseRowSection {

    /**
     * Creates a JPanel displaying a row of registered courses.
     * Each registered course is shown as a CourseItemPanel.
     * Clicking a course opens its CourseDashboard and closes the parent frame.
     *
     * @param parentFrame the JFrame to be disposed when a course is selected
     * @param courseData list of RegisteredCourse objects to display
     * @return JPanel containing the registered course items in a grid layout
     */
    public static JPanel createRegisteredCourseRowSection(JFrame parentFrame, List<RegisteredCourse> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15)); // dynamic rows, 4 columns, gaps between cells
        row.setOpaque(false);

        for (RegisteredCourse rc : courseData) {
            row.add(new CourseItemPanel(rc.getCourse(), () -> {
                new CourseDashboard(rc);
                parentFrame.dispose(); // Close current window
            }));
        }
        return row;
    }

    /**
     * Creates a JPanel displaying a row of courses.
     * Each course is shown as a CourseItemPanel.
     * Clicking a course opens the CourseStrategyWindow without closing the parent frame.
     *
     * @param parentFrame the parent JFrame that stays open
     * @param courseData list of Course objects to display
     * @param user the current User interacting with the courses
     * @return JPanel containing the course items in a grid layout
     */
    public static JPanel createCourseRowSection(JFrame parentFrame, List<Course> courseData, User user) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15)); // dynamic rows, 4 columns, with gaps
        row.setOpaque(false);

        for (Course course : courseData) {
            row.add(new CourseItemPanel(course, () -> {
                new CourseStrategyWindow(parentFrame, course, user);
            }));
        }
        return row;
    }

}
