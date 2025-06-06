package com.pds.curiousmind.model.registeredContentBlock;

import java.util.List;
import com.pds.curiousmind.model.contentBlock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.Difficulty;


public class RegisteredContentBlock {

    private String name;
    private List<Question> questions;
    private Difficulty difficulty;
    private boolean isCompleted;


    public RegisteredContentBlock(ContentBlock contentBlock) {
        this.name = contentBlock.name;
        this.questions = contentBlock.questions;
        this.difficulty = contentBlock.difficultyLevel;
        this.isCompleted = false; // Default completion status
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

    public boolean isCompleted() {
        return isCompleted;
    }

    // METHODS

    public void markAsCompleted(Boolean isCorrect) {
            this.isCompleted = true;
    }

    public void quitContentBlock() {
        this.isCompleted = false;

    }



}