package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.strategy.StrategyType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a user in the CuriousMind application.
 * <p>
 * This class is a JPA entity mapped to the "users" table and contains user profile information,
 * authentication credentials, statistics, and the list of registered courses. It also provides
 * methods for course registration, experience tracking, and time logging.
 * </p>
 *
 * <p>
 * The entity graph annotations are used to optimize fetching of related entities such as statistics,
 * registered courses, content blocks, questions, and options for efficient data retrieval.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     User user = new User("John Doe", "john@example.com", "password", "johndoe");
 *     user.registerInCourse(course, StrategyType.SEQUENTIAL);
 *     user.addExperiencePoints(100);
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 */
@Entity
@Table(name = "users")
@NamedEntityGraph(
    name = "User.fullGraph",
    attributeNodes = {
        @NamedAttributeNode("stats"),
        @NamedAttributeNode(value = "registeredCourses", subgraph = "registeredCoursesGraph")
    },
    subgraphs = {
        @NamedSubgraph(
            name = "registeredCoursesGraph",
            attributeNodes = {
                @NamedAttributeNode(value = "registeredContentBlocks", subgraph = "registeredContentBlocksGraph"),
                @NamedAttributeNode(value = "course", subgraph = "courseGraph")
            }
        ),
        @NamedSubgraph(
            name = "registeredContentBlocksGraph",
            attributeNodes = {
                @NamedAttributeNode(value = "contentBlock", subgraph = "contentBlockGraph")
            }
        ),
        @NamedSubgraph(
            name = "contentBlockGraph",
            attributeNodes = {
                @NamedAttributeNode(value = "questions", subgraph = "questionGraph")
            }
        ),
        @NamedSubgraph(
            name = "questionGraph",
            attributeNodes = {
                @NamedAttributeNode("options")
            }
        ),
        @NamedSubgraph(
            name = "courseGraph",
            attributeNodes = {
                @NamedAttributeNode(value = "contentBlocks", subgraph = "contentBlockGraph")
            }
        )
    }
)
public class User {
    /**
     * Unique identifier for the user (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the user.
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * Email address of the user.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Encrypted password for authentication.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Unique username for the user.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Statistics associated with the user (one-to-one relationship).
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "stat_id", nullable = false, unique = true)
    private Stat stats;

    /**
     * List of courses the user is registered in (one-to-many relationship).
     * <p>
     *     This list is eagerly fetched and managed by the user entity.
     * </p>
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RegisteredCourse> registeredCourses = new ArrayList<>();

    /**
     * No-argument constructor required by JPA.
     */
    public User() {}

    /**
     * Constructs a new user with the specified details and initializes statistics.
     *
     * @param fullName the user's full name
     * @param email the user's email address
     * @param password the user's password
     * @param username the user's username
     */
    public User(String fullName, String email, String password, String username) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.stats = new Stat(this);
    }

    // GETTERS
    /**
     * Returns the user's unique identifier.
     * @return the user ID
     */
    public Long getId() { return id; }

    /**
     * Returns the user's full name.
     * @return the full name
     */
    public String getFullName() { return fullName; }

    /**
     * Returns the user's email address.
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Returns the user's username.
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Returns the user's password.
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Returns the user's statistics.
     * @return the stats object
     */
    public Stat getStats() { return stats; }

    // SETTERS
    /**
     * Sets the user's email address.
     * @param email the new email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Sets the user's full name.
     * @param fullName the new full name
     */
    public void setFullName(String fullName) { this.fullName = fullName; }

    /**
     * Sets the user's password.
     * @param password the new password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Sets the user's username.
     * @param username the new username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Sets the user's statistics.
     * @param stats the new stats object
     */
    public void setStats(Stat stats) { this.stats = stats; }

    /**
     * Sets the list of registered courses for the user.
     * @param registeredCourses the new list of registered courses
     */
    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) { this.registeredCourses = registeredCourses; }

    // METHODS
    /**
     * Returns an unmodifiable list of the user's registered courses.
     * @return the list of registered courses
     */
    public List<RegisteredCourse> getRegisteredCourses() { return Collections.unmodifiableList(registeredCourses); }

    /**
     * Registers the user in a course with the specified strategy.
     *
     * @param course the course to register in
     * @param strategy the learning strategy to use
     */
    public void registerInCourse(Course course, StrategyType strategy) {
        RegisteredCourse registeredCourse = new RegisteredCourse(this, course, strategy);
        this.registeredCourses.add(registeredCourse);
    }

    /**
     * Logs a user entry (e.g., for tracking login streaks).
     * @return true if the log entry was successful, false otherwise
     */
    public boolean logEntry() {
        return this.stats.logEntry();
    }

    /**
     * Adds experience points to the user's statistics.
     * @param points the number of experience points to add
     * @return true if the operation was successful, false otherwise
     */
    public boolean addExperiencePoints(int points) {
        return this.stats.addExperiencePoints(points);
    }

    /**
     * Adds time spent to the user's statistics.
     * @param time the time spent (in milliseconds or seconds, depending on implementation)
     * @return true if the operation was successful, false otherwise
     */
    public boolean addTimeSpent(long time) {
        return this.stats.addTimeSpent(time);
    }
}
