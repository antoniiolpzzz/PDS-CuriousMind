package com.pds.curiousmind.model.stat;

import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the statistics and progress tracking for a user in the system.
 * <p>
 * The {@code Stat} class stores experience points, time spent, daily activity entries, and provides methods
 * to calculate user level, completed courses, activity streaks, and more. It is mapped to the {@code stats}
 * table in the database and is associated with a single {@link User}.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Tracks experience points and calculates user level.</li>
 *   <li>Tracks total time spent in the application.</li>
 *   <li>Records daily activity entries and computes best activity streaks.</li>
 *   <li>Links to a single user and provides statistics on completed courses.</li>
 *   <li>Supports adding experience, logging activity, and updating time spent.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     Stat stat = new Stat(user);
 *     stat.addExperiencePoints(500);
 *     stat.logEntry();
 *     int level = stat.getLevel();
 *     int streak = stat.getBestStreak();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
@Table(name = "stats")
public class Stat {

    /**
     * The number of experience points required to advance one level.
     */
    private static final int EXPERIENCE_POINTS_PER_LEVEL = 100;

    /**
     * Unique identifier for the stat record (primary key, auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user's current experience points (total, not just for the current level).
     */
    @Column(name = "experience_points", nullable = false)
    private int experiencePoints;

    /**
     * The total time spent by the user in the application (in seconds).
     */
    @Column(name = "time_spent", nullable = false)
    private long timeSpent;

    /**
     * The set of dates on which the user was active (for streak and activity tracking).
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stat_entries",joinColumns = @JoinColumn(name = "stat_id"))
    private Set<LocalDate> entries = new HashSet<>();

    /**
     * The user associated with this stat record.
     */
    @OneToOne(mappedBy = "stats", optional = false)
    private User user;

    /**
     * No-argument constructor for JPA.
     */
    public Stat() {}

    /**
     * Constructs a Stat object for the given user, initializing experience and time spent to zero.
     *
     * @param user the user associated with this stat record
     */
    public Stat(User user) {
        this.experiencePoints = 0;
        this.user = user;
    }

    // GETTERS

    /**
     * Returns the unique identifier of this stat record.
     *
     * @return the stat ID
     */
    public Long getId() { return id; }

    /**
     * Returns the user associated with this stat record.
     *
     * @return the user
     */
    public User getUser() { return user; }

    /**
     * Returns the user's current level, calculated from experience points.
     *
     * @return the user's level
     */
    public int getLevel() { return (experiencePoints / EXPERIENCE_POINTS_PER_LEVEL); }

    /**
     * Returns the user's experience points towards the next level.
     *
     * @return the experience points for the current level
     */
    public int getExperiencePoints() { return experiencePoints % EXPERIENCE_POINTS_PER_LEVEL; }

    /**
     * Returns the total time spent by the user in the application (in seconds).
     *
     * @return the total time spent
     */
    public long getTimeSpent() { return timeSpent; }

    /**
     * Returns the set of dates on which the user was active.
     *
     * @return the set of activity dates (immutable)
     */
    public Set<LocalDate> getEntries() { return Collections.unmodifiableSet(entries); }

    // SETTERS

    /**
     * Sets the user's total experience points.
     *
     * @param experiencePoints the experience points to set
     */
    public void setExperiencePoints(int experiencePoints) { this.experiencePoints = experiencePoints; }

    /**
     * Sets the set of activity dates for the user.
     *
     * @param entries the set of activity dates
     */
    public void setEntries(Set<LocalDate> entries) { this.entries = entries; }

    /**
     * Sets the user associated with this stat record.
     *
     * @param user the user to set
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Sets the total time spent by the user in the application.
     *
     * @param timeSpent the time spent to set (in seconds)
     */
    public void setTimeSpent(long timeSpent) { this.timeSpent = timeSpent; }

    // ADDITIONAL METHODS

    /**
     * Returns the number of completed courses for the user.
     *
     * @return the number of completed courses
     */
    public int getCompletedCourses() {
        return user != null && user.getRegisteredCourses() != null ?
            user.getRegisteredCourses().stream()
                .filter(RegisteredCourse::isCompleted)
                .mapToInt(course -> 1)
                .sum() : 0;
    }

    /**
     * Returns the number of days the user has been active (i.e., has an entry).
     *
     * @return the number of active days
     */
    public int getDaysActive() {
        return this.entries.size();
    }

    /**
     * Returns the user's best (longest) streak of consecutive active days.
     *
     * @return the best streak length
     */
    public int getBestStreak() {
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

    /**
     * Adds experience points to the user. Returns {@code true} if successful, {@code false} if the value is negative.
     *
     * @param experiencePoints the experience points to add
     * @return {@code true} if added successfully, {@code false} otherwise
     */
    public boolean addExperiencePoints(int experiencePoints) {
        if (experiencePoints < 0) {
            return false;
        } else {
            this.experiencePoints += experiencePoints;
            return true;
        }
    }

    /**
     * Logs an activity entry for the current day. Returns {@code true} if the entry was added (i.e., not already present).
     *
     * @return {@code true} if the entry was added, {@code false} if it already existed
     */
    public boolean logEntry() {
        LocalDate entry = LocalDate.now();
        return this.entries.add(entry);
    }

    /**
     * Adds time spent to the user's total. Returns {@code true} if successful, {@code false} if the value is negative.
     *
     * @param time the time to add (in seconds)
     * @return {@code true} if added successfully, {@code false} otherwise
     */
    public boolean addTimeSpent(long time) {
        if (time < 0) {
            return false;
        } else {
            this.timeSpent += time;
            return true;
        }
    }
}
