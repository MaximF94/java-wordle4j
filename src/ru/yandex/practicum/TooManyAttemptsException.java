package ru.yandex.practicum;

public class TooManyAttemptsException extends Throwable {

    public TooManyAttemptsException(String message) {
        super(message);
    }

}
