package com.pds.curiousmind.model.question.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageOptionTest {

    @Test
    void testConstructorAndGetters() {
        String label = "Option A";
        String imageUrl = "http://example.com/image.jpg";

        ImageOption imageOption = new ImageOption(label, imageUrl);

        assertEquals(label, imageOption.getLabel());
        assertEquals(imageUrl, imageOption.getImageURL());
    }

    @Test
    void testEmptyConstructor() {
        ImageOption imageOption = new ImageOption();

        assertNull(imageOption.getLabel());
        assertNull(imageOption.getImageURL());
    }

}
