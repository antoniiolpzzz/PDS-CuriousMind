package com.pds.curiousmind.model.question.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {

    @Test
    void testConstructorAndGetters() {
        String rawLabel = "  Option A  ";
        Option option = new Option(rawLabel);

        // Debe hacer trim
        assertEquals("Option A", option.getLabel());
    }

    @Test
    void testSetLabel() {
        Option option = new Option();
        option.setLabel("New Label");

        assertEquals("New Label", option.getLabel());
    }

    @Test
    void testEqualsAndHashCode() {
        Option option1 = new Option("Option A");
        Option option2 = new Option("Option A");
        Option option3 = new Option("Option B");

        // equals
        assertEquals(option1, option2);
        assertNotEquals(option1, option3);

        // hashCode
        assertEquals(option1.hashCode(), option2.hashCode());
        assertNotEquals(option1.hashCode(), option3.hashCode());
    }

    @Test
    void testEqualsWithDifferentClass() {
        Option option = new Option("Option A");

        assertNotEquals(option, null);
        assertNotEquals(option, "NotAnOption");
    }
}
