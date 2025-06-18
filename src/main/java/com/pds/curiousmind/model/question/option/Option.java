package com.pds.curiousmind.model.question.option;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

/**
 * Represents a selectable option for a question, such as a possible answer in a multiple-choice or flashcard scenario.
 * <p>
 * This class is the base for all option types and supports polymorphic serialization with Jackson. It is mapped to the
 * "options" table in the database using JPA annotations. Subclasses can extend this class to provide additional
 * functionality or data (e.g., {@link ImageOption} for image-based options).
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Stores a label representing the option's text or value.</li>
 *   <li>Supports polymorphic serialization and deserialization for different option types.</li>
 *   <li>Overrides {@code equals} and {@code hashCode} for correct comparison and usage in collections.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     Option option = new Option("Apple");
 *     String label = option.getLabel();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "options")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Option.class, name = "option"),
        @JsonSubTypes.Type(value = ImageOption.class, name = "imageoption")
})
public class Option {
    /**
     * Unique identifier for the option (primary key, auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The label or text of the option.
     */
    private String label;

    /**
     * Constructs an option with the specified label.
     *
     * @param label the label or text for the option
     */
    public Option(String label) {
        this.label = label.trim();
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public Option() {}

    /**
     * Returns the unique identifier of the option.
     *
     * @return the option ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the label or text of the option.
     *
     * @return the label of the option
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label or text of the option.
     *
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Checks if this option is equal to another object based on the label.
     *
     * @param obj the object to compare
     * @return {@code true} if the labels are equal and the classes match, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Option option = (Option) obj;
        return Objects.equals(this.label, option.label);
    }

    /**
     * Returns the hash code for this option, based on the label.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.label);
    }
}
