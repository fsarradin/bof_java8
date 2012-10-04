package bof_java8.t01;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.streams.Streams;

import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Map a transformation on a collection
 */
public class SalaryIncreaseTest {

    @Test
    public void should_increase_salary_imperative_way() {
        Iterable<Double> salaries = asList(20_000.0, 30_000.0, 40_000.0, 50_000.0);

        Iterable<Double> result = increaseSalaries_imperative(salaries, 0.02);

        assertThat(result).containsOnly(20_400.0, 30_600.0, 40_800.0, 51_000.0);
    }

    private Iterable<Double> increaseSalaries_imperative(Iterable<Double> salaries,
                                                         double rate) {
        List<Double> result = new ArrayList<>();

        for (Double salary : salaries) {
            result.add(salary * (1.0 + rate));
        }

        return result;
    }

    @Test
    public void should_increase_salary_guava_way() {
        Iterable<Double> salaries = asList(20_000.0, 30_000.0, 40_000.0, 50_000.0);

        Iterable<Double> result = increaseSalaries_guava(salaries, 0.02);

        assertThat(result).containsOnly(20_400.0, 30_600.0, 40_800.0, 51_000.0);
    }

    private Iterable<Double> increaseSalaries_guava(Iterable<Double> salaries,
                                                    final double rate) {
        return Iterables.transform(salaries, salary -> salary * (1.0 + rate));
    }

    @Test
    public void should_increase_salary_java8_way() {
        Iterable<Double> salaries = asList(20_000.0, 30_000.0, 40_000.0, 50_000.0);

        Iterable<Double> result = increaseSalaries_java8(salaries, 0.02);

        assertThat(result).containsOnly(20_400.0, 30_600.0, 40_800.0, 51_000.0);
    }

    private Iterable<Double> increaseSalaries_java8(Iterable<Double> salaries,
                                                    double rate) {
        return Streams.stream(salaries)
                .map(salary -> salary * (1.0 + rate))
                .into(new ArrayList<Double>());
    }

}
