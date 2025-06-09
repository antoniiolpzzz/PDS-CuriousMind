package com.pds.curiousmind.model.question;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {

    protected String indication;
    protected String statement;
    protected String correctAnswer;

    protected List<Option> options = new ArrayList<>();

    // CONSTRUCTORS
    public Question(String indication, String statement, String correctAnswer, List<Option> options) {
        this.indication = indication;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        if (options != null) this.options.addAll(options);
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
        return options;
    }

    // ABSTRACT METHODS
    public abstract Boolean validateAnswer(String answer);

}
