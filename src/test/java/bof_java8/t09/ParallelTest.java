package bof_java8.t09;

import org.junit.Test;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class ParallelTest {

    @Test
    public void should_() {
        List<Long> longs = new ArrayList<>();

        for (long i = 0; i < 2_000_000; i++) {
            longs.add(i);
        }

        int count = 100;
        double delta;

        delta = getAverageTimeForStream(longs, count);
        System.out.println("Average Stream Time: " + (delta * 1e-6));

        delta = getAverageTimeForParallel(longs, count);
        System.out.println("Average Parallel Time: " + (delta * 1e-6));
    }

    private double getAverageTimeForParallel(List<Long> longs, int count) {
        double delta = 0.0;
        long start;
        Optional<Long> sum;

        for (int i = 0; i < count; i++) {
            start = System.nanoTime();
            sum = getParallelSum(longs);
            delta += System.nanoTime() - start;
            assertThat(sum.isPresent()).isTrue();
        }

        return delta / count;
    }

    private Optional<Long> getParallelSum(List<Long> longs) {
        return longs.parallel()
                    .filter(x -> x % 2 == 0)
                    .map(x -> x / 2)
                    .reduce((x, y) -> x + y);
    }

    private double getAverageTimeForStream(List<Long> longs, int count) {
        double delta = 0.0;
        long start;
        Optional<Long> sum;

        for (int i = 0; i < count; i++) {
            start = System.nanoTime();
            sum = getStreamSum(longs);
            delta += System.nanoTime() - start;
            assertThat(sum.isPresent()).isTrue();
        }

        return delta / count;
    }

    private Optional<Long> getStreamSum(List<Long> longs) {
        return longs.stream()
                    .filter(x -> x % 2 == 0)
                    .map(x -> x / 2)
                    .reduce((x, y) -> x + y);
    }

}
