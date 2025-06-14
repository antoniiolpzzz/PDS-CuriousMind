package com.pds.curiousmind.model.user;

import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import jakarta.persistence.*;

import java.util.ArrayList;
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

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Stat stats;

    // FOTO DE USUARIO -> API "https://www.dicebear.com"
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegisteredCourse> registeredCourses = new ArrayList<>();

    // No-arg constructor for JPA
    public User() {}

    public User(String firstName, String lastName, String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        // TODO: Initialize stats with a new Stat object
        //  because it cant be done this way here due to jpa apparently
        this.stats = new Stat(this);
    }

    // GETTERS AND SETTERS
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Stat getStats() { return stats; }
    public void setStats(Stat stats) { this.stats = stats; }

    //METHODS
    public List<RegisteredCourse> getRegisteredCourses() { return registeredCourses; }
    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) { this.registeredCourses = registeredCourses; }
}
