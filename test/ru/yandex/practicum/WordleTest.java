package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    private static WordleDictionaryLoader dictionaryLoader;
    private static WordleDictionary dictionary;
    private static WordleGame wordleGame;
    private static LogDebug logDebug;

    @BeforeAll
    static void setUpWordleGame () {
        logDebug = new LogDebug("log.txt");
        dictionaryLoader = new WordleDictionaryLoader(logDebug);
        dictionary = dictionaryLoader.readWordsFromFile("words_ru.txt");
        wordleGame = new WordleGame(logDebug,dictionary);
    }

    @Test
    public void testRandomWordFiveLetters() {
        String randomWord = dictionary.getRandomWord();
        assertNotNull(randomWord);
        assertEquals(5, randomWord.length());
    }

    @Test
    public void testDictionaryLoad() {
        assertTrue(dictionary.getWords().size() > 0);
    }


    @Test
    public void testCorrectHint() {
        String testWord = "сидо";
        Map<Integer,Character> positionsHint = new HashMap<>();
        for (int i = 0; i < testWord.length(); i++) {
            positionsHint.put(i,testWord.charAt(i));
        }

        assertEquals("сидор", wordleGame.findWordHint(positionsHint));
    }


    @Test
    void testInvalidCharacterException() {
        Assertions.assertThrows(InvalidCharacterException.class, () -> {
            wordleGame.validateWrite("сидо1");
        });
    }

    @Test
    void testWordNotFoundInDictionary() {
        Assertions.assertThrows(WordNotFoundInDictionary.class, () -> {
            wordleGame.validateWrite("вчерс");
        });
    }

    @Test
    void testIncorrectWordLengthException() {
        Assertions.assertThrows(IncorrectWordLengthException.class, () -> {
            wordleGame.validateWrite("Привет");
        });
    }
}
