package org.jsoup.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterReaderToStringTest {

    CharacterReader reader;

    @BeforeEach
    public void setUp() {
        reader = new CharacterReader("one");
    }

    @Test
    public void testToStringInitial() {
        assertEquals("one", reader.toString());
    }

    @Test
    public void testToStringAdvancedCursor() {
        reader.advance();
        assertEquals("ne", reader.toString());
    }

    @Test
    public void testToStringAdvancedCursorMultiple() {
        reader.advance();
        reader.advance();
        assertEquals("e", reader.toString());
    }

    @Test
    public void testToStringAdvancedToCompletion() {
        reader.advance();
        reader.advance();
        reader.advance();
        assertEquals("", reader.toString());
    }

    @Test
    public void testToStringAdvancedPastCompletion() {
        reader.advance();
        reader.advance();
        reader.advance();
        reader.advance();
        assertNull(reader.toString());
    }

    
}
