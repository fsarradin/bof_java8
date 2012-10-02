package bof_java8.t04;

import org.junit.Test;

import java.util.functions.Block;
import java.util.functions.Factory;

import static org.fest.assertions.api.Assertions.assertThat;

public class CallByNameByValueTest {

    public boolean loop() {
        return loop();
    }

    public boolean and_byValue(boolean a, boolean b) {
        return a && b;
    }

    public boolean and_byName(Factory<Boolean> a, Factory<Boolean> b) {
        return a.make() && b.make();
    }

    @Test
    public void should_complain() {
        boolean result = and_byValue(false, loop());

        assertThat(result).isFalse();
    }

    @Test
    public void should_succeed() {
        boolean result = and_byName(() -> false, () -> loop());

        assertThat(result).isFalse();
    }

}
