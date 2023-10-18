package com.keyin;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
public class SuggestionEngineTest {

    SuggestionEngine suggestionEngine = new SuggestionEngine();
    @Test
    void testLoadDictionaryData() {
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(suggestionEngine.getWordSuggestionDB().containsKey("abacus"), "The word 'abacus' should be in the database");
    }

    @Test
    void testCorrectWord() {
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        String result = suggestionEngine.generateSuggestions("abacus");
        assertEquals("", result, "Expected an empty string for a correct word");
    }

    @Test
    void testIncorrectWord() {
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        String result = suggestionEngine.generateSuggestions("abakus");
        assertTrue(!result.isEmpty(), "Expected suggestions for an incorrect word");
    }
}