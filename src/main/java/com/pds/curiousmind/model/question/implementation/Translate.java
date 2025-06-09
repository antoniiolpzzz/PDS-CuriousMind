package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;

import java.util.Arrays;
import java.util.List;

public class Translate extends Question {

    public static final String DELIMITER = " ";

    public Translate(String indication, String statement, String correctAnswer, List<Option> options) {

        super(indication, statement, correctAnswer, options);
        Arrays.stream(correctAnswer.split(DELIMITER))
                .map(Option::new)
                .forEach(this.options::add);
    }

}
