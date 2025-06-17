package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public enum SpacedRepetition implements Strategy {
    INSTANCE;

    private static final int REPETITION_NUMBER = 3;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SPACED_REPETITION;
    }

    @Override
    public List<Question> getProcessedQuestions(RegisteredContentBlock block) {
        List<Question> questions = block.getQuestions();
        List<Question> result = new ArrayList<>();

        IntStream.range(0, questions.size()).forEach(i -> {
            result.add(questions.get(i));
            if ((i + 1) % REPETITION_NUMBER == 0) {
                int repeatIndex = (i + 1) / REPETITION_NUMBER - 1;
                if (repeatIndex < questions.size()) {
                    result.add(questions.get(repeatIndex));
                }
            }
        });

        return result;
    }
}
