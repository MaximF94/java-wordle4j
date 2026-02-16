package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogDebug implements AutoCloseable{

    FileWriter writer;
    public LogDebug(String filename) {
        try {
            writer = new FileWriter(filename, StandardCharsets.UTF_8,true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //К сожалению, код перестал работать. Что я сделал не так?
    public void writeExceptionToFile(String exceptionInfo) {
        try {
            writer.write(exceptionInfo);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}