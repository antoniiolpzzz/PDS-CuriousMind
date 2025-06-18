package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StatTest {

    private User user;
    private Stat stat;

    @BeforeEach
    void setUp() {
        user = new User("Ana López", "ana@example.com", "password123", "ana123");
        stat = new Stat(user);
        stat.setUser(user);
    }

    @Test
    void testAddExperiencePointsPositive() {
        boolean result = stat.addExperiencePoints(1500);

        assertTrue(result);
        assertEquals(1, stat.getLevel());
        assertEquals(500, stat.getExperiencePoints());
    }

    @Test
    void testAddExperiencePointsNegative() {
        boolean result = stat.addExperiencePoints(-500);

        assertFalse(result);
        assertEquals(0, stat.getLevel());
        assertEquals(0, stat.getExperiencePoints());
    }

    @Test
    void testAddTimeSpentPositive() {
        boolean result = stat.addTimeSpent(3600);

        assertTrue(result);
        assertEquals(3600, stat.getTimeSpent());
    }

    @Test
    void testAddTimeSpentNegative() {
        boolean result = stat.addTimeSpent(-300);

        assertFalse(result);
        assertEquals(0, stat.getTimeSpent());
    }

    @Test
    void testLogEntry() {
        boolean firstLog = stat.logEntry();
        boolean secondLog = stat.logEntry();  // Log again same day

        assertTrue(firstLog);
        assertFalse(secondLog);  // Same day should not add twice
        assertEquals(1, stat.getDaysActive());
    }

    @Test
    void testBestStreakSingleDay() {
        stat.logEntry();

        assertEquals(1, stat.getBestStreak());
    }

    @Test
    void testBestStreakMultipleDays() {
        // Simulate 3 consecutive days
        Set<LocalDate> entries = Set.of(
                LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1),
                LocalDate.now()
        );

        stat.setEntries(entries);

        assertEquals(3, stat.getBestStreak());
    }

    @Test
    void testGetCompletedCourses() {
        RegisteredCourse course1 = new RegisteredCourse();
        course1.setRegisteredContentBlocks(new ArrayList<>());  // vacía
        course1.setCourse(null);  // irrelevant
        course1.setStrategy(null);
        course1.setUser(user);

        RegisteredCourse course2 = new RegisteredCourse();
        course2.setRegisteredContentBlocks(new ArrayList<>());
        course2.setCourse(null);
        course2.setStrategy(null);
        course2.setUser(user);

        // Simulate one course completed
        course1.setRegisteredContentBlocks(List.of());
        course2.setRegisteredContentBlocks(List.of());
        // mock "isCompleted" true for 1 course:
        List<RegisteredCourse> registeredCourses = new ArrayList<>() {
            {
                add(new RegisteredCourse() {
                    @Override
                    public boolean isCompleted() {
                        return true;
                    }
                });
                add(new RegisteredCourse() {
                    @Override
                    public boolean isCompleted() {
                        return false;
                    }
                });
            }
        };

        user.setRegisteredCourses(registeredCourses);

        int completed = stat.getCompletedCourses();

        assertEquals(1, completed);
    }

}
