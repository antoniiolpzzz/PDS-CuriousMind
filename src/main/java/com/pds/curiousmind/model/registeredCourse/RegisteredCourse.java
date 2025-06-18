package com.pds.curiousmind.model.registeredCourse;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a registered course for a user, tracking their progress and strategy within a specific course.
 * <p>
 * This class links a {@link Course} to a {@link User} and manages the user's chosen learning strategy and
 * the set of {@link RegisteredContentBlock}s that represent the user's progress through the course's content blocks.
 * It provides methods to access course metadata, progress, and completion status.
 * <p>
 * Mapped to the {@code registered_courses} table in the database using JPA annotations.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Links a user to a course and tracks their chosen learning strategy.</li>
 *   <li>Maintains a list of registered content blocks for tracking progress.</li>
 *   <li>Provides methods to get course metadata (name, description, image URL).</li>
 *   <li>Calculates progress and completion status based on completed content blocks.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     RegisteredCourse rc = new RegisteredCourse(user, course, StrategyType.SEQUENTIAL);
 *     double progress = rc.getProgress();
 *     boolean completed = rc.isCompleted();
 *     List&lt;RegisteredContentBlock&gt; blocks = rc.getRegisteredContentBlocks();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
@Table(name = "registered_courses")
public class RegisteredCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "strategy", nullable = false)
    private StrategyType strategy;

    @OneToMany(mappedBy = "registeredCourse", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();

    // No-arg constructor for JPA
    public RegisteredCourse() {}

    /**
     * Constructs a RegisteredCourse instance for a given user, course, and strategy.
     * <p>
     * Initializes the registered content blocks based on the course's content blocks.
     *
     * @param user     The user registering for the course.
     * @param course   The course being registered.
     * @param strategy The learning strategy chosen by the user.
     */
    public RegisteredCourse(User user, Course course, StrategyType strategy) {
        this.user = user;
        this.course = course;
        this.strategy = strategy;
        this.registeredContentBlocks = course.getContentBlocks().stream()
            .map(cb -> {
                return new RegisteredContentBlock(this, cb);
            })
            .collect(Collectors.toCollection(ArrayList::new));
    }

    // GETTERS
    /**
     * Returns the unique identifier of the registered course.
     *
     * @return the id of the registered course.
     */
    public Long getId() { return id; }

    /**
     * Returns the user associated with this registered course.
     *
     * @return the user.
     */
    public User getUser() { return user; }

    /**
     * Returns the course associated with this registered course.
     *
     * @return the course.
     */
    public Course getCourse() { return course; }

    /**
     * Returns the learning strategy chosen for this registered course.
     *
     * @return the strategy.
     */
    public StrategyType getStrategy() { return strategy; }

    /**
     * Returns an unmodifiable list of registered content blocks for this course.
     *
     * @return the list of registered content blocks.
     */
    public List<RegisteredContentBlock> getRegisteredContentBlocks() { return Collections.unmodifiableList(registeredContentBlocks); }


    // SETTERS
    /**
     * Sets the user for this registered course.
     *
     * @param user the user to be set.
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Sets the learning strategy for this registered course.
     *
     * @param strategyID the strategy to be set.
     */
    public void setStrategy(StrategyType strategyID) { this.strategy = strategyID; }

    /**
     * Sets the list of registered content blocks for this course.
     *
     * @param registeredContentBlocks the list of registered content blocks to be set.
     */
    public void setRegisteredContentBlocks(List<RegisteredContentBlock> registeredContentBlocks) { this.registeredContentBlocks = registeredContentBlocks; }

    /**
     * Sets the course for this registered course.
     *
     * @param course the course to be set.
     */
    public void setCourse(Course course) { this.course = course; }


    // ADDITIONAL METHODS
    /**
     * Returns the name of the course.
     *
     * @return the course name, or null if the course is not set.
     */
    public String getName() { return this.course != null ? this.course.getName() : null; }

    /**
     * Returns the description of the course.
     *
     * @return the course description, or null if the course is not set.
     */
    public String getDescription() { return this.course != null ? this.course.getDescription() : null; }

    /**
     * Returns the image URL of the course.
     *
     * @return the course image URL, or null if the course is not set.
     */
    public String getImageURL() { return this.course != null ? this.course.getImageURL() : null; }

    /**
     * Calculates and returns the progress percentage of the registered course.
     * <p>
     * Progress is calculated based on the number of completed content blocks relative to the total number of content blocks.
     *
     * @return the progress percentage, ranging from 0.0 to 100.0.
     */
    public double getProgress() {
        return this.registeredContentBlocks.isEmpty() ? 0.0 :
                ((double) getCompletedBlocksCount() * 100.0 / registeredContentBlocks.size());
    }

    /**
     * Checks if the registered course is completed.
     * <p>
     * A course is considered completed when all its content blocks are marked as completed.
     *
     * @return true if the course is completed, false otherwise.
     */
    public boolean isCompleted() {
        return getCompletedBlocksCount() == registeredContentBlocks.size();
    }

    /**
     * Counts and returns the number of completed content blocks in the registered course.
     *
     * @return the count of completed content blocks.
     */
    public int getCompletedBlocksCount() {
        return (int) registeredContentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredCourse that = (RegisteredCourse) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RegisteredCourse{" +
                "id=" + id +
                ", course=" + (course != null ? course.getName() : null) +
                ", user=" + (user != null ? user.getUsername() : null) +
                '}';
    }

}
