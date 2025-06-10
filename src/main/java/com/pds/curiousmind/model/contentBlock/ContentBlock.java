package com.pds.curiousmind.model.contentBlock;

import java.util.ArrayList;
import java.util.Collections;

public class ContentBlock {
    private String name;
    private Difficulty difficulty;

    private List<Question> questions;

    // CONSTRUCTORS
    public ContentBlock(String name, List<Question> questions, Difficulty difficultyLevel) {
        this.name = name;
        this.questions = new ArrayList<Question>(questions);
        this.difficulty = difficultyLevel;
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

}
