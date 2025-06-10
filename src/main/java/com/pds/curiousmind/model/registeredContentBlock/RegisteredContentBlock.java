package com.pds.curiousmind.model.registeredContentBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.pds.curiousmind.model.contentBlock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.Difficulty;


public class RegisteredContentBlock {
    
    private ContentBlock contentBlock;
    private boolean isCompleted;

    // CONSTRUCTOR
    public RegisteredContentBlock(ContentBlock contentBlock) {
        this.isCompleted = false;
    }

    // GETTERS
    public String getName() {
        return this.contentBlock.getName();
    }

    public List<Question> getQuestions() {
        return this.contentBlock.getQuestions();
    }

    public Difficulty getDifficulty() {
        return this.contentBlock.getDifficulty();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // METHODS
    public void markAsCompleted() {
            this.isCompleted = true;
    }
    public void resetContentBlock() {
        this.isCompleted = false;
    }
}