package bof_java8.t03;

import org.fest.assertions.Condition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Group elements in a collection according to a criteria
 */
public class AgeTest {

    @Test
    public void should_distributed_ages() {
        Iterable<Person> persons = Arrays.asList(
                new Person("Bob", 30),
                new Person("Brice", 10),
                new Person("Alexander", 1)
        );

        Map<DevelopmentStage, ? extends Iterable<Person>> personDistribution;

        personDistribution = distribute_1(persons);

        assertThat(personDistribution.get(DevelopmentStage.INFANT)).satisfies(containsOnlyPersonWithName("Alexander"));
        assertThat(personDistribution.get(DevelopmentStage.CHILD)).satisfies(containsOnlyPersonWithName("Brice"));
        assertThat(personDistribution.get(DevelopmentStage.ADULT)).satisfies(containsOnlyPersonWithName("Bob"));
    }

    private Map<DevelopmentStage, ? extends Iterable<Person>> distribute_1(Iterable<Person> persons) {
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

    private Condition<Iterator<?>> containsOnlyPersonWithName(final String name) {
        return new Condition<Iterator<?>>() {
            @Override
            public boolean matches(Iterator<?> iterator) {
                return name.equals(((Person) iterator.next()).getName()) && !iterator.hasNext();
            }
        };
    }
}
