package com.pds.curiousmind.model.question;

import com.pds.curiousmind.model.question.option.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public abstract class Question {

    protected String indication;
    protected String statement;
    protected String correctAnswer;

    protected List<Option> options;

    // CONSTRUCTORS
    public Question(String indication, String statement, String correctAnswer, List<Option> options) {
        this.indication = indication;
        this.statement = statement;
        this.correctAnswer = correctAnswer;

        if (options != null) {
            this.options = new ArrayList<>(options);
        } else {
            this.options = new ArrayList<>();
        }
    }

    public Question(String indication, String statement, String correctAnswer) {
        this(indication, statement, correctAnswer, null);
    }

    // GETTERS
    public String getIndication() {
        return indication;
    }

    public String getStatement() {
        return statement;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<Option> getOptions() {
        return Collections.unmodifiableList(options);
    }

    // ABSTRACT METHODS
    public abstract boolean validateAnswer(String answer);

}
