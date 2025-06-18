package com.pds.curiousmind.model.registeredContentBlock;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a registered content block for a user within a registered course.
 * <p>
 * This class links a {@link ContentBlock} to a {@link RegisteredCourse} and tracks the completion status
 * of the content block for the user. It provides access to the content block's name, questions, and difficulty,
 * as well as methods to mark the block as completed or reset its status.
 * <p>
 * Mapped to the {@code registered_content_blocks} table in the database using JPA annotations.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Links a content block to a registered course for a specific user.</li>
 *   <li>Tracks whether the content block has been completed by the user.</li>
 *   <li>Provides access to the content block's name, questions, and difficulty.</li>
 *   <li>Supports marking as completed and resetting completion status.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     RegisteredContentBlock rcb = new RegisteredContentBlock(registeredCourse, contentBlock);
 *     boolean done = rcb.isCompleted();
 *     rcb.markAsCompleted();
 *     List&lt;Question&gt; questions = rcb.getQuestions();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
@Table(name = "registered_content_blocks")
public class RegisteredContentBlock {
    /**
     * Unique identifier for the registered content block (primary key, auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The registered course to which this content block belongs.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_course_id", nullable = false)
    private RegisteredCourse registeredCourse;

    /**
     * The content block associated with this registration.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "content_block_id", nullable = false)
    private ContentBlock contentBlock;

    /**
     * Whether the content block has been completed by the user.
     */
    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    /**
     * No-argument constructor for JPA.
     */
    public RegisteredContentBlock() {}

    /**
     * Constructs a registered content block for the given content block.
     *
     * @param contentBlock the content block to register
     */
    public RegisteredContentBlock(ContentBlock contentBlock) {
        this.contentBlock = contentBlock;
        this.completed = false;
    }

    /**
     * Constructs a registered content block for the given registered course and content block.
     *
     * @param registeredCourse the registered course
     * @param contentBlock the content block to register
     */
    public RegisteredContentBlock(RegisteredCourse registeredCourse, ContentBlock contentBlock) {
        this.registeredCourse = registeredCourse;
        this.contentBlock = contentBlock;
        this.completed = false;
    }

    // GETTERS AND SETTERS

    /**
     * Returns the unique identifier of this registered content block.
     *
     * @return the ID
     */
    public Long getId() { return id; }

    /**
     * Returns the registered course to which this content block belongs.
     *
     * @return the registered course
     */
    public RegisteredCourse getRegisteredCourse() { return registeredCourse; }

    /**
     * Sets the registered course for this content block.
     *
     * @param registeredCourse the registered course to set
     */
    public void setRegisteredCourse(RegisteredCourse registeredCourse) { this.registeredCourse = registeredCourse; }

    /**
     * Returns the associated content block.
     *
     * @return the content block
     */
    public ContentBlock getContentBlock() { return contentBlock; }

    /**
     * Sets the associated content block.
     *
     * @param contentBlock the content block to set
     */
    public void setContentBlock(ContentBlock contentBlock) { this.contentBlock = contentBlock; }

    /**
     * Returns whether the content block has been completed by the user.
     *
     * @return {@code true} if completed, {@code false} otherwise
     */
    public boolean isCompleted() { return completed; }

    /**
     * Sets the completion status of the content block.
     *
     * @param completed {@code true} if completed, {@code false} otherwise
     */
    public void setCompleted(boolean completed) { this.completed = completed; }

    // ADDITIONAL METHODS

    /**
     * Returns the name of the associated content block.
     *
     * @return the content block name, or {@code null} if not set
     */
    public String getName() { return this.contentBlock != null ? this.contentBlock.getName() : null; }

    /**
     * Returns the list of questions in the associated content block.
     *
     * @return the list of questions, or {@code null} if not set
     */
    public List<Question> getQuestions() { return this.contentBlock != null ? this.contentBlock.getQuestions() : null; }

    /**
     * Returns the difficulty of the associated content block.
     *
     * @return the difficulty, or {@code null} if not set
     */
    public Difficulty getDifficulty() { return this.contentBlock != null ? this.contentBlock.getDifficulty() : null; }

    /**
     * Marks this content block as completed.
     */
    public void markAsCompleted() { this.completed = true; }

    /**
     * Resets the completion status of this content block to not completed.
     */
    public void resetContentBlock() { this.completed = false; }
}