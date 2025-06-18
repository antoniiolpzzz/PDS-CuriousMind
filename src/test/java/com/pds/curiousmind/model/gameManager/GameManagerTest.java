package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.strategy.provider.StrategyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameManagerTest {

    private RegisteredCourse course;
    private RegisteredContentBlock block;
    private Strategy strategy;

    private Question q1;
    private Question q2;

    @BeforeEach
    void setUp() {
        // Mocks
        course = mock(RegisteredCourse.class);
        block = mock(RegisteredContentBlock.class);
        strategy = mock(Strategy.class);

        q1 = new com.pds.curiousmind.model.question.implementation.Test("Indication", "Q1", "A1", List.of());
        q2 = new com.pds.curiousmind.model.question.implementation.Test("Indication", "Q2", "A2", List.of());

        when(course.getStrategy()).thenReturn(StrategyType.SEQUENTIAL);
        when(strategy.getProcessedQuestions(block)).thenReturn(List.of(q1, q2));

        // Forzar el StrategyProvider a devolver nuestro strategy mock
        try {
            var field = StrategyProvider.class.getDeclaredField("strategies");
            field.setAccessible(true);
            var map = (java.util.Map<StrategyType, Strategy>) field.get(StrategyProvider.INSTANCE);
            map.put(StrategyType.SEQUENTIAL, strategy);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInitializeAndDeactivateGame() {
        GameManager.INSTANCE.initializeGame(course, block);

        assertEquals(course, GameManager.INSTANCE.getCurrentCourse());
        assertEquals(block, GameManager.INSTANCE.getCurrentContentBlock());
        assertTrue(GameManager.INSTANCE.hasNextQuestion());

        GameManager.INSTANCE.deactivateGame();

        assertNull(GameManager.INSTANCE.getCurrentCourse());
        assertNull(GameManager.INSTANCE.getCurrentContentBlock());
        assertFalse(GameManager.INSTANCE.hasNextQuestion());
    }
    @Test
    void testNextQuestionAndProgress() {
        GameManager.INSTANCE.initializeGame(course, block);

        assertEquals(2, GameManager.INSTANCE.totalQuestions());
        assertEquals(2, GameManager.INSTANCE.questionsLeft());
        assertEquals(0, GameManager.INSTANCE.getFailedCount());
        assertEquals(0, GameManager.INSTANCE.getCurrentProgress());

        // Next 1
        Question next = GameManager.INSTANCE.getNextQuestion();
        assertNotNull(next);
        assertTrue(next == q1 || next == q2);

        assertEquals(50, GameManager.INSTANCE.getCurrentProgress());
        assertEquals(1, GameManager.INSTANCE.questionsLeft());

        // Next 2
        next = GameManager.INSTANCE.getNextQuestion();
        assertNotNull(next);

        assertEquals(100, GameManager.INSTANCE.getCurrentProgress());
        assertEquals(0, GameManager.INSTANCE.questionsLeft());

        // No more questions
        assertNull(GameManager.INSTANCE.getNextQuestion());
        assertFalse(GameManager.INSTANCE.hasNextQuestion());
    }

    @Test
    void testAddFailedQuestion() {
        GameManager.INSTANCE.initializeGame(course, block);

        assertEquals(0, GameManager.INSTANCE.getFailedCount());
        assertEquals(2, GameManager.INSTANCE.totalQuestions());

        GameManager.INSTANCE.addFailedQuestion(q1);

        assertEquals(1, GameManager.INSTANCE.getFailedCount());
        assertEquals(3, GameManager.INSTANCE.totalQuestions());
        assertEquals(3, GameManager.INSTANCE.questionsLeft()); // <-- importante
    }


    @Test
    void testMarkBlockAsCompleted() {
        GameManager.INSTANCE.initializeGame(course, block);

        GameManager.INSTANCE.markBlockAsCompleted();

        verify(block, times(1)).markAsCompleted();
    }

    @Test
    void testProgressMethodsWhenInactive() {
        GameManager.INSTANCE.deactivateGame();

        assertEquals(0, GameManager.INSTANCE.getCurrentProgress());
        assertEquals(0, GameManager.INSTANCE.totalQuestions());
        assertEquals(0, GameManager.INSTANCE.questionsLeft());
        assertEquals(0, GameManager.INSTANCE.getFailedCount());
    }
}
