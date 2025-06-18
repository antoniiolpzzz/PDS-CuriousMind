package com.pds.curiousmind.model.question;

import com.pds.curiousmind.model.question.implementation.FillTheGap;
import com.pds.curiousmind.model.question.implementation.FlashCard;
import com.pds.curiousmind.model.question.implementation.Translate;
import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testValidateAnswerCaseInsensitive() {
        Question q = new com.pds.curiousmind.model.question.implementation.Test("Indication", "What is 2 + 2?", "4", List.of());

        assertTrue(q.validateAnswer("4"));
        assertTrue(q.validateAnswer(" 4 "));
        assertTrue(q.validateAnswer("4"));
        assertFalse(q.validateAnswer("5"));
    }

    @Test
    void testGetTypeForEachSubclass() {
        Question q1 = new com.pds.curiousmind.model.question.implementation.Test();
        Question q2 = new Translate();
        Question q3 = new FlashCard();
        Question q4 = new FillTheGap();

        assertEquals("Test", q1.getType());
        assertEquals("Translate", q2.getType());
        assertEquals("FlashCard", q3.getType());
        assertEquals("FillTheGap", q4.getType());
    }

    @Test
    void testGetCorrectAnswer() {
        Question q = new FlashCard("Indication", "Translate 'cat'", "gato", List.of());

        assertEquals("gato", q.getCorrectAnswer());
    }

    @Test
    void testGetOptionsWhenEmpty() {
        Question q = new com.pds.curiousmind.model.question.implementation.Test("Indication", "Question?", "answer", List.of());

        List<Option> options = q.getOptions();

        assertNotNull(options);
        assertTrue(options.isEmpty());
    }

    @Test
    void testGetOptionsWithOptions() {
        Option opt1 = new Option("A");
        Option opt2 = new Option("B");

        Question q = new FlashCard("Indication", "Select A", "A", List.of(opt1, opt2));

        List<Option> options = q.getOptions();

        assertEquals(2, options.size());
        assertTrue(options.contains(opt1));
        assertTrue(options.contains(opt2));
    }

    @Test
    void testDefaultConstructor() {
        Question q = new com.pds.curiousmind.model.question.implementation.Test();

        assertNull(q.getCorrectAnswer());
        assertNull(q.getStatement());
        assertNull(q.getIndication());
        assertNotNull(q.getOptions());
    }
}
