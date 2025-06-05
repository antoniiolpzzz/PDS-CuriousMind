package com.pds.curiousmind.model.contentBlock;

import com.pds.curiousmind.model.course.Course;

public class ContentBlock {
    private String name;
    private List<Question> questions;
    private Difficulty difficulty;

    public ContentBlock(String name, List<Question> questions, Difficulty difficultyLevel) {
        this.name = name;
        this.questions = questions;
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
    public ContentBlock create(String name, List<Question> questions, Difficulty difficultyLevel) {
        return new ContentBlock(name, questions, difficultyLevel);
    }


}
