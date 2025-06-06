package com.pds.curiousmind.model.registeredContentBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.pds.curiousmind.model.contentBlock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.Difficulty;


public class RegisteredContentBlock {

    private String name;
    private List<Question> questions = new ArrayList<>();
    private Difficulty difficulty;
    private boolean isCompleted;

    private ContentBlock contentBlock;


    public RegisteredContentBlock(ContentBlock contentBlock) {
        this.name = contentBlock.name;
        this.difficulty = contentBlock.difficultyLevel;
        this.isCompleted = false;

        for (var question : contentBlock.questions) {
            this.questions.add(question);
        }
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