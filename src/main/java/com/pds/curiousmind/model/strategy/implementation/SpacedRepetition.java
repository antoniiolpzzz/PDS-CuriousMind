package com.pds.curiousmind.model.strategy.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SpacedRepetition {

    public static final int REPETITION_NUMBER = 3;

    public List<Question> getQuestionsBlock(Bloque bloque){

        List<Question> questions = bloque.getQuestions();
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
