package bof_java8.t01;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Map a transformation on a collection
 */
public class SalaryIncreaseTest {

    @Test
    public void should_increase_salary_imperative_way() {
        Collection<Double> salaries = asList(20_000.0, 30_000.0, 40_000.0, 50_000.0);

        Collection<Double> result = increaseSalaries_imperative(salaries, 0.02);

        assertThat(result).containsOnly(20_400.0, 30_600.0, 40_800.0, 51_000.0);
    }

    private Collection<Double> increaseSalaries_imperative(Collection<Double> salaries,
                                                           double rate) {
        List<Double> result = new ArrayList<>();

        for (Double salary : salaries) {
            result.add(salary * (1.0 + rate));
        }

        return result;
    }

    @Test
    public void should_increase_salary_guava_way() {
        Collection<Double> salaries = asList(20_000.0, 30_000.0, 40_000.0, 50_000.0);

        Collection<Double> result = increaseSalaries_guava(salaries, 0.02);

        assertThat(result).containsOnly(20_400.0, 30_600.0, 40_800.0, 51_000.0);
    }

    private Collection<Double> increaseSalaries_guava(Collection<Double> salaries,
                                                      double rate) {
        return Collections2.transform(salaries, salary -> salary * (1.0 + rate));
    }

    @Test
    public void should_increase_salary_java8_way() {
        Collection<Double> salaries = asList(20_000.0, 30_000.0, 40_000.0, 50_000.0);

        Collection<Double> result = increaseSalaries_java8(salaries, 0.02);

        assertThat(result).containsOnly(20_400.0, 30_600.0, 40_800.0, 51_000.0);
    }

    private Collection<Double> increaseSalaries_java8(Collection<Double> salaries,
                                                      double rate) {
        return salaries.stream()
                .map(salary -> salary * (1.0 + rate))
                .into(new ArrayList<Double>());
    }

}
