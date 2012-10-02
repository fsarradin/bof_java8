package bof_java8.t08;

import com.google.common.base.Function;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class OptimTest {

    public Function<Object, Integer> constant() {
        return _ -> 1;
    }

    public Function<Object, Integer> constant(int value) {
        return _ -> value;
    }

    @Test
    public void test_references() {
        Function<Object, Integer> c1 = constant();
        Function<Object, Integer> c2 = constant();
        Function<Object, Integer> c3 = constant(1);
        Function<Object, Integer> c4 = constant(1);

        assertThat(c1).isSameAs(c2);

        assertThat(c1).isNotSameAs(c3);
        assertThat(c3).isNotSameAs(c4);
    }

}
