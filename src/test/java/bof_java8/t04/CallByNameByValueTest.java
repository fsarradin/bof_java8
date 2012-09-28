package bof_java8.t04;

import org.junit.Test;

import java.util.functions.Block;
import java.util.functions.Factory;

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

    @Test(expected = StackOverflowError.class)
    public void should_complain() {
        and_byValue(false, loop());
    }

    @Test
    public void should_succeed() {
        and_byName(() -> false, () -> loop());
    }

}
