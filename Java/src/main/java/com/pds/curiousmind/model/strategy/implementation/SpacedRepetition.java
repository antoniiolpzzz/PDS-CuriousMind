package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <p>
 * The {@code SpacedRepetition} enum implements the spaced repetition learning strategy.
 * This strategy is used to reinforce learning by repeating questions at specific intervals.
 * </p>
 *
 * <p>
 * For every {@value #REPETITION_NUMBER} questions, the strategy repeats a previous question
 * to help users retain information more effectively. This is commonly used in educational
 * applications to optimize memory retention.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 *     List<Question> processed = SpacedRepetition.INSTANCE.getProcessedQuestions(block);
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.strategy.Strategy
 * @see com.pds.curiousmind.model.strategy.StrategyType
 */
public enum SpacedRepetition implements Strategy {
    /**
     * Singleton instance of the spaced repetition strategy.
     */
    INSTANCE;

    /**
     * The number of questions after which a previous question is repeated.
     */
    private static final int REPETITION_NUMBER = 3;

    /**
     * Returns the type of this strategy.
     *
     * @return the {@link StrategyType} representing spaced repetition
     */
    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SPACED_REPETITION;
    }

    /**
     * Processes the questions in the given {@link RegisteredContentBlock} according to the spaced repetition algorithm.
     * For every {@value #REPETITION_NUMBER} questions, a previous question is repeated in the result list.
     *
     * @param block the content block containing the questions to process
     * @return a list of questions with spaced repetition applied
     */
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
