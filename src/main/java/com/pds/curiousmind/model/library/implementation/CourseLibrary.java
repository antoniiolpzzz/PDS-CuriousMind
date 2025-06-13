package com.pds.curiousmind.model.library.implementation;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.course.Course;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CourseLibrary implements Library<Course> {
    INSTANCE;

    private final List<Course> courses;

    CourseLibrary() {
        this.courses = new ArrayList<>();
    }

    //METHODS
    @Override
    public void add(Course course) {
        courses.add(course);
    }

    @Override
    public void remove(Course course) {
        courses.remove(course);
    }

    @Override
    public List<Course> getAll() {
        return Collections.unmodifiableList(courses);
    }

    @Override
    public Course getById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Course getByName(String name) {
        return courses.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}

