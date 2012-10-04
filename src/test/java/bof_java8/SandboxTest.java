package bof_java8;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SandboxTest {

    interface MyFunction<T, R> {
        R apply(T x);
    }

    @Test
    public void should_do_something() {
        MyFunction<Integer, MyFunction<Integer, Integer>> function = x -> y -> x + y;
        assertThat(function.apply(1).apply(2)).isEqualTo(3);
    }

}
