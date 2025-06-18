package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * Represents a flashcard question, typically used for simple prompt-and-answer learning.
 * <p>
 * A flashcard question presents a statement or prompt (the "front" of the card) and expects the user to provide
 * the correct answer (the "back" of the card). Flashcards may also include a list of options for multiple-choice
 * scenarios, but are most often used for direct recall.
 * <p>
 * This class extends {@link Question} and is mapped as an entity in the database.
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     List<Option> options = List.of(new Option("Apple"), new Option("Banana"));
 *     FlashCard card = new FlashCard("Translate to German:", "Apple", "Apfel", options);
 *     boolean correct = card.validateAnswer("Apfel");
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
public class FlashCard extends Question {

    /**
     * Constructs a flashcard question with the given indication, statement, correct answer, and options.
     *
     * @param indication the instruction or hint for the flashcard
     * @param statement the main text or prompt of the flashcard
     * @param correctAnswer the correct answer for the flashcard
     * @param options the list of possible options (may be null or empty)
     */
    public FlashCard(String indication, String statement, String correctAnswer, List<Option> options) {
        super(indication, statement, correctAnswer, options);
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public FlashCard() {
        super();
    }
}
