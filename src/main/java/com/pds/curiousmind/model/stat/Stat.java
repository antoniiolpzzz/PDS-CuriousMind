package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.user.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Stat {

    private static final int EXPERIENCE_POINTS_PER_LEVEL = 1000;

    //private int level; // TODO: determine, since this is a dynamically calculated value, how it is calculated
    private int experiencePoints;
    private final Set<LocalDate> entries = new HashSet<>();
    //private int completedCourses; //TODO: this is a dinamically calculated value, not stored in the database
    //private int bestStell; //TODO: this is a dinamically calculated value, not stored in the database
    //private int daysActive; //TODO: this is a dinamically calculated value, not stored in the database
    //TODO: we may need a logIn timestamp-set to calculate daysActive and bestStell

    private User user;

    public Stat(int experiencePoints) {
        this.experiencePoints = experiencePoints;

    }

    public int getLevel() {
        return experiencePoints / EXPERIENCE_POINTS_PER_LEVEL;
    }

    public int getExperiencePoints() {
        return experiencePoints % EXPERIENCE_POINTS_PER_LEVEL;
    }

    public int getCompletedCourses() {
        return user.getStats().getCompletedCourses();
    }

    public int getBestStell() {
        return bestStell;
    }

    public int getDaysActive() {
        return daysActive;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public void setCompletedCourses(int completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void setBestStell(int bestStell) {
        this.bestStell = bestStell;
    }

    public void setDaysActive(int daysActive) {
        this.daysActive = daysActive;
    }


}
