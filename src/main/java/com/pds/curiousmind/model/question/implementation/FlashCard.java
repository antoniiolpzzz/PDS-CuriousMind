package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.Option;

import java.util.List;

public class FlashCard extends Question {

    public FlashCard(String indication, String statement, String correctAnswer, List<Option> options) {
        super(indication, statement, correctAnswer, options);
    }

    @Override
    public Boolean validateAnswer(String answer) {
        return this.correctAnswer.trim().equalsIgnoreCase(answer.trim());
    }
}
