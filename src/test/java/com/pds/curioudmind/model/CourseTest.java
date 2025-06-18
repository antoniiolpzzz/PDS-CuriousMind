package com.pds.curioudmind.model;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.question.implementation.Translate;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.strategy.StrategyType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course createExampleCourse() {
        ContentBlock block = new ContentBlock(
                "Introduction",
                Difficulty.EASY,
                List.of(
                        new Translate("Translate", "Banana", "Banane",
                                List.of(
                                        new Option("Banane"),
                                        new Option("Ananas")
                                )
                        )
                )
        );

        Course course = new Course(
                "Beginner German Course",
                "A course to introduce basic German language concepts.",
                "https://stock.adobe.com/247157521/german-language-learning-logo-icon-with-headphones.jpg",

        List.of(StrategyType.SEQUENTIAL, StrategyType.SHUFFLED),
                List.of(block)
        );


        return course;
    }


    @Test
    void testGetName() {
        Course course = createExampleCourse();
        assertEquals("Beginner German Course", course.getName());
    }

    @Test
    void testGetId() {
        Course course = createExampleCourse();
        assertNotNull(course.getId());
    }

    @Test
    void testGetImageURL() {
        Course course = createExampleCourse();
        assertEquals("https://stock.adobe.com/247157521/german-language-learning-logo-icon-with-headphones.jpg", course.getImageURL());
    }

    @Test
    void testGetDescription() {
        Course course = createExampleCourse();
        assertEquals("A course to introduce basic German language concepts.", course.getDescription());
    }

    @Test
    void testGetContentBlocks() {
        Course course = createExampleCourse();
        assertNotNull(course.getContentBlocks());
        assertEquals(1, course.getContentBlocks().size());
    }

    @Test
    void testSetContentBlocks() {
        Course course = createExampleCourse();
        course.setContentBlocks(List.of());
        assertEquals(0, course.getContentBlocks().size());
    }

    @Test
    void testSetDescription() {
        Course course = createExampleCourse();
        course.setDescription("New description");
        assertEquals("New description", course.getDescription());
    }

    @Test
    void testSetImageURL() {
        Course course = createExampleCourse();
        course.setImageURL("https://example.com/image.jpg");
        assertEquals("https://example.com/image.jpg", course.getImageURL());
    }

    @Test
    void testSetName() {
        Course course = createExampleCourse();
        course.setName("New Name");
        assertEquals("New Name", course.getName());
    }

    @Test
    void testSetId() {
        Course course = createExampleCourse();
        course.setId(123L);
        assertEquals(123L, course.getId());
    }
}
