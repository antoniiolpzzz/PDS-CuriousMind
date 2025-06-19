package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.implementation.FillTheGap;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    private User user;
    private Course course;
    private RegisteredCourse registeredCourse;
    private RegisteredContentBlock registeredContentBlock;

    private Question q1;
    private Question q2;

    @BeforeEach
    void setUp() {
        // Crear user + course
        user = new User("Ana López", "ana@example.com", "pass123", "ana123");

        course = new Course();
        course.setName("Curso Test");
        course.setContentBlocks(List.of());

        registeredCourse = new RegisteredCourse(user, course, StrategyType.SEQUENTIAL);

        // Crear ContentBlock con preguntas
        q1 = new com.pds.curiousmind.model.question.implementation.Test("Indication 1", "Question 1", "Answer 1", List.of());
        q2 = new FillTheGap("Indication 2", "Question 2", "Answer 2");

        RegisteredContentBlock block = new RegisteredContentBlock();
        block.setRegisteredCourse(registeredCourse);
        block.setContentBlock(new com.pds.curiousmind.model.contentblock.ContentBlock() {{
            setName("Block Test");
            setQuestions(List.of(q1, q2));
        }});

        registeredContentBlock = block;
    }

    @Test
    void testInitializeGame() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        assertEquals(registeredCourse, GameManager.INSTANCE.getCurrentCourse());
        assertEquals(registeredContentBlock, GameManager.INSTANCE.getCurrentContentBlock());
        assertEquals(5, GameManager.INSTANCE.getLives());
        assertEquals(2, GameManager.INSTANCE.totalQuestions());
        assertTrue(GameManager.INSTANCE.getGameDuration() >= 0);

        GameManager.INSTANCE.deactivateGame();
    }

    @Test
    void testNextQuestionAndProgress() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        assertEquals(2, GameManager.INSTANCE.totalQuestions());
        assertEquals(2, GameManager.INSTANCE.questionsLeft());
        assertEquals(5, GameManager.INSTANCE.getLives());
        assertEquals(0, GameManager.INSTANCE.getFailedCount());
        assertEquals(0, GameManager.INSTANCE.getCurrentProgress());

        Question next = GameManager.INSTANCE.getNextQuestion();
        assertNotNull(next);
        assertEquals(5, GameManager.INSTANCE.getLives());

        Question next2 = GameManager.INSTANCE.getNextQuestion();
        assertNotNull(next2);

        assertNull(GameManager.INSTANCE.getNextQuestion());
    }

    @Test
    void testFailedQuestionsAndLives() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        Question q = GameManager.INSTANCE.getNextQuestion();
        assertNotNull(q);

        GameManager.INSTANCE.addFailedQuestion(q);

        assertEquals(1, GameManager.INSTANCE.getFailedCount());
        assertEquals(4, GameManager.INSTANCE.getLives());
    }

    @Test
    void testLivesReachZero() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        Question q = GameManager.INSTANCE.getNextQuestion();
        for (int i = 0; i < 5; i++) {
            GameManager.INSTANCE.addFailedQuestion(q);
        }

        assertEquals(0, GameManager.INSTANCE.getLives());
        assertNull(GameManager.INSTANCE.getNextQuestion());
    }

    @Test
    void testMarkBlockAsCompleted() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        assertFalse(registeredContentBlock.isCompleted());

        GameManager.INSTANCE.markBlockAsCompleted();

        assertTrue(registeredContentBlock.isCompleted());
    }

    @Test
    void testDeactivateGame() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        GameManager.INSTANCE.deactivateGame();

        assertNull(GameManager.INSTANCE.getCurrentCourse());
        assertNull(GameManager.INSTANCE.getCurrentContentBlock());
        assertEquals(5, GameManager.INSTANCE.getLives());
        assertEquals(0, GameManager.INSTANCE.totalQuestions());
    }
}
