package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;

import java.util.List;

/**
 * <p>
 * The {@code Sequential} enum implements a strategy for presenting questions in their original order.
 * This strategy is used when questions should be answered sequentially, as they are stored in the content block.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 *     List<Question> ordered = Sequential.INSTANCE.getProcessedQuestions(block);
 * </pre>
 * </p>
 *
 * <p>
 * This strategy is useful in educational applications where the order of questions is important for learning progression.
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.strategy.Strategy
 * @see com.pds.curiousmind.model.strategy.StrategyType
 */
public enum Sequential implements Strategy {
    /**
     * Singleton instance of the sequential strategy.
     */
    INSTANCE;

    /**
     * Returns the type of this strategy.
     *
     * @return the {@link StrategyType} representing sequential order
     */
    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SEQUENTIAL;
    }

    /**
     * Returns the list of questions from the given {@link RegisteredContentBlock} in their original order.
     *
     * @param block the content block containing the questions
     * @return the list of questions in sequential order
     */
    @Override
    public List<Question> getProcessedQuestions(RegisteredContentBlock block){
        return block.getQuestions();
    }
}
