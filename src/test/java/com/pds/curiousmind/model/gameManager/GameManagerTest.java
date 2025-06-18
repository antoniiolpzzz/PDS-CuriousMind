package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.question.Question;
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
    private ContentBlock contentBlock;
    private RegisteredCourse registeredCourse;
    private RegisteredContentBlock registeredContentBlock;

    private Question q1;
    private Question q2;

    @BeforeEach
    void setUp() {
        // Crear preguntas
        q1 = new com.pds.curiousmind.model.question.implementation.Test("Indication", "Q1", "A1", List.of());
        q2 = new com.pds.curiousmind.model.question.implementation.Test("Indication", "Q2", "A2", List.of());

        // Crear ContentBlock con preguntas
        contentBlock = new ContentBlock();
        contentBlock.setName("Block 1");
        contentBlock.setQuestions(List.of(q1, q2));

        // Crear Course con contentBlock
        course = new Course();
        course.setName("Test Course");
        course.setContentBlocks(List.of(contentBlock));

        // Crear User
        user = new User("Ana", "ana@test.com", "1234", "ana");

        // Registrar Course
        registeredCourse = new RegisteredCourse(user, course, StrategyType.SEQUENTIAL);
        registeredContentBlock = registeredCourse.getRegisteredContentBlocks().getFirst();
    }

    @Test
    void testInitializeAndDeactivateGame() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        assertEquals(registeredCourse, GameManager.INSTANCE.getCurrentCourse());
        assertEquals(registeredContentBlock, GameManager.INSTANCE.getCurrentContentBlock());
        assertEquals(5, GameManager.INSTANCE.getLives());
        assertTrue(GameManager.INSTANCE.hasNextQuestion());

        GameManager.INSTANCE.deactivateGame();

        assertNull(GameManager.INSTANCE.getCurrentCourse());
        assertNull(GameManager.INSTANCE.getCurrentContentBlock());
        assertFalse(GameManager.INSTANCE.hasNextQuestion());
        assertEquals(5, GameManager.INSTANCE.getLives());
        assertEquals(0, GameManager.INSTANCE.getGameDuration());
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
        assertEquals(5, GameManager.INSTANCE.getLives()); // No pierde vidas al responder

        // consumir todo
        GameManager.INSTANCE.getNextQuestion();
        assertNull(GameManager.INSTANCE.getNextQuestion());
    }

    @Test
    void testAddFailedQuestionAndLives() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        assertEquals(5, GameManager.INSTANCE.getLives());
        assertEquals(0, GameManager.INSTANCE.getFailedCount());

        GameManager.INSTANCE.addFailedQuestion(q1);

        assertEquals(4, GameManager.INSTANCE.getLives());
        assertEquals(1, GameManager.INSTANCE.getFailedCount());
    }

    @Test
    void testOutOfLivesPreventsNextQuestion() {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        for (int i = 0; i < 5; i++) {
            GameManager.INSTANCE.addFailedQuestion(q1);
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
    void testGetGameDuration() throws InterruptedException {
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);

        Thread.sleep(1000);

        assertTrue(GameManager.INSTANCE.getGameDuration() >= 1);
    }
}
