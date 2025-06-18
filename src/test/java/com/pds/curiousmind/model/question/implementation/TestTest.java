package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {

    @Test
    void testConstructorAndGetters() {
        Option option1 = new Option();
        Option option2 = new Option();

        List<Option> options = List.of(option1, option2);

        com.pds.curiousmind.model.question.implementation.Test questionTest = new com.pds.curiousmind.model.question.implementation.Test(
                "Answer this question.",
                "What is the capital of Spain?",
                "Madrid",
                options
        );

        assertEquals("Answer this question.", questionTest.getIndication());
        assertEquals("What is the capital of Spain?", questionTest.getStatement());
        assertEquals("Madrid", questionTest.getCorrectAnswer());
        assertNotNull(questionTest.getOptions());
        assertEquals(2, questionTest.getOptions().size());
        assertSame(option1, questionTest.getOptions().get(0));
        assertSame(option2, questionTest.getOptions().get(1));
    }

    @Test
    void testEmptyConstructor() {
        com.pds.curiousmind.model.question.implementation.Test questionTest = new com.pds.curiousmind.model.question.implementation.Test();

        assertNull(questionTest.getIndication());
        assertNull(questionTest.getStatement());
        assertNull(questionTest.getCorrectAnswer());
        assertNotNull(questionTest.getOptions());
        assertTrue(questionTest.getOptions().isEmpty());
    }

    @Test
    void testSetOptions() {
        com.pds.curiousmind.model.question.implementation.Test questionTest = new com.pds.curiousmind.model.question.implementation.Test();

        Option optionA = new Option();
        Option optionB = new Option();
        List<Option> newOptions = List.of(optionA, optionB);

        questionTest.setOptions(newOptions);

        assertNotNull(questionTest.getOptions());
        assertEquals(2, questionTest.getOptions().size());
        assertSame(optionA, questionTest.getOptions().get(0));
        assertSame(optionB, questionTest.getOptions().get(1));
    }
}
