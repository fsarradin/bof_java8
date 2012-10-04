package bof_java8.t07;

import org.junit.Test;

public class DiamondProblemTest {

    public static interface HouseElement {
        void enter() default { ; };
    }

    public static interface Garage extends HouseElement {
    }

    public static interface Veranda extends HouseElement {
    }

    public static interface MyHouse extends Veranda, Garage {
    }

    @Test
    public void test_myHouse() {
        MyHouse house = null;
    }

}
