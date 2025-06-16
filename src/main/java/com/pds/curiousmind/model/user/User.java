package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.strategy.StrategyType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "stat_id", nullable = false, unique = true)
    private Stat stats;

    // FOTO DE USUARIO -> API "https://www.dicebear.com"
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RegisteredCourse> registeredCourses = new ArrayList<>();

    // No-arg constructor for JPA
    public User() {}

    public User(String fullName, String email, String password, String username) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.stats = new Stat(this);
    }

    // GETTERS
    public Long getId() { return id; }

    public String getFullName() { return fullName; }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public Stat getStats() { return stats; }


    // SETTERS
    public void setEmail(String email) { this.email = email; }

    public void setFullName(String firstName) { this.fullName = firstName; }

    public void setPassword(String password) { this.password = password; }

    public void setUsername(String username) { this.username = username; }

    public void setStats(Stat stats) { this.stats = stats; }

    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) { this.registeredCourses = registeredCourses; }


    //METHODS
    public List<RegisteredCourse> getRegisteredCourses() { return Collections.unmodifiableList(registeredCourses); }

    public void registerInCourse(Course course, StrategyType strategy) {
        RegisteredCourse registeredCourse = new RegisteredCourse(this, course, strategy);
        this.registeredCourses.add(registeredCourse);
    }

}
