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

    private static final Map<Character, Character> CHAR_TO_DIGIT = charToDigit(MNEMONICS);
    public static Map<Character, Character> charToDigit(Map<Character, String> mnemonics) {
        HashMap<Character, Character> charToDigit = new HashMap<>();

        mnemonics.forEach((digit, letters) -> {
            charToDigit.addAll(letters.asChars().mapped(letter -> digit));
        });

        return charToDigit;
    }

    public static String getNumberFrom(String word) {
        return word.toUpperCase().asChars()
                .map(letter -> CHAR_TO_DIGIT.get(letter).toString())
                .reduce("", (result, digit) -> result + digit);
    }

    public static Map<String, Collection<String>> distributeWords(Set<String> words) {
        return words.stream()
                .map(word -> word.toUpperCase())
                .groupBy(PhoneCoder::getNumberFrom);
    }

    private Map<String, Collection<String>> numbers;

    public PhoneCoder(Set<String> words) {
        this.numbers = distributeWords(words);
    }

    public Collection<String> translate(String number) {
        return numbers.get(number);
    }

}
