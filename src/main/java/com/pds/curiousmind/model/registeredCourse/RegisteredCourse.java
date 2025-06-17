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
    public Long getId() { return id; }

    public User getUser() { return user; }

    public Course getCourse() { return course; }

    public StrategyType getStrategy() { return strategy; }

    public List<RegisteredContentBlock> getRegisteredContentBlocks() { return Collections.unmodifiableList(registeredContentBlocks); }


    // SETTERS
    public void setUser(User user) { this.user = user; }

    public void setStrategy(StrategyType strategyID) { this.strategy = strategyID; }

    public void setRegisteredContentBlocks(List<RegisteredContentBlock> registeredContentBlocks) { this.registeredContentBlocks = registeredContentBlocks; }

    public void setCourse(Course course) { this.course = course; }


    // ADDITIONAL METHODS
    public String getName() { return this.course != null ? this.course.getName() : null; }

    public String getDescription() { return this.course != null ? this.course.getDescription() : null; }

    public String getImageURL() { return this.course != null ? this.course.getImageURL() : null; }

    public double getProgress() {
        return this.registeredContentBlocks.isEmpty() ? 0.0 :
                ((double) getCompletedBlocksCount() * 100.0 / registeredContentBlocks.size());
    }

    public boolean isCompleted() {
        return getCompletedBlocksCount() == registeredContentBlocks.size();
    }

    public int getCompletedBlocksCount() {
        return (int) registeredContentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }

}
