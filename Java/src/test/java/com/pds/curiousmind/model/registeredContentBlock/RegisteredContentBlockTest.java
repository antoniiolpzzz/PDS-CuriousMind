package com.pds.curiousmind.model.registeredContentBlock;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisteredContentBlockTest {

    @Test
    void testConstructorWithContentBlock() {
        ContentBlock contentBlock = mock(ContentBlock.class);
        RegisteredContentBlock rcb = new RegisteredContentBlock(contentBlock);

        assertEquals(contentBlock, rcb.getContentBlock());
        assertFalse(rcb.isCompleted());
        assertNull(rcb.getRegisteredCourse());
    }

    @Test
    void testConstructorWithRegisteredCourseAndContentBlock() {
        ContentBlock contentBlock = mock(ContentBlock.class);
        RegisteredCourse registeredCourse = mock(RegisteredCourse.class);

        RegisteredContentBlock rcb = new RegisteredContentBlock(registeredCourse, contentBlock);

        assertEquals(contentBlock, rcb.getContentBlock());
        assertEquals(registeredCourse, rcb.getRegisteredCourse());
        assertFalse(rcb.isCompleted());
    }

    @Test
    void testMarkAsCompletedAndReset() {
        RegisteredContentBlock rcb = new RegisteredContentBlock();

        assertFalse(rcb.isCompleted());

        rcb.markAsCompleted();
        assertTrue(rcb.isCompleted());

        rcb.resetContentBlock();
        assertFalse(rcb.isCompleted());
    }

    @Test
    void testGetNameDelegatesToContentBlock() {
        ContentBlock contentBlock = mock(ContentBlock.class);
        when(contentBlock.getName()).thenReturn("Block Name");

        RegisteredContentBlock rcb = new RegisteredContentBlock(contentBlock);

        assertEquals("Block Name", rcb.getName());
    }

    @Test
    void testGetQuestionsDelegatesToContentBlock() {
        ContentBlock contentBlock = mock(ContentBlock.class);
        List<Question> questions = List.of(mock(Question.class), mock(Question.class));

        when(contentBlock.getQuestions()).thenReturn(questions);

        RegisteredContentBlock rcb = new RegisteredContentBlock(contentBlock);

        assertEquals(questions, rcb.getQuestions());
    }

    @Test
    void testGetDifficultyDelegatesToContentBlock() {
        ContentBlock contentBlock = mock(ContentBlock.class);
        Difficulty difficulty = Difficulty.MEDIUM;

        when(contentBlock.getDifficulty()).thenReturn(difficulty);

        RegisteredContentBlock rcb = new RegisteredContentBlock(contentBlock);

        assertEquals(difficulty, rcb.getDifficulty());
    }

    @Test
    void testSettersAndGetters() {
        RegisteredContentBlock rcb = new RegisteredContentBlock();

        RegisteredCourse registeredCourse = mock(RegisteredCourse.class);
        ContentBlock contentBlock = mock(ContentBlock.class);

        rcb.setRegisteredCourse(registeredCourse);
        rcb.setContentBlock(contentBlock);
        rcb.setCompleted(true);

        assertEquals(registeredCourse, rcb.getRegisteredCourse());
        assertEquals(contentBlock, rcb.getContentBlock());
        assertTrue(rcb.isCompleted());
    }
}
