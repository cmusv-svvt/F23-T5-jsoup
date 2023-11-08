package org.jsoup.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class Additional {
    private CharacterReader reader;
    private static final String BUFFER_CONTENT = "This is an EXAMPLE test INPUT.";

    @Before
    public void setUp() {
        this.reader = new CharacterReader(BUFFER_CONTENT);
    }

    @Test
    public void testContainsIgnoreCaseWithNonCapitalizedExistingWord() {
        setUp();
        assertTrue(reader.containsIgnoreCase("example"));
    }

    @Test
    public void testContainsIgnoreCaseWithCapitalizedExistingWord() {
        setUp();
        assertTrue(reader.containsIgnoreCase("EXAMPLE"));
    }

    @Test
    public void testContainsIgnoreCaseWithNonCapitalizedNonExistingWord() {
        setUp();
        assertFalse(reader.containsIgnoreCase("hello"));
    }


}
