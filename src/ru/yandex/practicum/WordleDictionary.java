package ru.yandex.practicum;

import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private List<String> words;
    LogDebug logDebug;

    public WordleDictionary(LogDebug logDebug,List<String> words) {
        this.words = words;
        this.logDebug = logDebug;

        try {
            if (words.isEmpty()) {
                throw new DictionaryLoadException("Словарь пуст.");
            }
        } catch (DictionaryLoadException e) {
            logDebug.writeExceptionToFile(e.getMessage());
        }
    }

    public List<String> getWords() {
        return words;
    }

    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());

        return words.get(index);
    }


}
