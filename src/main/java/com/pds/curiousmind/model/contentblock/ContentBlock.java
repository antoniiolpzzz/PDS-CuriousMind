package com.pds.curiousmind.model.contentblock;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.question.Question;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "content_blocks")
public class ContentBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "contentBlock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    // CONSTRUCTORS
    public ContentBlock() {
        this.questions = new ArrayList<>();
    }

    public ContentBlock(String name, List<Question> questions, Difficulty difficultyLevel, Course course) {
        this.name = name;
        this.questions = new ArrayList<>(questions);
        this.difficulty = difficultyLevel;
        this.course = course;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Course getCourse() {
        return course;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
