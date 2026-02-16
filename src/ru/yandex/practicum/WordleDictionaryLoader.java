package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    private LogDebug logDebug;

    public WordleDictionaryLoader(LogDebug logDebug) {
        this.logDebug = logDebug;
    }

    public WordleDictionary readWordsFromFile(String filename) {

        List<String> fileWords = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            while (fileReader.ready()) {
                fileWords.add(fileReader.readLine());
            }
        } catch (IOException ex) {
            logDebug.writeExceptionToFile("Произошла ошибка во время чтения файла: " + ex.getMessage());
        }

        return new WordleDictionary(logDebug, fileWords);
    }
}
