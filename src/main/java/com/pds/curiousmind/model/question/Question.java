package com.pds.curiousmind.model.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.option.Option;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = com.pds.curiousmind.model.question.implementation.Test.class, name = "test"),
    @JsonSubTypes.Type(value = com.pds.curiousmind.model.question.implementation.Translate.class, name = "translate"),
    @JsonSubTypes.Type(value = com.pds.curiousmind.model.question.implementation.FlashCard.class, name = "flashcard"),
    @JsonSubTypes.Type(value = com.pds.curiousmind.model.question.implementation.FillTheGap.class, name = "fillthegap")
})
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String indication;
    protected String statement;
    protected String correctAnswer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id", nullable = false)
    protected List<Option> options;

    @ManyToOne(optional = false)
    @JoinColumn(name = "content_block_id", nullable = false)
    protected ContentBlock contentBlock;

    // CONSTRUCTORS
    public Question(String indication, String statement, String correctAnswer, List<Option> options) {
        this.indication = indication;
        this.statement = statement;
        this.correctAnswer = correctAnswer.trim();
        if (options != null) {
            this.options = new ArrayList<>(options);
        } else {
            this.options = new ArrayList<>();
        }
    }

    public Question(String indication, String statement, String correctAnswer) {
        this(indication, statement, correctAnswer, null);
    }

    public Question() {
        this.options = new ArrayList<>();
    }

    // GETTERS
    public Long getId() { return id; }
    public String getIndication() { return indication; }
    public String getStatement() { return statement; }
    public String getCorrectAnswer() { return correctAnswer; }
    public List<Option> getOptions() { return Collections.unmodifiableList(options); }
    public ContentBlock getContentBlock() { return contentBlock; }

    // SETTERS (for JPA)
    public void setId(Long id) { this.id = id; }
    public void setIndication(String indication) { this.indication = indication; }
    public void setStatement(String statement) { this.statement = statement; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    public void setOptions(List<Option> options) { this.options = options; }
    public void setContentBlock(ContentBlock contentBlock) { this.contentBlock = contentBlock; }

    // METHODS
    public boolean validateAnswer(String answer) {
        return this.correctAnswer.equalsIgnoreCase(answer.trim());
    }

}
