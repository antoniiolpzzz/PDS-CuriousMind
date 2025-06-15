package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.Entity;
import java.util.Collections;
import java.util.List;

@Entity
public class FillTheGap extends Question {

    public FillTheGap(String indication, String statement, String correctAnswer) {
        super(indication, statement, correctAnswer);
    }

    public FillTheGap() {
        super();
    }

    @Override
    public void setOptions(List<Option> options) {
        // Do nothing: FillTheGap does not support options
    }

    @Override
    public List<Option> getOptions() {
        return Collections.emptyList();
    }
}
