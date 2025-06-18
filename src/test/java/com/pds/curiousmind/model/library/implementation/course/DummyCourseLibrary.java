package com.pds.curiousmind.model.library.implementation.course;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.library.Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyCourseLibrary implements Library<Course> {

    private final List<Course> courses = new ArrayList<>();

    @Override
    public Course add(Course course) {
        course.setId((long) (courses.size() + 1)); // simulamos ID autoincremental
        courses.add(course);
        return course;
    }

    @Override
    public Course update(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(course.getId())) {
                courses.set(i, course);
                return course;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Course course) {
        return courses.remove(course);
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
