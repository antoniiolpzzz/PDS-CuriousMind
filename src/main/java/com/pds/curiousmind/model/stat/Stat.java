package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stats")
public class Stat {

    private static final int EXPERIENCE_POINTS_PER_LEVEL = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "experience_points", nullable = false)
    private int experiencePoints;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stat_entries",joinColumns = @JoinColumn(name = "stat_id"))
    private Set<LocalDate> entries = new HashSet<>();

    @OneToOne(mappedBy = "stats", optional = false)
    private User user;

    // No-arg constructor for JPA
    public Stat() {}

    public Stat(User user) {
        this.experiencePoints = 0;
        this.user = user;
    }

    // GETTERS
    public Long getId() { return id; }

    public User getUser() { return user; }

    public int getLevel() { return (experiencePoints / EXPERIENCE_POINTS_PER_LEVEL); }

    public int getExperiencePoints() { return experiencePoints % EXPERIENCE_POINTS_PER_LEVEL; }


    // SETTERS
    public void setExperiencePoints(int experiencePoints) { this.experiencePoints = experiencePoints; }

    public Set<LocalDate> getEntries() { return entries; }

    public void setEntries(Set<LocalDate> entries) { this.entries = entries; }

    public void setUser(User user) { this.user = user; }


    // ADDITIONAL METHODS
    public int getCompletedCourses() {
        return user != null && user.getRegisteredCourses() != null ?
            user.getRegisteredCourses().stream()
                .filter(course -> course.isCompleted())
                .mapToInt(course -> 1)
                .sum() : 0;
    }

    public int getDaysActive() {
        return this.entries.size();
    }

    public int getBestStreak() {
        //TODO: This need to be revised
        if (entries == null || entries.isEmpty()) return 0;
        int maxStreak = 1;
        int currentStreak = 1;
        LocalDate previous = null;
        List<LocalDate> sortedEntries = new java.util.ArrayList<>(entries);
        java.util.Collections.sort(sortedEntries);
        for (LocalDate current : sortedEntries) {
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
        return Math.max(maxStreak, currentStreak);
    }

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
