package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.StrategyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");

        assertEquals("Ana", user.getFullName());
        assertEquals("ana@test.com", user.getEmail());
        assertEquals("1234", user.getPassword());
        assertEquals("ana", user.getUsername());
        assertNotNull(user.getStats());
        assertEquals(0, user.getRegisteredCourses().size());
    }

    @Test
    void testSetters() {
        User user = new User();

        user.setFullName("Ana");
        user.setEmail("ana@test.com");
        user.setPassword("1234");
        user.setUsername("ana");

        assertEquals("Ana", user.getFullName());
        assertEquals("ana@test.com", user.getEmail());
        assertEquals("1234", user.getPassword());
        assertEquals("ana", user.getUsername());
    }

    @Test
    void testRegisterInCourse() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");

        Course course = new Course();
        course.setName("Course 1");

        user.registerInCourse(course, StrategyType.SEQUENTIAL);

        assertEquals(1, user.getRegisteredCourses().size());
        RegisteredCourse regCourse = user.getRegisteredCourses().get(0);
        assertEquals(course.getName(), regCourse.getName());
    }

    @Test
    void testAddExperiencePoints() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");

        assertTrue(user.addExperiencePoints(500));
        assertEquals(500, user.getStats().getExperiencePoints());
    }

    @Test
    void testAddTimeSpent() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");

        assertTrue(user.addTimeSpent(120));
        assertEquals(120, user.getStats().getTimeSpent());

        assertFalse(user.addTimeSpent(-50)); // invalid
    }

    @Test
    void testLogEntry() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");

        int initialDays = user.getStats().getDaysActive();

        assertTrue(user.logEntry());
        assertEquals(initialDays + 1, user.getStats().getDaysActive());
    }
}
