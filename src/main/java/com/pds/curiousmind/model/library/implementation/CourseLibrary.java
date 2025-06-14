package com.pds.curiousmind.model.library.implementation;

import com.pds.curiousmind.model.library.Library;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.persistence.provider.AdapterProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public enum CourseLibrary implements Library<Course> {
    INSTANCE;

    private final ICourseAdapter courseAdapter;
    private final List<Course> courses;

    CourseLibrary() {
        this.courseAdapter = AdapterProvider.INSTANCE().getCourseAdapter();
        this.courses = new CopyOnWriteArrayList<>(courseAdapter.findAll());
    }

    public void reload() {
        courses.clear();
        courses.addAll(courseAdapter.findAll());
    }

    //METHODS
    @Override
    public Course add(Course course) {
        Course saved = courseAdapter.save(course);
        courses.add(saved);
        return saved;
    }

    @Override
    public Course update(Course course) {
        Course updated = courseAdapter.update(course);
        if (updated != null) {
            courses.replaceAll(c -> c.getId().equals(updated.getId()) ? updated : c);
        }
        return updated;
    }

    @Override
    public boolean remove(Course course) {
        boolean deleted = courseAdapter.delete(course);
        if (deleted) {
            courses.remove(course);
        }
        return deleted;
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
