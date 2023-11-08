package org.jsoup.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ISM1 {
    @Test
    public void baseCaseNextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        char searchChar = 'e';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(4, nextIndex); // 'e' is at index 4 in the given string
    }

    @Test
    public void varyCharInput1NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //empty char input
        char searchChar = '\0';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }
    @Test
    public void varyCharInput2NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //capitalize char
        char searchChar = 'H';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(7, nextIndex);
    }

    @Test
    public void varyCharInput3NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //integer char
        char searchChar = '1';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(11, nextIndex);
    }

    @Test
    public void varyCharInput4NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //char !
        char searchChar = '!';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(14, nextIndex);
    }

    @Test
    public void varyCharInput5NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //char space
        char searchChar = ' ';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(9, nextIndex);
    }

    @Test
    public void varyCharInput6NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //non-existed normal char
        char searchChar = 'y';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyCharInput7NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //non-existed capitalized char
        char searchChar = 'Z';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyCharInput8NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //non-existed number char
        char searchChar = '7';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyCharInput9NextIndexOfChar() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //non-existed @ char
        char searchChar = '@';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyBufferContentToEmptyNextIndexOfChar() {
        String input = "";
        CharacterReader reader = new CharacterReader(input);

        //normal char
        char searchChar = 'e';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyBufferContentToSingleNextIndexOfChar() {
        String input = "a";
        CharacterReader reader = new CharacterReader(input);

        //normal char
        char searchChar = 'e';
        int nextIndex = reader.nextIndexOf(searchChar);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void baseCaseNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //normal char seq
        CharSequence searchSeq = "efg";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(4, nextIndex);
    }

    @Test
    public void varyCharSeqInputToCapitalizedLettersNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //normal capitalized char seq
        CharSequence searchSeq = "HI";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(7, nextIndex);
    }

    @Test
    public void varyCharSeqInputToLettersAndCapitalizedLettersNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //normal combined char seq
        CharSequence searchSeq = "fgHI";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(5, nextIndex);
    }

    @Test
    public void varyCharSeqInputToNumNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //normal numbers
        CharSequence searchSeq = "12";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(11, nextIndex);
    }

    @Test
    public void varyCharSeqInputToNumAndSymbolNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //normal numbers with "!" mark
        CharSequence searchSeq = "123!";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(11, nextIndex);
    }

    @Test
    public void varyCharSeqInputToSymbolNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //"!" mark
        CharSequence searchSeq = "!";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(14, nextIndex);
    }

    @Test
    public void varyCharSeqInputToCapitalizedLetterAndNumNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //capitalized letter and numbers
        CharSequence searchSeq = "J123";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(10, nextIndex);
    }

    @Test
    public void varyCharSeqInputToLetterAndNumNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //invalid: non-capitalized letter and numbers
        CharSequence searchSeq = "j123";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyCharSeqInputToAllCombinedNextIndexOfCharSeq() {
        String input = "abcdefgHI J123!";
        CharacterReader reader = new CharacterReader(input);

        //invalid: non-capitalized letter and numbers
        CharSequence searchSeq = "abcdefgHI J123!";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(0, nextIndex);
    }

    @Test
    public void varyBufferContentToEmptyNextIndexOfCharSeq() {
        String input = "";
        CharacterReader reader = new CharacterReader(input);

        //invalid: normal char seq input
        CharSequence searchSeq = "efg";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(-1, nextIndex);
    }

    @Test
    public void varyBufferContentToSingleNextIndexOfCharSeq() {
        String input = "a";
        CharacterReader reader = new CharacterReader(input);

        //invalid: normal char seq input
        CharSequence searchSeq = "efg";
        int nextIndex = reader.nextIndexOf(searchSeq);
        assertEquals(-1, nextIndex);
    }
}
