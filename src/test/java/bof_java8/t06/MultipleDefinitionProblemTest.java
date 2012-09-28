package bof_java8.t06;

import org.junit.Test;

public class MultipleDefinitionProblemTest {

    public static interface Garage {
        void enterByGarage();

        void enter() default { enterByGarage(); }
    }

    public static interface Veranda {
        void enterByVeranda();

        void enter() default { enterByVeranda(); }
    }

//    public static interface MyHouse extends Veranda, Garage {
//    }

    @Test
    public void test_myHouse() {
//        MyHouse house = null;
    }

}
