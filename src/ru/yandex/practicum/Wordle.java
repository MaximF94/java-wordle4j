package ru.yandex.practicum;

import java.util.*;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {


        try (LogDebug logDebug = new LogDebug("log.txt")) {
            WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader(logDebug);
            WordleDictionary wordleDictionary = wordleDictionaryLoader.readWordsFromFile("words_ru.txt");
            WordleGame wordleGame = new WordleGame(logDebug, wordleDictionary);
            wordleGame.game();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
