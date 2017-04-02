import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class OptionalMathTest {

    @Test
    public void test() {
        Arrays.asList("unu", "doi", "trei")
                .parallelStream()
                .flatMap(this::flatStream)
                .forEach(this::print);
    }

    private void print(String s) {
        System.out.println(String.format("Thread %s, num: %s", Thread.currentThread().getName(), s));
    }

    private Stream<String> flatStream(String integer) {
        try {
            TimeUnit.MILLISECONDS.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = Thread.currentThread().getName();
        return Stream.of(name + " " + integer, name + " " + integer, name + " " + integer);
    }

    Optional<Double> sqrt(Double d) {
        return d >= 0 ? Optional.of(Math.sqrt(d)) : Optional.empty();
    }

    Double inc(Double d) {
        return d + 1;
    }
}
