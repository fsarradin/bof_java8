package bof_java8.t07;

import org.junit.Test;

public class MultipleDefinitionProblemTest {

    public static interface Garage {
        void enterByGarage();

        default void enter() { enterByGarage(); }
    }

    public static interface Veranda {
        void enterByVeranda();

        default void enter() { enterByVeranda(); }
    }

//    public static interface MyHouse extends Veranda, Garage {
//    }

    @Test
    public void test_myHouse() {
//        MyHouse house = null;
    }

}
