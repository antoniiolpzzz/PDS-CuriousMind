package com.pds.curiousmind.model.library.implementation.course;

import com.pds.curiousmind.model.course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyCourseLibraryTest {

    private DummyCourseLibrary courseLibrary;

    @BeforeEach
    void setUp() {
        courseLibrary = new DummyCourseLibrary();
    }

    @Test
    void testAddCourse() {
        Course course = new Course();
        course.setName("Curso Java");

        Course saved = courseLibrary.add(course);

        assertEquals(1, courseLibrary.getAll().size());
        assertEquals("Curso Java", saved.getName());
    }

    @Test
    void testUpdateCourse() {
        Course course = new Course();
        course.setName("Curso Java");
        Course saved = courseLibrary.add(course);

        saved.setName("Curso Java Avanzado");

        Course updated = courseLibrary.update(saved);

        assertNotNull(updated);
        assertEquals("Curso Java Avanzado", updated.getName());
    }

    @Test
    void testRemoveCourse() {
        Course course = new Course();
        course.setName("Curso Java");

        courseLibrary.add(course);

        assertEquals(1, courseLibrary.getAll().size());

        boolean removed = courseLibrary.remove(course);

        assertTrue(removed);
        assertEquals(0, courseLibrary.getAll().size());
    }

    @Test
    void testGetByName() {
        Course course = new Course();
        course.setName("Curso Spring");

        courseLibrary.add(course);

        Course found = courseLibrary.getByName("Curso Spring");

        assertNotNull(found);
        assertEquals("Curso Spring", found.getName());
    }
}
