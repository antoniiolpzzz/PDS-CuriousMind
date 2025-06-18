package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * The {@code Shuffled} enum implements a strategy for shuffling questions in a content block.
 * This strategy randomizes the order of questions to provide a varied learning experience each time.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 *     List<Question> shuffled = Shuffled.INSTANCE.getProcessedQuestions(block);
 * </pre>
 * </p>
 *
 * <p>
 * This strategy is useful in educational applications where presenting questions in a random order
 * can help prevent memorization based on sequence and encourage deeper learning.
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.strategy.Strategy
 * @see com.pds.curiousmind.model.strategy.StrategyType
 */
public enum Shuffled implements Strategy {
    /**
     * Singleton instance of the shuffled strategy.
     */
    INSTANCE;

    /**
     * Returns the type of this strategy.
     *
     * @return the {@link StrategyType} representing shuffled order
     */
    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SHUFFLED;
    }

    /**
     * Returns a shuffled list of questions from the given {@link RegisteredContentBlock}.
     * The original order of questions is randomized for each call.
     *
     * @param block the content block containing the questions to shuffle
     * @return a new list of questions in random order
     */
    @Override
    public List<Question> getProcessedQuestions(RegisteredContentBlock block){
        List<Question> questions = new ArrayList<Question>(block.getQuestions());
        Collections.shuffle(questions);
        return questions;
    }
}
