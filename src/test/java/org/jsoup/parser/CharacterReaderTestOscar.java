package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.io.Reader;
import java.io.StringReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterReaderTestOscar {
    public final static int maxBufferLen = CharacterReader.maxBufferLen;


    @Test
    public void testRangeEqualsWhenEquals() {
        char[] charBuf = {'t', 'e', 's', 't'};
        int start = 0;
        int count = 4;
        String str = "test";
        assertTrue(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test
    public void testRangeEqualsStartsInMiddle() {
        char[] charBuf = {'t', 'e', 's', 't'};
        int start = 1;
        int count = 3;
        String str = "est";
        assertTrue(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test
    public void testRangeEqualsWhenOnlyLengthEquals() {
        char[] charBuf = {'t', 'e', 's', 't'};
        int start = 0;
        int count = 4;
        String str = "nope";
        assertFalse(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test
    public void testRangeEqualsWhenLengthDifferent() {
        char[] charBuf = {'t', 'e', 's', 't'};
        int start = 0;
        int count = 4;
        String str = "tes";
        assertFalse(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test 
    public void testRangeEqualsWhenStartOffEnd() {
        char[] charBuf = {'t', 'e', 's', 't'};
        int start = 4;
        int count = 4;
        String str = "test";
        // assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
        //     CharacterReader.rangeEquals(charBuf, start, count, str);
        // });
        assertFalse(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test
    public void testRangeEqualsWhenCountIsZero() {
        char[] charBuf = {'t', 'e', 's', 't'};
        int start = 3;
        int count = 0;
        String str = "";
        assertTrue(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test
    public void testRangeEqualsWithEmptyBuffer() {
        char[] charBuf = {};
        int start = 0;
        int count = 0;
        String str = "";
        assertTrue(CharacterReader.rangeEquals(charBuf, start, count, str));
    }

    @Test
    public void testConstructor() {
        Reader r = new StringReader("this is the test content");
        CharacterReader cr = new CharacterReader(r, 4);
        assertEquals(0, cr.pos());
        assertEquals(cr.toString().length(), 4);
    }

    @Test
    public void testConstructorSizeTooLarge() {
        char[] chars = new char[maxBufferLen + 1];
        Arrays.fill(chars, 'a');
        String str = new String(chars);
        Reader r = new StringReader(str);
        CharacterReader cr = new CharacterReader(r, str.length());
        assertEquals(cr.toString().length(), maxBufferLen);
    }

    @Test
    public void testConstructorSizeZero() {
        Reader r = new StringReader("this is the test content");
        CharacterReader cr = new CharacterReader(r, 0);
        assertEquals(cr.toString().length(), 0);
    }

    @Test
    public void testConstructorEmptyReader() {
        Reader r = new StringReader("");
        CharacterReader cr = new CharacterReader(r, 4);
        assertEquals(cr.toString().length(), 0);
    }

    @Test
    public void testConstructorNullReader() {
        Reader r = null;
        assertThrows(NullPointerException.class, () -> {
            CharacterReader cr = new CharacterReader(r, 4);
        });
    }

    @Test
    public void testConstructorUnmarkableReader() {
        try {
            Reader r = new FileReader("haha.txt");
            assertThrows(IllegalArgumentException.class, () -> {
                CharacterReader cr = new CharacterReader(r, 4);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorString() {
        String str = "this is the test content";
        CharacterReader cr = new CharacterReader(str);
        assertEquals(0, cr.pos());
        assertEquals(cr.toString().length(), str.length());
    }

    // @Test
    // public void testCacheStringTooLong() {
    //     String str = "this is the te!st content";
    //     Reader r = new StringReader(str);
    //     CharacterReader cr = new CharacterReader(r);
    //     assertEquals(str.substring(0, 14), cr.consumeTo('!'));
    // }

    // @Test
    // public void testCacheStringNotTooLong() {
    //     String str = "this! is the test content";
    //     Reader r = new StringReader(str);
    //     CharacterReader cr = new CharacterReader(r);
    //     assertEquals(str.substring(0, 4), cr.consumeTo('!'));
    // }
}
