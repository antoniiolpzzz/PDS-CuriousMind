package com.pds.curiousmind.model.question.option;

import java.util.Objects;

public class Option {
    private String label;

    public Option(String label) {
        this.label = label.trim();
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

