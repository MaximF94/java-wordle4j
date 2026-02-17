package ru.yandex.exceptions;

public class WordNotFoundInDictionary extends Exception {

    public WordNotFoundInDictionary(String message) {
        super(message);
    }
}
