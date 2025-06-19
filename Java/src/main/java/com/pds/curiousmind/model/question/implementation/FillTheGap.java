package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.Entity;
import java.util.Collections;
import java.util.List;

/**
 * Represents a fill-in-the-gap question, where the user must provide the correct answer without selecting from options.
 * <p>
 * This class extends {@link Question} but does not support options, as fill-the-gap questions require the user to type
 * the answer directly. Any attempt to set options is ignored, and calls to getOptions() always return an empty list.
 * <p>
 * <b>Usage Example:</b>
 * <pre>
 *     FillTheGap question = new FillTheGap("Complete the sentence:", "The capital of France is ___", "Paris");
 *     boolean correct = question.validateAnswer("Paris");
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
public class FillTheGap extends Question {

    /**
     * Constructs a fill-the-gap question with the given indication, statement, and correct answer.
     *
     * @param indication the instruction or hint for the question
     * @param statement the main text of the question
     * @param correctAnswer the correct answer
     */
    public FillTheGap(String indication, String statement, String correctAnswer) {
        super(indication, statement, correctAnswer);
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public FillTheGap() {
        super();
    }

    /**
     * Does nothing, as FillTheGap questions do not support options.
     *
     * @param options ignored
     */
    @Override
    public void setOptions(List<Option> options) {
        // Do nothing: FillTheGap does not support options
    }

    /**
     * Returns an empty list, as FillTheGap questions do not have options.
     *
     * @return an empty list
     */
    @Override
    public List<Option> getOptions() {
        return Collections.emptyList();
    }
}
