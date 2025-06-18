package com.pds.curiousmind.model.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pds.curiousmind.model.question.implementation.*;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


/**
 * Abstract base class representing a question in the system.
 * <p>
 * A question consists of an indication (instruction or hint), a statement (the main question text),
 * a correct answer, and a list of possible options. This class is designed to be extended by specific
 * question types such as Test, Translate, FlashCard, and FillTheGap. It is mapped to the "questions"
 * table in the database using JPA annotations and supports polymorphic serialization with Jackson.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Supports multiple question types via inheritance and Jackson annotations.</li>
 *   <li>Stores a list of options for multiple-choice or similar questions.</li>
 *   <li>Provides validation logic for checking answers.</li>
 *   <li>Ensures encapsulation and immutability of the options list.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     List<Option> options = List.of(new Option("A"), new Option("B"));
 *     Question q = new Test("Choose the correct answer", "2+2?", "4", options);
 *     boolean correct = q.validateAnswer("4");
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "questions")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Test.class, name = "test"),
        @JsonSubTypes.Type(value = Translate.class, name = "translate"),
        @JsonSubTypes.Type(value = FlashCard.class, name = "flashcard"),
        @JsonSubTypes.Type(value = FillTheGap.class, name = "fillthegap")
})
public abstract class Question {
    /**
     * Unique identifier for the question (primary key, auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Instruction or hint for the question.
     * This field is mandatory and cannot be null.
     */
    @Column(name = "indication", nullable = false)
    protected String indication;

    /**
     * The main text or statement of the question.
     * This field is mandatory and cannot be null.
     */
    @Column(name = "statement", nullable = false)
    protected String statement;

    /**
     * The correct answer for the question.
     * This field is mandatory and cannot be null.
     */
    @Column(name = "correct_answer", nullable = false)
    protected String correctAnswer;

    /**
     * List of possible options for the question (e.g., for multiple-choice).
     * This field is eagerly fetched and orphaned if removed.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    protected List<Option> options =  new ArrayList<>();

    // CONSTRUCTORS

    /**
     * Constructs a question with indication, statement, correct answer, and options.
     * Makes a defensive copy of the options list if provided.
     *
     * @param indication the instruction or hint for the question
     * @param statement the main text of the question
     * @param correctAnswer the correct answer
     * @param options the list of possible options (may be null)
     */
    public Question(String indication, String statement, String correctAnswer, List<Option> options) {
        this.indication = indication;
        this.statement = statement;
        this.correctAnswer = correctAnswer.trim();
        if (options != null) {
            this.options = new ArrayList<>(options);
        }
    }

    /**
     * Constructs a question with indication, statement, and correct answer (no options).
     *
     * @param indication the instruction or hint for the question
     * @param statement the main text of the question
     * @param correctAnswer the correct answer
     */
    public Question(String indication, String statement, String correctAnswer) {
        this(indication, statement, correctAnswer, null);
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public Question() {
    }

    // GETTERS

    /**
     * Returns the unique identifier of the question.
     *
     * @return the question ID
     */
    public Long getId() { return id; }

    /**
     * Returns the instruction or hint for the question.
     *
     * @return the indication
     */
    public String getIndication() { return indication; }

    /**
     * Returns the main text or statement of the question.
     *
     * @return the statement
     */
    public String getStatement() { return statement; }

    /**
     * Returns the correct answer for the question.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() { return correctAnswer; }

    /**
     * Returns an unmodifiable list of options for the question.
     *
     * @return the list of options
     */
    public List<Option> getOptions() { return Collections.unmodifiableList(options); }

    // SETTERS

    /**
     * Sets the unique identifier for the question.
     *
     * @param id the question ID
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Sets the instruction or hint for the question.
     *
     * @param indication the indication to set
     */
    public void setIndication(String indication) { this.indication = indication; }

    /**
     * Sets the main text or statement of the question.
     *
     * @param statement the statement to set
     */
    public void setStatement(String statement) { this.statement = statement; }

    /**
     * Sets the correct answer for the question.
     *
     * @param correctAnswer the correct answer to set
     */
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    /**
     * Sets the list of options for the question.
     *
     * @param options the list of options to set
     */
    public void setOptions(List<Option> options) { this.options = options; }

    // METHODS

    /**
     * Validates the provided answer against the correct answer for this question.
     *
     * @param answer the answer to validate
     * @return {@code true} if the answer matches the correct answer (case-insensitive), {@code false} otherwise
     */
    public boolean validateAnswer(String answer) {
        return this.correctAnswer.equalsIgnoreCase(answer.trim());
    }

    /**
     * Returns the type of the question as a string (class name).
     *
     * @return the type of the question
     */
    @JsonIgnore
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
