package com.pds.curiousmind.model.question.implementation;

import com.pds.curiousmind.model.question.option.Option;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranslateTest {

    @Test
    void testConstructorAndGeneratedOptions() {
        String correctAnswer = "Hello world from AI";
        List<Option> initialOptions = List.of(); // el constructor ya genera las opciones

        Translate translate = new Translate(
                "Translate this sentence.",
                "Hola mundo desde IA",
                correctAnswer,
                initialOptions
        );

        assertEquals("Translate this sentence.", translate.getIndication());
        assertEquals("Hola mundo desde IA", translate.getStatement());
        assertEquals(correctAnswer, translate.getCorrectAnswer());

        // Debe generar una Option por cada palabra
        String[] expectedWords = correctAnswer.split(Translate.DELIMITER);
        List<Option> options = translate.getOptions();

        assertNotNull(options);
        assertEquals(expectedWords.length, options.size());

        for (int i = 0; i < expectedWords.length; i++) {
            assertEquals(expectedWords[i], options.get(i).getLabel());
        }
    }

    @Test
    void testEmptyConstructor() {
        Translate translate = new Translate();

        assertNull(translate.getIndication());
        assertNull(translate.getStatement());
        assertNull(translate.getCorrectAnswer());
        assertNotNull(translate.getOptions());
        assertTrue(translate.getOptions().isEmpty());
    }

    @Test
    void testSetOptions() {
        Translate translate = new Translate();

        Option option1 = new Option();
        Option option2 = new Option();
        List<Option> newOptions = List.of(option1, option2);

        translate.setOptions(newOptions);

        assertNotNull(translate.getOptions());
        assertEquals(2, translate.getOptions().size());
        assertSame(option1, translate.getOptions().get(0));
        assertSame(option2, translate.getOptions().get(1));
    }
}
