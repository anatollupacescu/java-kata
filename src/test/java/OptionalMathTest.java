import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class OptionalMathTest {

    @Test
    public void test() {
        Arrays.asList("unu", "doi", "trei")
                .parallelStream()
                .flatMap(this::flatStream)
//                .map(s -> s.toUpperCase())
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
        return Stream.of("lol " + integer);
    }

}
