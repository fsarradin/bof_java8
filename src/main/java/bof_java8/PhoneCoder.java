package bof_java8;

import com.google.common.annotations.VisibleForTesting;

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

    private static final Map<Character, Character> LETTER_TO_DIGIT = getCharCode();
    private static Map<Character, Character> getCharCode() {
        Map<Character, Character> charCode = new HashMap<>();
        MNEMONICS.forEach((digit, letters) -> {
            letters.asChars().forEach(letter -> {
                charCode.put(letter, digit);
            });
        });
        return Collections.unmodifiableMap(charCode);
    }

//    private static final Map<Character, Character> charCode = new HashMap<Character, Character>() {{
//        MNEMONICS.forEach((digit, letters) -> {
//            letters.asChars().forEach(letter -> {
//                put(letter, digit);
//            });
//        });
//    }};

    private final Map<String, Set<String>> numberToWord;

    public PhoneCoder(Set<String> words) {
        this.numberToWord = distributeWords(words);
    }

    public Set<String> translate(String number) {
        return numberToWord.get(number);
    }

    @VisibleForTesting
    public static String getNumberFrom(String word) {
        return word.toUpperCase().asChars()
                .map(letter -> LETTER_TO_DIGIT.get(letter).toString())
                .reduce("", (number, digit) -> number + digit);
    }

    @VisibleForTesting
    public static Map<String, Set<String>> distributeWords(Set<String> words) {
        Map<String, Set<String>> distributedWords = new HashMap<>();

        words.forEach(word -> {
            String number = getNumberFrom(word);
            if (!distributedWords.containsKey(number)) {
                distributedWords.put(number, new HashSet<String>());
            }
            distributedWords.get(number).add(word.toUpperCase());
        });

        return distributedWords;
    }

}
