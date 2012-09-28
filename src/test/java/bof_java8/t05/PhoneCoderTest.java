package bof_java8.t05;

import bof_java8.PhoneCoder;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

public class PhoneCoderTest {

    @Test
    public void should_get_5282_when_JAVA() {
        Assertions.assertThat(PhoneCoder.getNumberFrom("Java")).isEqualTo("5282");
    }

    @Test
    public void should_constains_JAVA() {
        Set<String> words = new HashSet<>(Arrays.asList("Java"));

        Map<String, Set<String>> map = PhoneCoder.distributeWords(words);

        assertThat(map.get("5282")).contains("JAVA");
    }

    @Test
    public void should_get_JAVA_when_5282() {
        Set<String> words = new HashSet<>(Arrays.asList("Java"));
        PhoneCoder phoneCoder = new PhoneCoder(words);

        Set<String> result = phoneCoder.translate("5282");

        assertThat(result).contains("JAVA");
    }

}
