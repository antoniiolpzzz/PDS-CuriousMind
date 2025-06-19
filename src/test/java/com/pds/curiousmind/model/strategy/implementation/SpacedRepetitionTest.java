package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpacedRepetitionTest {

    @Test
    void testGetProcessedQuestionsAddsRepetitions() {
        RegisteredContentBlock block = mock(RegisteredContentBlock.class);

        Question q1 = mock(Question.class);
        Question q2 = mock(Question.class);
        Question q3 = mock(Question.class);
        Question q4 = mock(Question.class);
        Question q5 = mock(Question.class);
        Question q6 = mock(Question.class);

        List<Question> questions = List.of(q1, q2, q3, q4, q5, q6);
        when(block.getQuestions()).thenReturn(questions);

        List<Question> result = SpacedRepetition.INSTANCE.getProcessedQuestions(block);

        // Debe contener al menos los originales
        assertTrue(result.containsAll(questions));

        // Debe tener tamaño mayor o igual que original
        assertTrue(result.size() >= questions.size());

        // Verificamos que hay repeticiones cada REPETITION_NUMBER = 3
        int expectedExtra = questions.size() / 3; // 6 / 3 = 2 repeticiones
        int expectedSize = questions.size() + expectedExtra;

        assertEquals(expectedSize, result.size());
    }
}
