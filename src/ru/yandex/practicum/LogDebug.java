package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogDebug {

    private String filename;

    public LogDebug(String filename) {
        this.filename = filename;
    }

    public void writeExceptionToFile(String exceptionInfo) {
        try (FileWriter writer = new FileWriter(filename, StandardCharsets.UTF_8,true)) {
            writer.write(exceptionInfo);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
