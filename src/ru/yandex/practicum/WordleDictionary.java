package ru.yandex.practicum;

import ru.yandex.exceptions.DictionaryLoadException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    public static final int MAX_LETTERS = 5;

    private List<String> words;
    private List<String> gameWords;
    LogDebug logDebug;

    public WordleDictionary(LogDebug logDebug, List<String> words) {
        this.words = words;
        this.logDebug = logDebug;

        try {
            if (words.isEmpty()) {
                throw new DictionaryLoadException("Словарь пуст.");
            }
        } catch (DictionaryLoadException e) {
            logDebug.writeExceptionToFile(e.getMessage());
        }

        normalize();
    }

    private void normalize() {
        String finalWord;
        gameWords = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            finalWord = words.get(i);
            if (finalWord.length() == MAX_LETTERS) {
                finalWord = finalWord.toLowerCase();
                finalWord = finalWord.replace("ё", "е");
                finalWord = finalWord.trim();
                gameWords.add(finalWord);
            }
        }

    }

    public List<String> getWords() {
        return gameWords;
    }

    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(gameWords.size());

        return gameWords.get(index);
    }


}
