package com.pds.curiousmind.model.contentblock;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "content_block_id", nullable = false)
    private List<Question> questions;

    // CONSTRUCTORS
    public ContentBlock() {
        this.questions = new ArrayList<>();
    }

    public ContentBlock(String name, List<Question> questions, Difficulty difficultyLevel) {
        this.name = name;
        this.questions = new ArrayList<>(questions);
        this.difficulty = difficultyLevel;
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

}
