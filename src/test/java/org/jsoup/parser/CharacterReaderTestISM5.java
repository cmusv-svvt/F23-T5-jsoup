package org.jsoup.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CharacterReaderTestISM5 {
    public final static int maxBufferLen = CharacterReader.maxBufferLen;

    @Test
    public void matchesLetterNormalUppercaseLatin() {
        CharacterReader r = new CharacterReader("B");
        assertTrue(r.matchesLetter());
    }

    @Test
    public void matchesLetterNormalLowercaseLatin() {
        CharacterReader r = new CharacterReader("b");
        assertTrue(r.matchesLetter());
    }

    @Test
    public void matchesLetterBoundaryUppercaseLatin() {
        CharacterReader r = new CharacterReader("AZ");
        assertTrue(r.matchesLetter());
        r.consume();
        assertTrue(r.matchesLetter());
    }

    @Test
    public void matchesLetterBoundaryLowercaseLatin() {
        CharacterReader r = new CharacterReader("az");
        assertTrue(r.matchesLetter());
        r.consume();
        assertTrue(r.matchesLetter());
    }

    @Test
    public void matchesLetterChinese() {
        CharacterReader r = new CharacterReader("好");
        assertTrue(r.matchesLetter());
    }

    @Test
    public void matchesLetterGreek() {
        CharacterReader r = new CharacterReader("Ω");
        assertTrue(r.matchesLetter());
    }

    @Test
    public void matchesLetterNonLetter() {
        CharacterReader r = new CharacterReader("1");
        assertFalse(r.matchesLetter());
    }

    @Test
    public void matchesLetterEmpty() {
        CharacterReader r = new CharacterReader("");
        assertFalse(r.matchesLetter());
    }

    @Test
    public void matchesAsciiAlphaNormalUppercase() {
        CharacterReader r = new CharacterReader("Y");
        assertTrue(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesAsciiAlphaNormalLowercase() {
        CharacterReader r = new CharacterReader("y");
        assertTrue(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesAsciiAlphaBoundaryUppercase() {
        CharacterReader r = new CharacterReader("AY");
        assertTrue(r.matchesAsciiAlpha());
        r.consume();
        assertTrue(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesAsciiAlphaBoundaryLowercase() {
        CharacterReader r = new CharacterReader("ay");
        assertTrue(r.matchesAsciiAlpha());
        r.consume();
        assertTrue(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesAsciiAlphaChinese() {
        CharacterReader r = new CharacterReader("坏");
        assertFalse(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesAsciiAlphaNonLetter() {
        CharacterReader r = new CharacterReader("1");
        assertFalse(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesAsciiAlphaEmpty() {
        CharacterReader r = new CharacterReader("");
        assertFalse(r.matchesAsciiAlpha());
    }

    @Test
    public void matchesDigitNormal() {
        CharacterReader r = new CharacterReader("3");
        assertTrue(r.matchesDigit());
    }

    @Test
    public void matchesDigitBoundary() {
        CharacterReader r = new CharacterReader("09");
        assertTrue(r.matchesDigit());
        r.consume();
        assertTrue(r.matchesDigit());
    }

    @Test
    public void matchesDigitNonDigit() {
        CharacterReader r = new CharacterReader("x");
        assertFalse(r.matchesDigit());
    }

    @Test
    public void matchesDigitEmpty() {
        CharacterReader r = new CharacterReader("");
        assertFalse(r.matchesDigit());
    }
}
