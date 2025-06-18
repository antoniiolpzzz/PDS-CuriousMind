package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FillTheGapTest {

    @Test
    void testConstructorAndGetters() {
        FillTheGap question = new FillTheGap(
                "Complete the sentence.",
                "The capital of France is ___",
                "Paris"
        );

        assertEquals("Complete the sentence.", question.getIndication());
        assertEquals("The capital of France is ___", question.getStatement());
        assertEquals("Paris", question.getCorrectAnswer());
    }

    @Test
    void testEmptyConstructor() {
        FillTheGap question = new FillTheGap();

        assertNull(question.getIndication());
        assertNull(question.getStatement());
        assertNull(question.getCorrectAnswer());
    }

    @Test
    void testGetOptionsReturnsEmptyList() {
        FillTheGap question = new FillTheGap();
        List<Option> options = question.getOptions();

        assertNotNull(options);
        assertTrue(options.isEmpty());
    }

    @Test
    void testSetOptionsDoesNothing() {
        FillTheGap question = new FillTheGap();

        // Crea una lista vacía o de null, ya que FillTheGap no debería usarla
        question.setOptions(Collections.emptyList());

        // Debe seguir devolviendo una lista vacía
        List<Option> options = question.getOptions();
        assertNotNull(options);
        assertTrue(options.isEmpty());
    }

}
