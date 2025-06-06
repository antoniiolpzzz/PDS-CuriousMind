package com.pds.curiousmind.model.registeredContentBlock;

import java.util.List;
import com.pds.curiousmind.model.contentBlock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.Difficulty;


public class RegisteredContentBlock {

    private String name;
    private List<Question> questions;
    private Difficulty difficulty;
    private int progress;
    private boolean isCompleted;
    private int lives; // Default lives



    public RegisteredContentBlock(ContentBlock contentBlock) {
        this.name = contentBlock.name;
        this.questions = contentBlock.questions;
        this.difficulty = contentBlock.difficultyLevel;
        this.progress = 0; // Default progress
        this.isCompleted = false; // Default completion status
        this.lives = 5; // Default lives
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

    public int getProgress() {
        return progress;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getLives() {
        return lives;
    }

    // METHODS

    public void updateProgress() {
        this.progress = getCompletedQuestionsCount()/questions.size() * 100;
    }

    public void markAsCompleted(Boolean isCorrect) {
            this.isCompleted = true;
            updateProgress();

    }

    public void quitCOntentBlock() {
        this.isCompleted = false;
        this.progress = 0; // Reset progress
        this.lives = 5; // Reset lives
    }



}