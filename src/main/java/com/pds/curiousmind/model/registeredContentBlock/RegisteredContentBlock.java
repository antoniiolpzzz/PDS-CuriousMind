package com.pds.curiousmind.model.registeredContentBlock;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "registered_content_blocks")
public class RegisteredContentBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "registered_course_id", nullable = false)
    private RegisteredCourse registeredCourse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "content_block_id", nullable = false)
    private ContentBlock contentBlock;

    @Column(nullable = false)
    private boolean isCompleted = false;

    // No-arg constructor for JPA
    public RegisteredContentBlock() {}

    public RegisteredContentBlock(ContentBlock contentBlock) {
        this.contentBlock = contentBlock;
        this.isCompleted = false;
    }

    // GETTERS AND SETTERS
    public Long getId() { return id; }
    public RegisteredCourse getRegisteredCourse() { return registeredCourse; }
    public void setRegisteredCourse(RegisteredCourse registeredCourse) { this.registeredCourse = registeredCourse; }
    public ContentBlock getContentBlock() { return contentBlock; }
    public void setContentBlock(ContentBlock contentBlock) { this.contentBlock = contentBlock; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    // Business logic methods
    public String getName() { return this.contentBlock != null ? this.contentBlock.getName() : null; }
    public List<Question> getQuestions() { return this.contentBlock != null ? this.contentBlock.getQuestions() : null; }
    public Difficulty getDifficulty() { return this.contentBlock != null ? this.contentBlock.getDifficulty() : null; }
    public void markAsCompleted() { this.isCompleted = true; }
    public void resetContentBlock() { this.isCompleted = false; }
}