package bof_java8.t04;

import org.junit.Test;

import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;

public class CallByNameByValueTest {

    public boolean loop() {
        return loop();
    }

    public boolean and_byValue(boolean a, boolean b) {
        return a && b;
    }

    public boolean and_byName(Supplier<Boolean> a, Supplier<Boolean> b) {
        return a.get() && b.get();
    }

    @Test
    public void should_complain() {
        boolean result = and_byValue(false, loop());

        assertThat(result).isFalse();
    }

    @Test
    public void should_succeed() {
        boolean result = and_byName(() -> false, this::loop);

        assertThat(result).isFalse();
    }

}
