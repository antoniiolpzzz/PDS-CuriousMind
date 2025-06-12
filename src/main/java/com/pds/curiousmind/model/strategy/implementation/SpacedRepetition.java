package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public enum SpacedRepetition implements Strategy {
    INSTANCE;

    public static final int REPETITION_NUMBER = 3;

    @Override
    public List<Question> getQuestionsBlock(ContentBlock block){

        List<Question> questions = block.getQuestions();
        List<Question> result = new ArrayList<Question>();

        IntStream.range(0, questions.size()).forEach(i -> {
            result.add(questions.get(i));
            if ((i + 1) % REPETITION_NUMBER == 0) {
                result.add(questions.get(i % REPETITION_NUMBER));
            }
        });

        return result;

    }
}
