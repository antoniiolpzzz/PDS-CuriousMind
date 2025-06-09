package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.QuestionOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Translate extends Question {

    public static final String DELIMITER = " ";

    public Translate(String indication, String statement, String correctAnswer, List<QuestionOption> options) {

        super(indication, statement, correctAnswer, options);
        Arrays.stream(correctAnswer.split(DELIMITER))
                .map(QuestionOption::new)
                .forEach(this.options::add);
    }

    @Override
    public Boolean validateAnswer(String answer) {
        return this.correctAnswer.trim().equalsIgnoreCase(answer.trim());
    }
}
