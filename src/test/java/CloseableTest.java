import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CloseableTest {

    @Test
    public void first() {
        Stream.of(1, 2, 3, 4)
                .map(i -> new Object() {
                    int j = i;
                })
                .filter(anon -> anon.j > 3)
                .map(anon -> anon.j)
                .forEach(System.out::println);
    }

    @Test
    public void second() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        try (Stream<Integer> s = list.stream().onClose(Unchecked.wrap(new MyCloseable()))) {
            s.collect(Collectors.toList());
        }
    }

    @Test
    public void mainTest() throws InterruptedException {
        Unchecked un = Unchecked.wrap(new MyCloseable());
        System.out.println(un.toString());
        TimeUnit.SECONDS.sleep(1);
    }

    interface Unchecked extends AutoCloseable, Runnable {

        @Override
        default public void run() {
            try {
                close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static Unchecked wrap(AutoCloseable c) {
            return () -> {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }  ;
        }
    }

    static class MyCloseable implements AutoCloseable {
        @Override
        public void close() throws IOException {
            System.out.println("Closing mine");
        }
    }
}