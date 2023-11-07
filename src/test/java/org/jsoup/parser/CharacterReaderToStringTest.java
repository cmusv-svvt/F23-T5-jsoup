package org.jsoup.parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

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
        assertEquals("", reader.toString());
    }

    
}
