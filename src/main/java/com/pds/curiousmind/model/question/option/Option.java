package com.pds.curiousmind.model.question.option;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

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
