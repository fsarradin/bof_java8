package bof_java8.t05;

import bof_java8.PhoneCoder;
import org.junit.Test;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;


public class PhoneCoderTest {

    @Test
    public void should_get_2_when_A_B_or_C() {
        HashMap<Character, String> mnemonics = new HashMap<Character, String>() {{
            put('2', "ABC");
        }};

        Map<Character, Character> charCode = PhoneCoder.charToDigit(mnemonics);

        assertThat(charCode)
                .hasSize(3)
                .contains(entry('A', '2'), entry('B', '2'), entry('C', '2'));
    }

    @Test
    public void should_get_5282_when_JAVA() {
        assertThat(PhoneCoder.getNumberFrom("Java")).isEqualTo("5282");
    }

    @Test
    public void should_constains_JAVA() {
        Set<String> words = new HashSet<>(Arrays.asList("Java"));

        Map<String, Collection<String>> map = PhoneCoder.distributeWords(words);

        assertThat(map.get("5282")).contains("JAVA");
    }

    @Test
    public void should_get_JAVA_when_5282() {
        Set<String> words = new HashSet<>(Arrays.asList("Java"));
        PhoneCoder phoneCoder = new PhoneCoder(words);

        Collection<String> result = phoneCoder.translate("5282");

        assertThat(result).contains("JAVA");
    }

}
