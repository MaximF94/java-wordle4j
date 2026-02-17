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


        String finalWord;
        gameWords = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() == MAX_LETTERS) {
                finalWord = normalize(words.get(i));
                gameWords.add(finalWord);
            }
        }
    }

    private String normalize(String word) {
        word = word.toLowerCase();
        word = word.replace("ё", "е");
        word = word.trim();

        return word;
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
