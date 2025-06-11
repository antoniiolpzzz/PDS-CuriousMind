package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.user.User;

import java.time.LocalDate;
import java.util.Set;

public class Stat {

    private static final int EXPERIENCE_POINTS_PER_LEVEL = 1000;

    private int experiencePoints;
    private final Set<LocalDate> entries;

    private User user;

    // CONSTRUCTORS
    public Stat(User user) {
        this.experiencePoints = 0;
        this.entries = new java.util.TreeSet<>();
    }

    // GETTERS AND SETTERS
    public int getLevel() {
        return (experiencePoints / EXPERIENCE_POINTS_PER_LEVEL);
    }

    public int getExperiencePoints() {
        return experiencePoints % EXPERIENCE_POINTS_PER_LEVEL;
    }

    public int getCompletedCourses() {
        return user.getRegisteredCourses().stream()
                .filter(course -> course.isCompleted())
                .mapToInt(course -> 1)
                .sum();

    }

    public int getDaysActive() {
        return this.entries.size();
    }

    public int getBestStreak() {
        if (entries.isEmpty()) return 0;
        int maxStreak = 1;
        int currentStreak = 1;
        LocalDate previous = null;
        for (LocalDate current : entries) {
            if (previous != null) {
                if (previous.plusDays(1).equals(current)) {
                    currentStreak++;
                    maxStreak = Math.max(maxStreak, currentStreak);
                } else if (!previous.equals(current)) {
                    currentStreak = 1;
                }
            }
            previous = current;
        }
        return maxStreak;
    }

    //METHODS
    public boolean addExperiencePoints(int experiencePoints) {
        if (experiencePoints < 0) {
            return false;

        } else {
            this.experiencePoints += experiencePoints;
            return true;
        }
    }

    public boolean logEntry() {
        LocalDate entry = LocalDate.now();
        return this.entries.add(entry);
    }

}

