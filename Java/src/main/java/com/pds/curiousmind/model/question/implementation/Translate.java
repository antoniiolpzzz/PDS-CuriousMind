package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.Entity;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a translation question, where the user is expected to translate a statement and provide the correct answer.
 * <p>
 * This class extends {@link Question} and is mapped as an entity in the database. In addition to the provided options,
 * it automatically generates options by splitting the correct answer using a delimiter (default is a space) and adds
 * each part as an option. This is useful for translation exercises where the answer may consist of multiple words.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Automatically generates options from the correct answer by splitting on a delimiter.</li>
 *   <li>Supports custom options in addition to generated ones.</li>
 *   <li>Extends the base {@link Question} class and inherits its validation logic.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     List<Option> options = List.of(new Option("Haus"), new Option("Baum"));
 *     Translate question = new Translate("Translate to German:", "The house is big", "Das Haus ist groß", options);
 *     boolean correct = question.validateAnswer("Das Haus ist groß");
 *     List<Option> allOptions = question.getOptions(); // includes both provided and generated options
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
public class Translate extends Question {

    /**
     * The delimiter used to split the correct answer into options (default is a space).
     */
    public static final String DELIMITER = " ";

    /**
     * Constructs a translation question with the given indication, statement, correct answer, and options.
     * In addition to the provided options, automatically generates options by splitting the correct answer.
     *
     * @param indication the instruction or hint for the question
     * @param statement the main text or prompt of the question
     * @param correctAnswer the correct answer for the translation
     * @param options the list of possible options (may be null or empty)
     */
    public Translate(String indication, String statement, String correctAnswer, List<Option> options) {

        super(indication, statement, correctAnswer, options);
        Arrays.stream(correctAnswer.split(DELIMITER))
                .map(Option::new)
                .forEach(this.options::add);
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public Translate() {
        super();
    }
}
