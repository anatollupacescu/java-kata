package thread;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    private BlockingQueue<Runnable> futures = new LinkedBlockingQueue<>();
    private final ExecutorService service = new ThreadPoolExecutor(2, 2, 0L,TimeUnit.MILLISECONDS, futures);

    public void sleep(int millis, String name) {
        String id = Thread.currentThread().getName();
        String message = String.format("%s (%s) sleeping...", id, name);
        System.out.println(message);
        justSleep(millis);
        message = String.format("%s (%s) done!", id, name);
        System.out.println(message);
    }

    public void justSleep(int milis) {
        try {
            TimeUnit.MILLISECONDS.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        service.submit(() -> sleep(1000, "jora"));
        service.submit(() -> sleep(1000, "vasea"));
        service.submit(() -> sleep(1000, "grisa"));
        service.submit(() -> sleep(1000, "ninca"));
    }

    @Test
    public void test1() {
        service.submit(() -> sleep(1000, "ultimu"));
        while(true) {
            System.out.println("Queue size " + futures.size());
            justSleep(100);
            if(futures.size() == 0) {
                justSleep(400);
                break;
            }
        }
        service.shutdown();
    }
}
