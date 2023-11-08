package org.jsoup.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ISM2 {
    // 2.1 Base Case
    @Test
    public void consumeDataBC() {
        String context = "abcbar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcbar", r.consumeData());
    }

    @Test
    public void consumeDataTest1() {
        String context = "abc&bar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abc", r.consumeData());
    }

    @Test
    public void consumeDataTest2() {
        String context = "ab<cbar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("ab", r.consumeData());
    }

    @Test
    public void consumeDataTestNullChar() {
        String context = "abs\u0000bar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs", r.consumeData());
    }
    @Test
    public void consumeDataTestStart() {
        String context = "\u0000absbar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("", r.consumeData());
    }

    @Test
    public void consumeDataTestEnd() {
        String context = "absbar&";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absbar", r.consumeData());
    }

    @Test
    public void consumeDataTestMultiple() {
        String context = "abs<bar&";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs", r.consumeData());
    }


    // Consume Attribute
    @Test
    public void consumeAttributeTestOnlyLetterTrue() {
        String context = "absbar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absbar", r.consumeAttributeQuoted(true));
    }

    @Test
    public void consumeAttributeTestOnlyLetterFalse() {
        String context = "absbar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absbar", r.consumeAttributeQuoted(false));
    }

    @Test
    public void consumeAttributeTestDoubleTrue() {
        String context = "abs\"bar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs\"bar", r.consumeAttributeQuoted(true));
    }

    @Test
    public void consumeAttributeTestDoubleFalse() {
        String context = "abs\"bar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs", r.consumeAttributeQuoted(false));
    }

    @Test
    public void consumeAttributeTestSingleTrue() {
        String context = "abs'bar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs", r.consumeAttributeQuoted(true));
    }

    @Test
    public void consumeAttributeTestSingleFalse() {
        String context = "abs'bar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs'bar", r.consumeAttributeQuoted(false));
    }

    @Test
    public void consumeAttributeTestNullTrue() {
        String context = "absb\u0000ar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absb", r.consumeAttributeQuoted(true));
    }

    @Test
    public void consumeAttributeTestNullFalse() {
        String context = "absb\u0000ar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absb", r.consumeAttributeQuoted(false));
    }

    @Test
    public void consumeAttributeTest1True() {
        String context = "absb&ar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absb", r.consumeAttributeQuoted(true));
    }

    @Test
    public void consumeAttributeTest1False() {
        String context = "absb&ar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absb", r.consumeAttributeQuoted(false));
    }

    @Test
    public void consumeAttributeTestDoubleStart() {
        String context = "\"absb&ar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("", r.consumeAttributeQuoted(false));
    }


    @Test
    public void consumeAttributeTestSingleEnd() {
        String context = "absbar'";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absbar", r.consumeAttributeQuoted(false));
    }

    @Test
    public void consumeAttributeTestMultiple() {
        String context = "ab\"sb'ar";
        CharacterReader r = new CharacterReader(context);
        assertEquals("ab\"sb", r.consumeAttributeQuoted(true));
    }

    // consumeRaw Data
    @Test
    public void consumeRawDataTest() {
        String context = "absde acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absde acdf", r.consumeRawData());
    }

    @Test
    public void consumeRawDataTestSingle() {
        String context = "absde <span> acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absde ", r.consumeRawData());
    }

    @Test
    public void consumeRawDataTestMultiple() {
        String context = "abs <span> de <span> acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abs ", r.consumeRawData());
    }

    @Test
    public void consumeRawDataTestSingleNull() {
        String context = "absde\u0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absde", r.consumeRawData());
    }

    @Test
    public void consumeRawDataTestStart() {
        String context = "<span>absde acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("", r.consumeRawData());
    }

    @Test
    public void consumeRawDataTestEnd() {
        String context = "absde acdf<span>";
        CharacterReader r = new CharacterReader(context);
        assertEquals("absde acdf", r.consumeRawData());
    }


    // ConsumeToEnd

    @Test
    public void consumeToEnd() {
        String context = "abcde";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeToEnd());
    }

    @Test
    public void consumeToEndSpecial() {
        String context = "ab<cde";
        CharacterReader r = new CharacterReader(context);
        assertEquals("ab<cde", r.consumeToEnd());
    }

    // Letter Sequence
    @Test
    public void consumeLetterSequenceTestNull() {
        CharacterReader r = new CharacterReader("&");
        assertEquals("", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceTestSpeical() {
        CharacterReader r = new CharacterReader("{}");
        assertEquals("", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceTestLower() {
        CharacterReader r = new CharacterReader("onecde");
        assertEquals("onecde", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceTestUpperMixed() {
        CharacterReader r = new CharacterReader("ONECDE");
        assertEquals("ONECDE", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceTestSpace() {
        CharacterReader r = new CharacterReader("onE cde");
        assertEquals("onE", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceTestDigit() {
        CharacterReader r = new CharacterReader("onEd7 cde");
        assertEquals("onEd", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceTestNonLetterOrDigit() {
        CharacterReader r = new CharacterReader("onEd& sde cde");
        assertEquals("onEd", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceStart() {
        CharacterReader r = new CharacterReader(" onEd& sde cde");
        assertEquals("", r.consumeLetterSequence());
    }

    @Test
    public void consumeLetterSequenceEnd() {
        CharacterReader r = new CharacterReader("onEdsdecde9");
        assertEquals("onEdsdecde", r.consumeLetterSequence());
    }

    //consumeLetterThenDigitSequence()
    @Test
    public void consumeLetterThenDigitSequenceTestNull() {
        CharacterReader r = new CharacterReader("&");
        assertEquals("", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestSpcial() {
        CharacterReader r = new CharacterReader("{}");
        assertEquals("", r.consumeLetterThenDigitSequence());
    }
    @Test
    public void consumeLetterThenDigitSequenceTestLower() {
        CharacterReader r = new CharacterReader("onequx");
        assertEquals("onequx", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestLowerAndUpper() {
        CharacterReader r = new CharacterReader("oneQux");
        assertEquals("oneQux", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestAllUpper() {
        CharacterReader r = new CharacterReader("ONEQUX");
        assertEquals("ONEQUX", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestLetterThenDigit() {
        CharacterReader r = new CharacterReader("One12 Two &bar; qux");
        assertEquals("One12", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestLetterThenNonDigit() {
        CharacterReader r = new CharacterReader("One; qux");
        assertEquals("One", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestDigitThenLetter() {
        CharacterReader r = new CharacterReader("12One Two &bar; qux");
        assertEquals("12", r.consumeLetterThenDigitSequence());
    }

    @Test
    public void consumeLetterThenDigitSequenceTestNonDigitOrLetter() {
        CharacterReader r = new CharacterReader("One12;3Two &bar");
        assertEquals("One12", r.consumeLetterThenDigitSequence());
    }

    // consumeHexSequence
    @Test
    public void consumeHexSequenceTestNull() {
        CharacterReader r = new CharacterReader("&");
        assertEquals("", r.consumeHexSequence());
    }
    @Test
    public void consumeHexSequenceTestAllLowerHex() {
        CharacterReader r = new CharacterReader("bdecdecb");
        assertEquals("bdecdecb", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestAllUpperHex() {
        CharacterReader r = new CharacterReader("BDECDE");
        assertEquals("BDECDE", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestAllDigitHex() {
        CharacterReader r = new CharacterReader("12435758");
        assertEquals("12435758", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestBoundaryLowerHex() {
        CharacterReader r = new CharacterReader("abdecdecbf");
        assertEquals("abdecdecbf", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestBoundaryUpperHex() {
        CharacterReader r = new CharacterReader("ABDECDEF");
        assertEquals("ABDECDEF", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestBoundaryDigitHex() {
        CharacterReader r = new CharacterReader("0124357589");
        assertEquals("0124357589", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestHexLetterThenDigit() {
        CharacterReader r = new CharacterReader("abc12fand");
        assertEquals("abc12fa", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestHexStart() {
        CharacterReader r = new CharacterReader("oabc12fAnd");
        assertEquals("", r.consumeHexSequence());
    }

    @Test
    public void consumeHexSequenceTestHexEnd() {
        CharacterReader r = new CharacterReader("abc12fAdN");
        assertEquals("abc12fAd", r.consumeHexSequence());
    }

    // consumeDigitSequence

    @Test
    public void consumeDigitSequenceTestAllDigits() {
        CharacterReader r = new CharacterReader("164528651");
        assertEquals("164528651", r.consumeDigitSequence());
    }

    @Test
    public void consumeDigitSequenceTestDigitsThenLetter() {
        CharacterReader r = new CharacterReader("1645abc28651");
        assertEquals("1645", r.consumeDigitSequence());
    }

    @Test
    public void consumeDigitSequenceTestLetterThenDigit() {
        CharacterReader r = new CharacterReader("fsd164 5abc28651");
        assertEquals("", r.consumeDigitSequence());
    }

    @Test
    public void consumeDigitSequenceTestNonDigit() {
        CharacterReader r = new CharacterReader("164&5ab c28651");
        assertEquals("164", r.consumeDigitSequence());
    }


    // CosnumeTag Name
    @Test
    public void consumeTagNameTest1() {
        String context = "abcde<acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest2() {
        String context = "abcde/0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest3() {
        String context = "abcde\t0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }
    @Test
    public void consumeTagNameTest4() {
        String context = "abcde\n0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest5() {
        String context = "abcde\r0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest6() {
        String context = "abcde\f0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest7() {
        String context = "abcde 0000acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest8() {
        String context = "abcde>acdf";
        CharacterReader r = new CharacterReader(context);
        assertEquals("abcde", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest9() {
        String context = " ";
        CharacterReader r = new CharacterReader(context);
        assertEquals("", r.consumeTagName());
    }

    @Test
    public void consumeTagNameTest10() {
        String context = "<tag>";
        CharacterReader r = new CharacterReader(context);
        assertEquals("", r.consumeTagName());
    }




}
