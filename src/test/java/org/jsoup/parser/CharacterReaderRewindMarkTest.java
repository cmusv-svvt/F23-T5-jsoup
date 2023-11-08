package org.jsoup.parser;

import org.jsoup.integration.ParseTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for character reader.
 *
 * @author Jonathan Hedley, jonathan@hedley.net
 */
public class CharacterReaderRewindMarkTest {
    public final static int maxBufferLen = 1024 * 2;

    // Write tests for mark(), unmark, rewindToMark()

    @Test
    public void rewindToMarkWillBackToOriginalPosTest() {
        char[] chars = new char[maxBufferLen];
        Arrays.fill(chars, 'a');
        String str = new String(chars);
        Reader r = new StringReader(str);
        CharacterReader cr = new CharacterReader(r, str.length());
        cr.mark();
        assertEquals(0, cr.pos());
        cr.consume();
        cr.consume();
        cr.rewindToMark();
        assertEquals(0, cr.pos());
    }

    @Test
    public void rewindWithoutMarkWillThrowErrorTest() {
        CharacterReader cr = new CharacterReader("abc");
        cr.consume();
        cr.consume();
        assertThrows(UncheckedIOException.class, () -> {
            cr.rewindToMark();
        });
    }

}
