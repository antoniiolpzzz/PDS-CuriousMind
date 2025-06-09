package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;

public class FillTheGap extends Question {


    public FillTheGap(String indication, String statement, String correctAnswer) {
        super(indication, statement, correctAnswer);
    }

    @Override
    public boolean validateAnswer(String answer) {
        return this.correctAnswer.trim().equalsIgnoreCase(answer.trim());
    }
}
