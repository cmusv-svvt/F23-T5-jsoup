package org.jsoup.parser;

import org.jsoup.integration.ParseTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;

public class ISM3 {
    @Test
    public void baseCaseConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = 'e';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc@D", consumed);
    }

    @Test
    public void varyInputCharToEmptyConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = '\0';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc@DeF;g567", consumed);
    }

    @Test
    public void varyInputCharToNumConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = '6';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc@DeF;g5", consumed);
    }

    @Test
    public void varyInputCharToFirstOfBufferConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = 'a';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("", consumed);
    }

    @Test
    public void varyInputCharToLastOfBufferConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = '7';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc@DeF;g56", consumed);
    }

    @Test
    public void varyInputCharToCapitalizedCharConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = 'F';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc@De", consumed);
    }

    @Test
    public void varyInputCharToSymbolConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = '@';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc", consumed);
    }

    @Test
    public void varyInputCharToFirstOfBufferContentConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = 'a';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("", consumed);
    }

    @Test
    public void varyInputCharToLastOfBufferContentConsumeToChar() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = '7';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("abc@DeF;g56", consumed);
    }

    @Test
    public void varyBufferContentToEmptyConsumeToChar() {
        String input = "";
        CharacterReader reader = new CharacterReader(input);

        char targetChar = 'e';
        String consumed = reader.consumeTo(targetChar);
        assertEquals("", consumed);
    }

    @Test
    public void baseCaseConsumeToString() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String targetSeq = "c@DeF";
        String consumed = reader.consumeTo(targetSeq);
        assertEquals("ab", consumed);
    }

    @Test
    public void varyInputStringToStartOfBufferContentConsumeToString() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String targetSeq = "abc@";
        String consumed = reader.consumeTo(targetSeq);
        assertEquals("", consumed);
    }

    @Test
    public void varyInputStringToEndOfBufferContentConsumeToString() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String targetSeq = "67";
        String consumed = reader.consumeTo(targetSeq);
        assertEquals("abc@DeF;g5", consumed);
    }

    @Test
    public void varyBufferContentToEmptyConsumeToString() {
        String input = "";
        CharacterReader reader = new CharacterReader(input);

        String targetSeq = "c@DeF";
        String consumed = reader.consumeTo(targetSeq);
        assertEquals("", consumed);
    }

    @Test
    public void baseCaseConsumeToAny() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAny('g','@',';');
        assertEquals("abc", consumed);
    }

    @Test
    public void varyInputCharsToSingleCharConsumeToAny() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAny(';');
        assertEquals("abc@DeF", consumed);
    }

    @Test
    public void varyInputCharsToAllCharConsumeToAny() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAny('a','b','c','@','D','e','F',';','g','5','6','7');
        assertEquals("", consumed);
    }

    @Test
    public void varyBufferContentToEmptyConsumeToAny() {
        String input = "";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAny('g','@',';');
        assertEquals("", consumed);
    }

    @Test
    public void varyBufferContentToSingleConsumeToAny() {
        String input = "a";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAny('g','@',';');
        assertEquals("a", consumed);
    }

    @Test
    public void baseCaseConsumeToAnySorted() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('6',';','c');
        assertEquals("ab", consumed);
    }

    // ========== Faults Reported ===========
    @Test
    public void varyInputSortedCharSeqToSingleConsumeToAnySorted() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('@');
        assertEquals("abc", consumed);
    }

    @Test
    public void varyInputSortedCharSeqToAllConsumeToAnySorted() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('5','6','7',';','@','D','F','a','b','c','e');
        assertEquals("", consumed);
    }

    @Test
    public void varyInputSortedCharSeqToNonExistedConsumeToAnySorted() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('!','9','z');
        assertEquals("abc@DeF;g567", consumed);
    }

    // ========== Faults Reported ===========
    @Test
    public void varyInputSortedCharSeqToFirstCharConsumeToAnySorted() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('a');
        assertEquals("", consumed);
    }

    // ========== Faults Reported ===========
    @Test
    public void varyInputSortedCharSeqToLastCharConsumeToAnySorted() {
        String input = "abc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('7');
        assertEquals("abc@DeF;g56", consumed);
    }

    @Test
    public void varyBufferContentToEmptyToAnySorted() {
        String input = "";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('6',';','c');
        assertEquals("", consumed);
    }

    // ========== Faults Reported ===========
    // cannot read the first char in sorted char seq
    @Test
    public void testIfFirstInputCharCanBeReadToAnySorted() {
        String input = "a2bc@DeF;g567";
        CharacterReader reader = new CharacterReader(input);

        String consumed = reader.consumeToAnySorted('2',';','c');
        assertEquals("a", consumed);
    }
}
