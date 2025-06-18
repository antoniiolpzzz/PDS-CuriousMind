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
        QuestionIterator iterator = new QuestionIterator(List.of(testQuestion, fillTheGapQuestion));
        Question q1 = iterator.next();
        assertEquals(testQuestion, q1);

        Question q2 = iterator.next();
        assertEquals(fillTheGapQuestion, q2);

        assertThrows(NoSuchElementException.class, iterator::next);
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
