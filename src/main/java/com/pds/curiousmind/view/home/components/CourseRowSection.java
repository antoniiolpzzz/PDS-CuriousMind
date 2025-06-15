package com.pds.curiousmind.view.home.components;


import com.pds.curiousmind.view.home.course.CourseDashboard;

import javax.swing.*;
        import java.awt.*;
import java.util.Arrays;
import java.util.List;



public class CourseRowSection {

    public static JPanel createCourseRowSection(List<String[]> courseData) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(0, 4, 20, 15));
        row.setOpaque(false);
        for (String[] d : courseData) {
            row.add(new CourseItemPanel(d[0], d[1], () -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(row);
                // Only allow opening CourseStrategyWindow if user is not registered
                if (!isRegisteredCourse(d[0])) {

                    new CourseStrategyWindow(topFrame, d[0], d[1]);
                } else {
                    //dispose();
                    //TODO: Open CourseDashboard with the course as parameter
                    new CourseDashboard(d[0], d[1]);
                }
            }));
        }
        return row;
    }


    private static boolean isRegisteredCourse(String courseName) {

        //TODO: Check if the course is registered with the type of the object
        List<String> enrolledCourses = Arrays.asList("German", "Modern History", "Java Script");
        return enrolledCourses.contains(courseName);
    }

}