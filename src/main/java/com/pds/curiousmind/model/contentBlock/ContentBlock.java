package com.pds.curiousmind.model.contentBlock;

import com.pds.curiousmind.model.course.Course;

import java.util.ArrayList;

public class ContentBlock {
    private String name;

    private List<Question> questions = new ArrayList<>();
    private Difficulty difficulty;

    // CONSTRUCTORS
    public ContentBlock(String name, List<Question> questions, Difficulty difficultyLevel) {
        this.name = name;

        for (Question question : questions) {
            this.questions.add(question);
        }
        this.difficulty = difficultyLevel;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }

    // METHODS

}
