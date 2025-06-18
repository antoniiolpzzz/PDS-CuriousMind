package com.pds.curiousmind.model.contentBlock;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.question.implementation.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ContentBlockTest {

    private ContentBlock createExampleBlock() {
        return new ContentBlock(
                "Introduction",
                Difficulty.EASY,
                List.of(
                        new FillTheGap("Fill the gap", "Ich ___ ein Buch", "lese"
                        ),
                        new FlashCard("Flashcard", "Apple", "Apfel",
                                List.of(
                                        new Option("Apfel"),
                                        new Option("Banane")
                                )
                        ),
                        new com.pds.curiousmind.model.question.implementation.Test("Test question", "What is the German word for 'apple'?", "Apfel",
                                List.of(
                                        new Option("Apfel"),
                                        new Option("Banane"),
                                        new Option("Orange"),
                                        new Option("Birne")
                                )
                        ),
                        new Translate("Translate", "Banana", "Banane",
                                List.of(
                                        new Option("Banane"),
                                        new Option("Ananas")
                                )
                        )
                )
        );
    }

    // GETTERS

    @Test
    void testGetName() {
        ContentBlock block = createExampleBlock();
        assertEquals("Introduction", block.getName());
    }

    @Test
    void testGetQuestions() {
        ContentBlock block = createExampleBlock();
        assertNotNull(block.getQuestions());
        assertEquals(4, block.getQuestions().size());
    }

    @Test
    void testGetDifficulty() {
        ContentBlock block = createExampleBlock();
        assertEquals(Difficulty.EASY, block.getDifficulty());
    }


    // SETTERS
    @Test
    void testSetId() {
        ContentBlock block = createExampleBlock();
        block.setId(123L);
        assertEquals(123L, block.getId());
    }

    @Test
    void testSetName() {
        ContentBlock block = createExampleBlock();
        block.setName("New Name");
        assertEquals("New Name", block.getName());
    }

    @Test
    void testSetQuestions() {
        ContentBlock block = createExampleBlock();
        block.setQuestions(List.of());
        assertEquals(0, block.getQuestions().size());
    }

    @Test
    void testSetDifficulty() {
        ContentBlock block = createExampleBlock();
        block.setDifficulty(Difficulty.MEDIUM);
        assertEquals(Difficulty.MEDIUM, block.getDifficulty());
    }

}