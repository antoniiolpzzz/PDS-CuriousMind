package com.pds.curiousmind;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.question.implementation.Translate;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameIntegrationTest {

    @Test
    void testUserProgressThroughGameManager() {
        // Create a user
        User user = new User(
                "Ana López",
                "ana@example.com",
                "anauser",
                "pw123"
        );

        // Create a ContentBlock with a question
        ContentBlock block = new ContentBlock(
                "Introduction",
                Difficulty.EASY,
                List.of(
                        new Translate("Translate", "Banana", "Banane",
                                List.of(
                                        new Option("Banane"),
                                        new Option("Ananas")
                                )
                        )
                )
        );

        // Create a Course with a list of strategies
        Course course = new Course(
                "Beginner German Course",
                "A course to introduce basic German language concepts.",
                List.of(StrategyType.SEQUENTIAL),
                List.of(block)
        );
        course.setImageURL("https://stock.adobe.com/247157521/german-language-learning-logo-icon-with-headphones.jpg");

        // Create RegisteredCourse and RegisteredContentBlock
        List<RegisteredContentBlock> registeredBlocks = new java.util.ArrayList<>();
        RegisteredCourse registeredCourse = new RegisteredCourse(user, course, StrategyType.SEQUENTIAL);
        RegisteredContentBlock registeredBlock = new RegisteredContentBlock(registeredCourse, block);
        registeredBlocks.add(registeredBlock);
        // Set the mutable list of registered blocks using reflection (workaround for immutable list)
        try {
            java.lang.reflect.Field field = RegisteredCourse.class.getDeclaredField("registeredContentBlocks");
            field.setAccessible(true);
            field.set(registeredCourse, registeredBlocks);
        } catch (Exception e) {
            throw new RuntimeException("Could not set mutable list of registered blocks", e);
        }
        // Use GameManager singleton correctly
        GameManager.INSTANCE.initializeGame(registeredCourse, registeredBlock);

        // Check that the game has started
        assertTrue(GameManager.INSTANCE.hasNextQuestion());
        assertEquals(0, GameManager.INSTANCE.getCurrentProgress());

        // Simulate the user answering a question
        GameManager.INSTANCE.getNextQuestion(); // gets the first question

        // Check updated progress
        int progress = GameManager.INSTANCE.getCurrentProgress();
        assertTrue(progress >= 0 && progress <= 100);

        // Deactivate game
        GameManager.INSTANCE.deactivateGame();
        assertNull(GameManager.INSTANCE.getCurrentCourse());
        assertNull(GameManager.INSTANCE.getCurrentContentBlock());
    }
}
