package com.pds.curiousmind.model.registeredCourse;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisteredCourseTest {

    @Test
    void testConstructorGeneratesRegisteredContentBlocks() {
        User user = mock(User.class);
        Course course = mock(Course.class);
        ContentBlock cb1 = mock(ContentBlock.class);
        ContentBlock cb2 = mock(ContentBlock.class);

        when(course.getContentBlocks()).thenReturn(List.of(cb1, cb2));

        RegisteredCourse registeredCourse = new RegisteredCourse(user, course, StrategyType.SEQUENTIAL);

        assertEquals(user, registeredCourse.getUser());
        assertEquals(course, registeredCourse.getCourse());
        assertEquals(StrategyType.SEQUENTIAL, registeredCourse.getStrategy());

        List<RegisteredContentBlock> blocks = registeredCourse.getRegisteredContentBlocks();
        assertEquals(2, blocks.size());
        blocks.forEach(block -> assertEquals(registeredCourse, block.getRegisteredCourse()));
    }

    @Test
    void testGetProgressAndIsCompleted() {
        RegisteredCourse registeredCourse = new RegisteredCourse();

        RegisteredContentBlock block1 = mock(RegisteredContentBlock.class);
        RegisteredContentBlock block2 = mock(RegisteredContentBlock.class);
        RegisteredContentBlock block3 = mock(RegisteredContentBlock.class);

        when(block1.isCompleted()).thenReturn(true);
        when(block2.isCompleted()).thenReturn(false);
        when(block3.isCompleted()).thenReturn(true);

        registeredCourse.setRegisteredContentBlocks(List.of(block1, block2, block3));

        assertEquals(66.666, registeredCourse.getProgress(), 0.01);
        assertFalse(registeredCourse.isCompleted());

        // Completar todos
        when(block2.isCompleted()).thenReturn(true);

        assertEquals(100.0, registeredCourse.getProgress(), 0.01);
        assertTrue(registeredCourse.isCompleted());
    }

    @Test
    void testGetCompletedBlocksCount() {
        RegisteredCourse registeredCourse = new RegisteredCourse();

        RegisteredContentBlock block1 = mock(RegisteredContentBlock.class);
        RegisteredContentBlock block2 = mock(RegisteredContentBlock.class);
        RegisteredContentBlock block3 = mock(RegisteredContentBlock.class);

        when(block1.isCompleted()).thenReturn(true);
        when(block2.isCompleted()).thenReturn(false);
        when(block3.isCompleted()).thenReturn(true);

        registeredCourse.setRegisteredContentBlocks(List.of(block1, block2, block3));

        assertEquals(2, registeredCourse.getCompletedBlocksCount());
    }

    @Test
    void testGetNameDelegatesToCourse() {
        Course course = mock(Course.class);
        when(course.getName()).thenReturn("Course Name");

        RegisteredCourse registeredCourse = new RegisteredCourse();
        registeredCourse.setCourse(course);

        assertEquals("Course Name", registeredCourse.getName());
    }

    @Test
    void testGetDescriptionDelegatesToCourse() {
        Course course = mock(Course.class);
        when(course.getDescription()).thenReturn("Course Description");

        RegisteredCourse registeredCourse = new RegisteredCourse();
        registeredCourse.setCourse(course);

        assertEquals("Course Description", registeredCourse.getDescription());
    }

    @Test
    void testGetImageURLDelegatesToCourse() {
        Course course = mock(Course.class);
        when(course.getImageURL()).thenReturn("http://image.url");

        RegisteredCourse registeredCourse = new RegisteredCourse();
        registeredCourse.setCourse(course);

        assertEquals("http://image.url", registeredCourse.getImageURL());
    }

    @Test
    void testSetters() {
        RegisteredCourse registeredCourse = new RegisteredCourse();

        User user = mock(User.class);
        registeredCourse.setUser(user);
        assertEquals(user, registeredCourse.getUser());

        registeredCourse.setStrategy(StrategyType.SHUFFLED);
        assertEquals(StrategyType.SHUFFLED, registeredCourse.getStrategy());
    }
}
