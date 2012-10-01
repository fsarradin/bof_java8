package bof_java8.t08;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import static org.fest.assertions.api.Assertions.assertThat;


public class FakeBrokenMixinTest {

    public static interface FakeBrokenMixin {

        static Map<FakeBrokenMixin, String> NAMES = Collections.synchronizedMap(new WeakHashMap<FakeBrokenMixin, String>());

        String getName() default { return NAMES.get(this); }
        void setName(String name) default { NAMES.put(this, name); }

    }

    public static interface MyInterface extends FakeBrokenMixin, Runnable {}

    private static MyInterface newMyInterface() {
        return () -> { System.out.println("X"); };
    }

    @Test
    public void should_get_different_names() {
        MyInterface x1 = newMyInterface();
        MyInterface x2 = newMyInterface();

        x1.setName("x1");
        x2.setName("x2");

        assertThat(x1).isNotEqualTo(x2);

        assertThat(x1.getName()).isNotEqualTo(x2.getName());
    }

}
