package com.pds.curiousmind.model.strategy;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;

import java.util.List;

/**
 * Strategy interface for defining different question ordering and processing algorithms in a course.
 * <p>
 * Implementations of this interface determine how questions are selected, ordered, or processed for a given
 * {@link RegisteredContentBlock}. This enables flexible learning strategies such as sequential, random, or spaced repetition.
 *
 * <h2>Key Responsibilities:</h2>
 * <ul>
 *   <li>Identify the type of strategy via {@link #getStrategyType()}.</li>
 *   <li>Process and return a list of questions for a content block via {@link #getProcessedQuestions(RegisteredContentBlock)}.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     Strategy strategy = new SequentialStrategy();
 *     List&lt;Question&gt; questions = strategy.getProcessedQuestions(registeredContentBlock);
 *     StrategyType type = strategy.getStrategyType();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
public interface Strategy {

    /**
     * Returns the type of this strategy.
     *
     * @return the {@link StrategyType} of this strategy
     */
    StrategyType getStrategyType();

    /**
     * Processes and returns the list of questions for the given registered content block according to the strategy.
     *
     * @param block the registered content block to process
     * @return the list of processed questions
     */
    List<Question> getProcessedQuestions(RegisteredContentBlock block);

}
