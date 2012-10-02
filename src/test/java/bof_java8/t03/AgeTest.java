package bof_java8.t03;

import org.fest.assertions.core.Condition;
import org.junit.Test;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Group elements in a collection according to a criteria
 */
public class AgeTest {

    private static class Person {

        private final String name;

        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    private static enum DevelopmentStage {
        INFANT,
        CHILD,
        ADOLESCENT,
        ADULT,
        SENIOR;
    }

    private static DevelopmentStage getDevelopmentStage(Person person) {
        DevelopmentStage development;

        if (person.getAge() <= 1) {
            development = DevelopmentStage.INFANT;
        } else if (person.getAge() <= 12) {
            development = DevelopmentStage.CHILD;
        } else if (person.getAge() <= 19) {
            development = DevelopmentStage.ADOLESCENT;
        } else if (person.getAge() <= 59) {
            development = DevelopmentStage.ADULT;
        } else {
            development = DevelopmentStage.SENIOR;
        }

        return development;
    }

    /* IMPERATIVE SOLUTION */

    @Test
    public void should_distributed_ages_imperative_way() {
        Iterable<Person> persons = Arrays.asList(
                new Person("Bob", 30),
                new Person("Brice", 10),
                new Person("Alexander", 1)
        );

        Map<DevelopmentStage, ? extends Iterable<Person>> personDistribution
                = distribute_imperative(persons);

        assertThat(personDistribution.get(DevelopmentStage.INFANT))
                .are(personWithName("Alexander"));
        assertThat(personDistribution.get(DevelopmentStage.CHILD))
                .are(personWithName("Brice"));
        assertThat(personDistribution.get(DevelopmentStage.ADULT))
                .are(personWithName("Bob"));
    }

    private Map<DevelopmentStage, ? extends Iterable<Person>> distribute_imperative(Iterable<Person> persons) {
        Map<DevelopmentStage, Collection<Person>> distribution = new HashMap<>();

        for (Person person : persons) {
            DevelopmentStage development = getDevelopmentStage(person);

            if (!distribution.containsKey(development)) {
                distribution.put(development, new ArrayList<Person>());
            }
            distribution.get(development).add(person);
        }

        return distribution;
    }

    /* JAVA 8 SOLUTION */

    @Test
    public void should_distributed_ages_java8_way() {
        Iterable<Person> persons = Arrays.asList(
                new Person("Bob", 30),
                new Person("Brice", 10),
                new Person("Alexander", 1)
        );

        Map<DevelopmentStage, ? extends Iterable<Person>> personDistribution
                = distribute_java8(persons);

        assertThat(personDistribution.get(DevelopmentStage.INFANT))
                .are(personWithName("Alexander"));
        assertThat(personDistribution.get(DevelopmentStage.CHILD))
                .are(personWithName("Brice"));
        assertThat(personDistribution.get(DevelopmentStage.ADULT))
                .are(personWithName("Bob"));
    }

    private Map<DevelopmentStage, ? extends Iterable<Person>> distribute_java8(Iterable<Person> persons) {
        return null;
    }

    private Condition<Person> personWithName(final String name) {
        return new Condition<Person>() {
            @Override
            public boolean matches(Person person) {
                return name.equals(person.getName());
            }
        };
    }
}
