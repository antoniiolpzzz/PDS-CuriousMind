package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShuffledTest {

    @Test
    void testGetProcessedQuestionsReturnsShuffledList() {
        RegisteredContentBlock block = mock(RegisteredContentBlock.class);

        Question q1 = mock(Question.class);
        Question q2 = mock(Question.class);
        Question q3 = mock(Question.class);
        Question q4 = mock(Question.class);

        List<Question> questions = List.of(q1, q2, q3, q4);
        when(block.getQuestions()).thenReturn(questions);

        List<Question> result = Shuffled.INSTANCE.getProcessedQuestions(block);

        assertEquals(questions.size(), result.size());
        assertTrue(result.containsAll(questions));
        assertTrue(questions.containsAll(result));

        // No garantizamos que cambie el orden, pero si quieres puedes probarlo varias veces
        // para comprobar que en la mayoría de los casos el orden es distinto
    }
}
