package com.pds.curiousmind.util.mapper;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperJSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperJSONTest {

    private CourseMapperJSON courseMapper;
    private Course course;
    private File tempFile;

    @BeforeEach
    void setUp() {
        courseMapper = new CourseMapperJSON();

        course = new Course();
        course.setName("Curso de Spring Boot");
        course.setDescription("Aprende a desarrollar APIs REST con Spring Boot");
        course.setImageURL("https://example.com/springboot.png");

        tempFile = null;
    }

    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testFromEntityCreatesValidJsonFile() {
        tempFile = courseMapper.fromEntity(course);

        assertNotNull(tempFile);
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }

    @Test
    void testToEntityReadsValidCourseFromFile() {
        // Primero generamos el fichero
        tempFile = courseMapper.fromEntity(course);

        // Luego leemos
        Course readCourse = courseMapper.toEntity(tempFile);

        assertNotNull(readCourse);
        assertEquals(course.getName(), readCourse.getName());
        assertEquals(course.getDescription(), readCourse.getDescription());
        assertEquals(course.getImageURL(), readCourse.getImageURL());
    }

    @Test
    void testToEntityWithNullFileReturnsNull() {
        Course result = courseMapper.toEntity(null);
        assertNull(result);
    }

    @Test
    void testToEntityWithNonExistentFileReturnsNull() {
        File fakeFile = new File("nonexistent.json");
        Course result = courseMapper.toEntity(fakeFile);
        assertNull(result);
    }

    @Test
    void testFromEntityWithNullCourseReturnsNull() {
        File result = courseMapper.fromEntity(null);
        assertNull(result);
    }
}
