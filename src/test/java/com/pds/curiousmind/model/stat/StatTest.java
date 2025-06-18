package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatTest {

    @Test
    void testAddExperiencePoints() {
        Stat stat = new Stat();

        assertTrue(stat.addExperiencePoints(1500));
        assertEquals(1, stat.getLevel());
        assertEquals(500, stat.getExperiencePoints());

        assertFalse(stat.addExperiencePoints(-100));
    }

    @Test
    void testGetCompletedCourses() {
        User user = mock(User.class);
        RegisteredCourse course1 = mock(RegisteredCourse.class);
        RegisteredCourse course2 = mock(RegisteredCourse.class);
        RegisteredCourse course3 = mock(RegisteredCourse.class);

        when(course1.isCompleted()).thenReturn(true);
        when(course2.isCompleted()).thenReturn(false);
        when(course3.isCompleted()).thenReturn(true);

        when(user.getRegisteredCourses()).thenReturn(List.of(course1, course2, course3));

        Stat stat = new Stat(user);

        assertEquals(2, stat.getCompletedCourses());
    }

    @Test
    void testGetDaysActive() {
        Stat stat = new Stat();

        stat.setEntries(Set.of(
                LocalDate.of(2024, 6, 10),
                LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 6, 13)
        ));

        assertEquals(3, stat.getDaysActive());
    }

    @Test
    void testLogEntry() {
        Stat stat = new Stat();

        int initialSize = stat.getDaysActive();

        assertTrue(stat.logEntry());
        assertEquals(initialSize + 1, stat.getDaysActive());

        // Si se vuelve a llamar el mismo día, no debe sumar de nuevo
        assertFalse(stat.logEntry());
    }

    @Test
    void testBestStreak() {
        Stat stat = new Stat();

        // caso vacío
        stat.setEntries(Set.of());
        assertEquals(0, stat.getBestStreak());

        // caso 1 solo día
        stat.setEntries(Set.of(LocalDate.of(2024, 6, 10)));
        assertEquals(1, stat.getBestStreak());

        // caso 2 días consecutivos
        stat.setEntries(Set.of(
                LocalDate.of(2024, 6, 10),
                LocalDate.of(2024, 6, 11)
        ));
        assertEquals(2, stat.getBestStreak());

        // caso 3 días seguidos, con huecos
        stat.setEntries(Set.of(
                LocalDate.of(2024, 6, 10),
                LocalDate.of(2024, 6, 11),
                LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 6, 15),
                LocalDate.of(2024, 6, 17)
        ));
        assertEquals(3, stat.getBestStreak());

        // caso streak de 4 días
        stat.setEntries(Set.of(
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                LocalDate.of(2024, 6, 3),
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 6)
        ));
        assertEquals(4, stat.getBestStreak());
    }
}
