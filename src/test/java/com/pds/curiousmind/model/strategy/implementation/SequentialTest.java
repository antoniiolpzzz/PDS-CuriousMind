package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SequentialTest {

    @Test
    void testGetProcessedQuestionsReturnsSameList() {
        RegisteredContentBlock block = mock(RegisteredContentBlock.class);

        Question q1 = mock(Question.class);
        Question q2 = mock(Question.class);
        Question q3 = mock(Question.class);

        List<Question> questions = List.of(q1, q2, q3);
        when(block.getQuestions()).thenReturn(questions);

        List<Question> result = Sequential.INSTANCE.getProcessedQuestions(block);

        assertEquals(questions.size(), result.size());
        assertIterableEquals(questions, result); // misma lista, mismo orden
    }
}
