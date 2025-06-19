package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlashCardTest {

    @Test
    void testConstructorAndGetters() {
        Option option1 = new Option();
        Option option2 = new Option();

        List<Option> options = List.of(option1, option2);

        FlashCard flashCard = new FlashCard(
                "Study this flashcard.",
                "What is the largest planet?",
                "Jupiter",
                options
        );

        assertEquals("Study this flashcard.", flashCard.getIndication());
        assertEquals("What is the largest planet?", flashCard.getStatement());
        assertEquals("Jupiter", flashCard.getCorrectAnswer());
        assertEquals(2, flashCard.getOptions().size());
        assertSame(option1, flashCard.getOptions().get(0));
        assertSame(option2, flashCard.getOptions().get(1));
    }

    @Test
    void testEmptyConstructor() {
        FlashCard flashCard = new FlashCard();

        assertNull(flashCard.getIndication());
        assertNull(flashCard.getStatement());
        assertNull(flashCard.getCorrectAnswer());
        assertTrue(flashCard.getOptions().isEmpty());
    }


    @Test
    void testSetOptions() {
        FlashCard flashCard = new FlashCard();

        Option optionA = new Option();
        Option optionB = new Option();
        List<Option> newOptions = List.of(optionA, optionB);

        flashCard.setOptions(newOptions);

        assertNotNull(flashCard.getOptions());
        assertEquals(2, flashCard.getOptions().size());
        assertSame(optionA, flashCard.getOptions().get(0));
        assertSame(optionB, flashCard.getOptions().get(1));
    }
}
