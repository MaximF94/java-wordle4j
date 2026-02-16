package ru.yandex.exceptions;

public class TooManyAttemptsException extends Exception {

    public TooManyAttemptsException(String message) {
        super(message);
    }

}
