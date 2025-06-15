package com.pds.curiousmind.view.home.components;


import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.home.course.CourseDashboard;

import javax.swing.*;
        import java.awt.*;
import java.util.Arrays;
import java.util.List;



public class CourseRowSection {


    public static JPanel createRegisteredCourseRowSection(JFrame parentFrame, List<RegisteredCourse> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15));
        row.setOpaque(false);
        for (RegisteredCourse rc : courseData) {
            row.add(new RegisteredCourseItemPanel(rc, () -> {
                new CourseDashboard(rc);
                parentFrame.dispose(); // Cierra la ventana actual
            }));
        }
        return row;
    }

    public static JPanel createCourseRowSection(JFrame parentFrame, List<Course> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15));
        row.setOpaque(false);
        for (Course course : courseData) {
            row.add(new CourseItemPanel(course, () -> {
                new CourseStrategyWindow(parentFrame, course);
            }));
        }
        return row;
    }

}