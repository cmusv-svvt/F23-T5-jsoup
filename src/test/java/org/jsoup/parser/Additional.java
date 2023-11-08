package org.jsoup.parser;

import org.jsoup.integration.ParseTest;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;

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
