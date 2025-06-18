package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StatTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");
        Stat stat = user.getStats();

        assertNotNull(stat);
        assertEquals(0, stat.getExperiencePoints());
        assertEquals(0, stat.getLevel());
        assertEquals(0, stat.getTimeSpent());
        assertEquals(0, stat.getDaysActive());
    }

    @Test
    void testAddExperiencePoints() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");
        Stat stat = user.getStats();

        assertTrue(stat.addExperiencePoints(1500));
        assertEquals(500, stat.getExperiencePoints());
        assertEquals(1, stat.getLevel());

        assertFalse(stat.addExperiencePoints(-100));
    }

    @Test
    void testAddTimeSpent() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");
        Stat stat = user.getStats();

        assertTrue(stat.addTimeSpent(300));
        assertEquals(300, stat.getTimeSpent());

        assertFalse(stat.addTimeSpent(-50));
    }

    @Test
    void testLogEntryAndDaysActive() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");
        Stat stat = user.getStats();

        int initialDays = stat.getDaysActive();

        assertTrue(stat.logEntry());
        assertEquals(initialDays + 1, stat.getDaysActive());

        // log again same day → false
        assertFalse(stat.logEntry());
    }

    @Test
    void testBestStreak() {
        User user = new User("Ana", "ana@test.com", "1234", "ana");
        Stat stat = user.getStats();

        // No entries
        stat.setEntries(Set.of());
        assertEquals(0, stat.getBestStreak());

        // 1 day
        stat.setEntries(Set.of(LocalDate.of(2024,6,15)));
        assertEquals(1, stat.getBestStreak());

        // 3 consecutive days
        stat.setEntries(Set.of(
                LocalDate.of(2024,6,15),
                LocalDate.of(2024,6,16),
                LocalDate.of(2024,6,17)
        ));
        assertEquals(3, stat.getBestStreak());

        // 2 days + gap
        stat.setEntries(Set.of(
                LocalDate.of(2024,6,10),
                LocalDate.of(2024,6,11),
                LocalDate.of(2024,6,13),
                LocalDate.of(2024,6,14)
        ));
        assertEquals(2, stat.getBestStreak());
    }
}
