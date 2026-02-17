package ru.yandex.practicum;

import ru.yandex.exceptions.IncorrectWordLengthException;
import ru.yandex.exceptions.InvalidCharacterException;
import ru.yandex.exceptions.WordNotFoundInDictionary;

import java.util.*;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    public static final int MAX_ATTEMPTS = 6;

    private String answer;

    private int steps;

    private WordleDictionary dictionary;

    private LogDebug logDebug;

    Scanner scanner = new Scanner(System.in);

    public WordleGame(LogDebug logDebug, WordleDictionary dictionary) {
        this.logDebug = logDebug;
        this.dictionary = dictionary;
    }

    Map<Integer, Character> rightCharPositions = new HashMap<>();

    public void game() {

        List<String> dictionaryWords = dictionary.getWords();

        System.out.println("Угадайте слово из 5 букв");
        Random random = new Random();
        answer = dictionary.getRandomWord();
        Set<Character> randomWordLetters = new HashSet<>();

        for (int i = 0; i < answer.length(); i++) {
            randomWordLetters.add(answer.charAt(i));
        }
        String wordAnswer;
        steps = 1;
        for (int i = 1; i < MAX_ATTEMPTS; i++) {
            do {
                wordAnswer = scanner.nextLine();

                try {
                    validateWrite(wordAnswer);
                } catch (WordNotFoundInDictionary | IncorrectWordLengthException | InvalidCharacterException e) {
                    logDebug.writeExceptionToFile(e.getMessage());
                }

                if (wordAnswer.isBlank()) {
                    if (rightCharPositions.isEmpty()) {
                        System.out.println(dictionaryWords.get(random.nextInt(dictionaryWords.size())));
                    } else {
                        System.out.println(findWordHint(rightCharPositions));
                    }
                }

            } while (wordAnswer.length() != dictionary.MAX_LETTERS);

            findWord(wordAnswer, answer, randomWordLetters);
        }
        System.out.println("Вы не угадали слово. Ответ: " + answer);
    }

    private void findWord(String wordAnswer, String answer, Set<Character> randomWordLetters) {
        if (wordAnswer.equals(answer)) {
            System.out.println("Вы отгадали слово: " + answer);
            return;
        } else {
            for (int j = 0; j < dictionary.MAX_LETTERS; j++) {
                if (wordAnswer.charAt(j) == answer.charAt(j)) {
                    System.out.print("+");
                    rightCharPositions.put(j, wordAnswer.charAt(j));
                } else if (randomWordLetters.contains(wordAnswer.charAt(j))) {
                    System.out.print("^");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        steps++;

        if (steps >= MAX_ATTEMPTS) {
            logDebug.writeExceptionToFile("Вы исчерпали все попытки");
        }
    }

    public String findWordHint(Map<Integer, Character> positions) {
        StringBuilder currentWord = new StringBuilder();
        int counter;

        List<String> dictionaryWords = dictionary.getWords();
        List<String> matchWords = new ArrayList<>();

        for (int i = 0; i < dictionaryWords.size(); i++) {
            currentWord.setLength(0);
            currentWord.append(dictionaryWords.get(i));
            counter = 0;

            for (int j = 0; j < currentWord.length(); j++) {
                if (positions.containsKey(j) && currentWord.charAt(j) == positions.get(j)) {
                    counter++;
                }
            }

            if (positions.size() == counter) {
                matchWords.add(currentWord.toString());
            }

        }
        Random random = new Random();
        int index = random.nextInt(matchWords.size());

        return matchWords.get(index);
    }

    public void validateWrite(String testWord) throws IncorrectWordLengthException, InvalidCharacterException,
            WordNotFoundInDictionary {

        if (!testWord.isBlank() && testWord.length() != dictionary.MAX_LETTERS) {
            throw new IncorrectWordLengthException("Слово должно состоять из 5 букв.");
        }

        for (char c : testWord.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new InvalidCharacterException("Слово содержит недопустимые символы.");
            }
        }

        if (!testWord.isBlank() && !dictionary.getWords().contains(testWord)) {
            throw new WordNotFoundInDictionary("Слово отсутствует в словаре: " + testWord);
        }

    }
}
