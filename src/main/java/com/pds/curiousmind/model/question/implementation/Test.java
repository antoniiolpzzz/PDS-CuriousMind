package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.QuestionOption;

import java.util.List;

public class Test extends Question {

    public Test(String indication, String statement, String correctAnswer, List<QuestionOption> options) {
        super(indication, statement, correctAnswer, options);
    }

    @Override
    public Boolean validateAnswer(String answer) {
        return this.correctAnswer.trim().equalsIgnoreCase(answer.trim());
    }
}
