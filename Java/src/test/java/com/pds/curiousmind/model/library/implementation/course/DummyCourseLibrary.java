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
        courses.add(course);
        return course;
    }

    @Override
    public Course update(Course course) {
        int index = courses.indexOf(course);
        if (index >= 0) {
            courses.set(index, course);
            return course;
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
        // Si tu Course no tiene id → devuelve null o usa name como id en tests
        return null;
    }

    public Course getByName(String name) {
        return courses.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
