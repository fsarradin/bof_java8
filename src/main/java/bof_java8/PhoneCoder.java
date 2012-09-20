package bof_java8;

import com.google.common.annotations.VisibleForTesting;

import java.util.*;
import java.util.functions.Factory;

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

    private static final Map<Character, Character> CHAR_CODE = getCharCode(MNEMONICS);
    private static Map<Character, Character> getCharCode(Map<Character, String> mnemonics) {
        Map<Character, Character> charCode = new HashMap<>();
        mnemonics.forEach((digit, letters) -> {
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

    private final Map<String, Set<String>> numberForWords;

    public PhoneCoder(Set<String> words) {
        this.numberForWords = distributeWords(words);
    }

    public Set<String> translate(String number) {
        return numberForWords.get(number);
    }

    @VisibleForTesting
    protected static String wordCode(String word) {
        return word.toUpperCase().asChars()
                .map(letter -> CHAR_CODE.get(letter).toString())
                .reduce("", (number, digit) -> number + digit);
    }

    public static Map<String, Set<String>> distributeWords(Set<String> words) {
        Map<String, Set<String>> map = new HashMap<>();

        words.forEach(word -> {
            String number = wordCode(word);
            if (!map.containsKey(number)) {
                map.put(number, new HashSet<String>());
            }
            map.get(number).add(word.toUpperCase());
        });

        return map;
    }

}
