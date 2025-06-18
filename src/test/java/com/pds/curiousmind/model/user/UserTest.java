package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.stat.Stat;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User("Ana López", "ana@example.com", "secret", "ana123");

        assertEquals("Ana López", user.getFullName());
        assertEquals("ana@example.com", user.getEmail());
        assertEquals("secret", user.getPassword());
        assertEquals("ana123", user.getUsername());

        assertNotNull(user.getStats());
        assertEquals(user, user.getStats().getUser());
    }

    @Test
    void testRegisterInCourseAddsRegisteredCourse() {
        User user = new User("Ana López", "ana@example.com", "secret", "ana123");

        Course course = mock(Course.class);
        when(course.getContentBlocks()).thenReturn(List.of()); // evita que cree bloques por defecto

        assertEquals(0, user.getRegisteredCourses().size());

        user.registerInCourse(course, StrategyType.SEQUENTIAL);

        List<RegisteredCourse> registeredCourses = user.getRegisteredCourses();
        assertEquals(1, registeredCourses.size());
        assertEquals(user, registeredCourses.get(0).getUser());
        assertEquals(course, registeredCourses.get(0).getCourse());
    }

    @Test
    void testGetRegisteredCoursesIsUnmodifiable() {
        User user = new User("Ana López", "ana@example.com", "secret", "ana123");

        List<RegisteredCourse> registeredCourses = user.getRegisteredCourses();

        assertThrows(UnsupportedOperationException.class, () -> registeredCourses.add(mock(RegisteredCourse.class)));
    }

    @Test
    void testAddExperiencePointsDelegatesToStat() {
        User user = new User("Ana López", "ana@example.com", "secret", "ana123");

        Stat stat = mock(Stat.class);
        user.setStats(stat);

        when(stat.addExperiencePoints(500)).thenReturn(true);

        assertTrue(user.addExperiencePoints(500));

        verify(stat, times(1)).addExperiencePoints(500);
    }

    @Test
    void testSetters() {
        User user = new User("Ana López", "ana@example.com", "secret", "ana123");

        user.setEmail("new_email@example.com");
        user.setFullName("Ana María López");
        user.setUsername("ana456");
        user.setPassword("new_secret");

        assertEquals("new_email@example.com", user.getEmail());
        assertEquals("Ana María López", user.getFullName());
        assertEquals("ana456", user.getUsername());
        assertEquals("new_secret", user.getPassword());
    }
}
