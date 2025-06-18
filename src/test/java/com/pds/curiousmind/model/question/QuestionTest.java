package com.pds.curiousmind.model.question;

import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    // Clase dummy para poder testear Question
    static class DummyQuestion extends Question {
        public DummyQuestion(String indication, String statement, String correctAnswer, List<Option> options) {
            super(indication, statement, correctAnswer, options);
        }

        public DummyQuestion() {
            super();
        }
    }

    @Test
    void testConstructorAndGetters() {
        String correctAnswer = "  Correct Answer  ";
        Option option1 = new Option("Option 1");
        Option option2 = new Option("Option 2");

        DummyQuestion question = new DummyQuestion(
                "Choose the correct answer.",
                "This is the statement.",
                correctAnswer,
                List.of(option1, option2)
        );

        assertEquals("Choose the correct answer.", question.getIndication());
        assertEquals("This is the statement.", question.getStatement());
        // Debe haber hecho trim
        assertEquals("Correct Answer", question.getCorrectAnswer());
        assertEquals(2, question.getOptions().size());
    }

    @Test
    void testConstructorWithNullOptions() {
        DummyQuestion question = new DummyQuestion(
                "Indication",
                "Statement",
                "Answer",
                null
        );

        assertNotNull(question.getOptions());
        assertTrue(question.getOptions().isEmpty());
    }

    @Test
    void testEmptyConstructor() {
        DummyQuestion question = new DummyQuestion();

        assertNull(question.getIndication());
        assertNull(question.getStatement());
        assertNull(question.getCorrectAnswer());
        assertNotNull(question.getOptions());
        assertTrue(question.getOptions().isEmpty());
    }

    @Test
    void testSetOptions() {
        DummyQuestion question = new DummyQuestion();

        Option option1 = new Option("A");
        Option option2 = new Option("B");
        List<Option> newOptions = List.of(option1, option2);

        question.setOptions(newOptions);

        assertEquals(2, question.getOptions().size());
    }

    @Test
    void testValidateAnswer() {
        DummyQuestion question = new DummyQuestion(
                "Indication",
                "Statement",
                "Correct Answer",
                null
        );

        // Exact match
        assertTrue(question.validateAnswer("Correct Answer"));

        // Different case
        assertTrue(question.validateAnswer("correct answer"));

        // With extra spaces
        assertTrue(question.validateAnswer("   Correct Answer   "));

        // Incorrect answer
        assertFalse(question.validateAnswer("Wrong Answer"));
    }

    @Test
    void testGetType() {
        DummyQuestion question = new DummyQuestion();
        assertEquals("DummyQuestion", question.getType());
    }
}
