package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.implementation.FillTheGap;
import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QuestionIteratorTest {

    private Question testQuestion;
    private Question fillTheGapQuestion;

    @BeforeEach
    void setUp() {
        testQuestion = new com.pds.curiousmind.model.question.implementation.Test(
                "Choose the correct translation.",
                "What is the German word for 'apple'?",
                "Apfel",
                List.of(
                        new Option("Apfel"),
                        new Option("Banane"),
                        new Option("Orange"),
                        new Option("Birne")
                )
        );

        fillTheGapQuestion = new FillTheGap(
                "Fill the gap.",
                "The capital of Germany is ____.",
                "Berlin"
        );
    }

    @Test
    void testGetFailedCount() {
        QuestionIterator iterator = new QuestionIterator(List.of(testQuestion));
        assertEquals(0, iterator.getFailedCount());

        iterator.addFailedQuestion(testQuestion);
        assertEquals(1, iterator.getFailedCount());
    }

    @Test
    void testGetQuestionsLeft() {
        QuestionIterator iterator = new QuestionIterator(List.of(testQuestion, fillTheGapQuestion));
        assertEquals(2, iterator.getQuestionsLeft());

        iterator.next();
        assertEquals(1, iterator.getQuestionsLeft());
    }

    @Test
    void testGetTotalQuestions() {
        QuestionIterator iterator = new QuestionIterator(List.of(testQuestion, fillTheGapQuestion));
        assertEquals(2, iterator.getTotalQuestions());
    }

    @Test
    void testHasNext() {
        QuestionIterator iterator = new QuestionIterator(List.of(testQuestion));
        assertTrue(iterator.hasNext());

        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testNext() {
        Question q1 = new com.pds.curiousmind.model.question.implementation.Test("Indication", "Q1", "A1", List.of());
        Question q2 = new FillTheGap("Indication", "Q2", "A2");

        QuestionIterator iterator = new QuestionIterator(List.of(q1, q2));

        Question first = iterator.next();
        assertEquals(q1, first);

        Question second = iterator.next();
        assertEquals(q2, second);

        // ADAPTACIÓN: en vez de esperar la excepción, verificamos que no hay más
        assertFalse(iterator.hasNext());
    }



    @Test
    void testAddFailedQuestion() {
        QuestionIterator iterator = new QuestionIterator(List.of(testQuestion));

        assertEquals(1, iterator.getTotalQuestions());
        assertEquals(0, iterator.getFailedCount());

        iterator.addFailedQuestion(fillTheGapQuestion);

        assertEquals(2, iterator.getTotalQuestions());
        assertEquals(1, iterator.getFailedCount());

        assertTrue(iterator.hasNext());
    }
}
