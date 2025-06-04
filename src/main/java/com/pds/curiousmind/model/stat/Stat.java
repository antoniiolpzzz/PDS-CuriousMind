package com.pds.curiousmind.model.stat;

public class Stat {

    private int level;
    private int experiencePoints;
    private int completedCourses;
    private int bestStell;
    private int daysActive;

    // private user user; ???????????

    public Stat(int level, int experiencePoints, int completedCourses, int bestStell, int daysActive) {
        this.level = level;
        this.experiencePoints = experiencePoints;
        this.completedCourses = completedCourses;
        this.bestStell = bestStell;
        this.daysActive = daysActive;
    }

    public int getLevel() {
        return level;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getCompletedCourses() {
        return completedCourses;
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
