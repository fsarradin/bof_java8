package bof_java8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneCoder {

    private static final Map<Character, String> mnemonics = new HashMap<Character, String>() {{
        put('2', "ABC");
        put('3', "DEF");
        put('4', "GHI");
        put('5', "JKL");
        put('6', "MNO");
        put('7', "PQRS");
        put('8', "TUV");
        put('9', "WXYZ");
    }};

    private static final Map<Character, Character> charCode = new HashMap<Character, Character>() {{
        mnemonics.forEach((digit, letters)->{
            letters.asChars().forEach(letter->{
                put(letter, digit);
            });
        });
    }};

    private final Map<String, Set<String>> numberForWords;

    public PhoneCoder(Set<String> words) {
        this.numberForWords = distributeWords(words);
    }

    public Set<String> translate(String number) {
        return numberForWords.get(number);
    }

    public static String wordCode(String word) {
        return (String) word.toUpperCase().asChars()
                .map(letter->charCode.get(letter).toString())
                .reduce("", (number, digit)->number + digit);
    }

    public static Map<String, Set<String>> distributeWords(Set<String> words) {
        Map<String, Set<String>> map = new HashMap<>();

        words.forEach(word->{
            String number = wordCode(word);
            if (!map.containsKey(number)) {
                map.put(number, new HashSet<String>());
            }
            map.get(number).add(word.toUpperCase());
        });

        return map;
    }

}
