package com.pds.curiousmind.model.question;

import java.util.List;

public abstract class Question {

    protected String indication;
    protected String statement;
    protected String correctAnswer;

    protected List<QuestionOption> options;


    public Question(String indication, String statement, String correctAnswer, List<QuestionOption> options) {
        this.indication = indication;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }

    public String getIndication() {
        return indication;
    }

    public String getStatement() {
        return statement;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public abstract Boolean validateAnswer(String answer);

}
