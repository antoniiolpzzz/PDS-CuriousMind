package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * Represents a test (multiple-choice) question, where the user selects the correct answer from a list of options.
 * <p>
 * This class extends {@link Question} and is mapped as an entity in the database. Test questions are typically used
 * for quizzes or assessments where several possible answers are presented and only one is correct.
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     List<Option> options = List.of(new Option("A"), new Option("B"), new Option("C"));
 *     Test test = new Test("Choose the correct answer:", "2+2?", "4", options);
 *     boolean correct = test.validateAnswer("4");
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
public class Test extends Question {

    /**
     * Constructs a test question with the given indication, statement, correct answer, and options.
     *
     * @param indication the instruction or hint for the test question
     * @param statement the main text or prompt of the test question
     * @param correctAnswer the correct answer for the test question
     * @param options the list of possible options (may be null or empty)
     */
    public Test(String indication, String statement, String correctAnswer, List<Option> options) {
        super(indication, statement, correctAnswer, options);
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public Test() {
        super();
    }
}
