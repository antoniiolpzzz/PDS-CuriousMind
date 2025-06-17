package com.pds.curiousmind.model.question.option;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Option.class, name = "option"),
        @JsonSubTypes.Type(value = ImageOption.class, name = "imageoption")
})

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    // CONSTRUCTORS
    public Option(String label) {
        this.label = label.trim();
    }

    public Option() {}


    // GETTERS
    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }


    // SETTERS

    public void setLabel(String label) {
        this.label = label;
    }

    //COMMON OVERRIDES
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Option option = (Option) obj;
        return Objects.equals(this.label, option.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.label);
    }
}
