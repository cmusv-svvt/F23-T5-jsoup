package org.jsoup.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test suite for character reader.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class CharacterReaderISM6 {
    public final static int maxBufferLen = CharacterReader.maxBufferLen;

    @Test
    public void matchConsumeStringBaseCase() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("a"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeNonDisplayableString() {
        CharacterReader cr = new CharacterReader("\nabc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("\n"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeStringEmptyCase() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume(""));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeStringEmptyBuffer() {
        CharacterReader cr = new CharacterReader("");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume(""));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeStringBufferLenIsOne() {
        CharacterReader cr = new CharacterReader("a");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("a"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeBufferPosAtOne() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("b"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeBufferAtTheEnd() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("c"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeBufferExceedEnd() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume(""));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeBufferExceedEndCannotMatch() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertFalse(cr.matchConsume("a"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeBufferCannotMatch() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertFalse(cr.matchConsume("c"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseStringBaseCase() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase("A"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseStringEmptyCase() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase(""));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseStringEmptyBuffer() {
        CharacterReader cr = new CharacterReader("");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase(""));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseStringBufferLenIsOne() {
        CharacterReader cr = new CharacterReader("a");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase("A"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseBufferPosAtOne() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase("B"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseBufferAtTheEnd() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase("C"));
        assertEquals(current_pos + 1, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseBufferExceedEnd() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase(""));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseBufferExceedEndCannotMatch() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertFalse(cr.matchConsumeIgnoreCase("A"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeMoreThanOneString() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("ab"));
        assertEquals(current_pos + 2, cr.pos());
    }

    @Test
    public void matchConsumeEmptyWillFail() {
        CharacterReader cr = new CharacterReader("");
        int current_pos = cr.pos();
        assertFalse(cr.matchConsume("a"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeMoreThanOneStringWillFailWhenBufferLenIsOne() {
        CharacterReader cr = new CharacterReader("a");
        int current_pos = cr.pos();
        assertFalse(cr.matchConsume("ab"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeMoreThanOneBufferPosAtOne() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsume("bc"));
        assertEquals(current_pos + 2, cr.pos());
    }

    @Test
    public void matchConsumeMoreThanOneBufferAtTheEndWillFail() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertFalse(cr.matchConsume("ca"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeBufferExceedEndWillFail() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertFalse(cr.matchConsume("a"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeMoreThanOneIgnoreCaseString() {
        CharacterReader cr = new CharacterReader("abc");
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase("AB"));
        assertEquals(current_pos + 2, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseEmptyWillFail() {
        CharacterReader cr = new CharacterReader("");
        int current_pos = cr.pos();
        assertFalse(cr.matchConsumeIgnoreCase("a"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseMoreThanOneStringWillFailWhenBufferLenIsOne() {
        CharacterReader cr = new CharacterReader("a");
        int current_pos = cr.pos();
        assertFalse(cr.matchConsumeIgnoreCase("AB"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseMoreThanOneBufferPosAtOne() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        int current_pos = cr.pos();
        assertTrue(cr.matchConsumeIgnoreCase("BC"));
        assertEquals(current_pos + 2, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseMoreThanOneBufferAtTheEndWillFail() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertFalse(cr.matchConsumeIgnoreCase("CA"));
        assertEquals(current_pos, cr.pos());
    }

    @Test
    public void matchConsumeIgnoreCaseBufferExceedEndWillFail() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        cr.consume();
        int current_pos = cr.pos();
        assertFalse(cr.matchConsumeIgnoreCase("A"));
        assertEquals(current_pos, cr.pos());
    }
}
