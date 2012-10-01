package bof_java8.t07;

import org.junit.Test;

public class DiamondProblemTest {

    public static interface HouseElement {
        void enter();
    }

    public static interface Garage {
    }

    public static interface Veranda {
    }

    public static interface MyHouse extends Veranda, Garage {
    }

    @Test
    public void test_myHouse() {
        MyHouse house = null;
    }

}
