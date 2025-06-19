package com.pds.curiousmind.util.mapper;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.service.CourseMapperService;
import com.pds.curiousmind.util.mapper.service.MapperFormat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperServiceTest {

    private Course testCourse;
    private File tempFile;

    @BeforeEach
    void setUp() {
        testCourse = new Course();
        testCourse.setName("Curso Spring Boot");
        testCourse.setDescription("Curso de APIs REST con Spring Boot");
        testCourse.setImageURL("https://example.com/springboot.png");
    }

    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testFromEntityToJsonAndBack() {
        tempFile = CourseMapperService.INSTANCE.fromEntity(testCourse, MapperFormat.JSON);
        assertNotNull(tempFile);
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);

        Course result = CourseMapperService.INSTANCE.toEntity(tempFile);
        assertNotNull(result);
        assertEquals(testCourse.getName(), result.getName());
        assertEquals(testCourse.getDescription(), result.getDescription());
        assertEquals(testCourse.getImageURL(), result.getImageURL());
    }

    @Test
    void testFromEntityToYamlAndBack() {
        tempFile = CourseMapperService.INSTANCE.fromEntity(testCourse, MapperFormat.YAML);
        assertNotNull(tempFile);
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);

        Course result = CourseMapperService.INSTANCE.toEntity(tempFile);
        assertNotNull(result);
        assertEquals(testCourse.getName(), result.getName());
        assertEquals(testCourse.getDescription(), result.getDescription());
        assertEquals(testCourse.getImageURL(), result.getImageURL());
    }

    @Test
    void testToEntityWithUnsupportedExtensionThrowsException() {
        File unsupportedFile = new File("file.unsupported");

        assertThrows(IllegalArgumentException.class, () -> {
            CourseMapperService.INSTANCE.toEntity(unsupportedFile);
        });
    }

    @Test
    void testFromEntityWithNullFormatThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            CourseMapperService.INSTANCE.fromEntity(testCourse, null);
        });
    }


    @Test
    void testExtractFileExtensionValid() {
        File jsonFile = new File("example.json");
        File yamlFile = new File("example.yaml");

        assertEquals("JSON", CourseMapperService.INSTANCE.extractFileExtension(jsonFile));
        assertEquals("YAML", CourseMapperService.INSTANCE.extractFileExtension(yamlFile));
    }

    @Test
    void testExtractFileExtensionInvalid() {
        File noExtensionFile = new File("noextensionfile");
        File dotAtEndFile = new File("file.");

        assertNull(CourseMapperService.INSTANCE.extractFileExtension(noExtensionFile));
        assertNull(CourseMapperService.INSTANCE.extractFileExtension(dotAtEndFile));
    }
}
