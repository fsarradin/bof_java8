package bof_java8;

import java.util.*;

public class PhoneCoder {

    private static final Map<Character, String> MNEMONICS = new HashMap<Character, String>() {{
        put('2', "ABC");
        put('3', "DEF");
        put('4', "GHI");
        put('5', "JKL");
        put('6', "MNO");
        put('7', "PQRS");
        put('8', "TUV");
        put('9', "WXYZ");
    }};
    private static final Map<Character, Character> DIGITS = charToDigit(MNEMONICS);
    private final Map<String, Collection<String>> numbers;

    public static Map<Character, Character> charToDigit(Map<Character, String> mnemonics) {
        HashMap<Character, Character> digits = new HashMap<>();

        mnemonics.forEach((digit, letters)
                -> letters.chars().forEach(letter -> digits.put(letter, digit)));

        return digits;
    }

    public static String getNumberFrom(String word) {
        return word.toUpperCase().chars()
                .map(letter -> DIGITS.get(letter).toString())
                .reduce("", (number, digit) -> number + digit);
    }

    public static Map<String, Collection<String>> distributeWords(Set<String> words) {
        return words.stream()
                .map(String::toUpperCase)
                .groupBy(PhoneCoder::getNumberFrom);
    }

    public PhoneCoder(Set<String> words) {
        numbers = distributeWords(words);
    }

    public Collection<String> translate(String number) {
        return numbers.get(number);
    }

}
