package rx;

import org.junit.Test;
import rx.functions.Action;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class RxTest {

    private final ExecutorService service = Executors.newFixedThreadPool(2);

    public static void print(Object v) {
        justSleep(300);
        System.out.println(Thread.currentThread().getName() + " received value: " + v);
    }

    @Test
    public void test1() {
        Observable<Integer> source = Observable.range(0, 5);
        source.subscribeOn(Schedulers.newThread())
                .subscribe((i) -> Observable.just(i)
                                .observeOn(Schedulers.from(service))
                                .subscribe(RxTest::print));

        justSleep(1000);
    }

    @Test
    public void test2() {
        Observable<Integer> source = Observable.range(0, 5);
        source
                .map(Observable::just)
                .subscribe(integerObservable -> integerObservable
                        .observeOn(Schedulers.from(service))
                        .subscribe(RxTest::print));

        justSleep(2000);
    }

    @Test
    public void test3() {
        Observable.range(0, 5)
                .window(3)
                .subscribe(window -> window.subscribe(RxTest::print));
    }

    @Test
    public void test4() {
    	Observable<String> ob1 = observableFactory("LOL");
		Observable<String> ob2 = observableFactory("ROFL");
		Func2<String, String, List<String>> func = (i1, i2) -> Arrays.asList(i1, i2);
		Observable<List<String>> zip = Observable.zip(ob1, ob2, func);
//		Observable<List<String>> emptyResponse = emptyResponse();
//		Observable.amb(zip, emptyResponse).take(1).subscribe(list -> System.out.println(list));
	}

    @Test
    public void test5() {
    	Observable.merge(observableFactory("real"), emptyResponse()).first().subscribe(list -> System.out.println(list));
    }

    @Test
    public void test6() {
    	observableFactory("real")
    	.window(200)
//    	.doOnError(Throwable::printStackTrace)
    	.single()
    	.subscribe(list -> {
    		System.out.println(list);
    	});
    }
    
    private Observable<String> emptyResponse() {
    	Future<String> fugure = service.submit(() -> {
    		justSleep(100);
    		return "replacement";
    	});
		return Observable.from(fugure);
	}

	private Observable<String> observableFactory(String value) {
    	Future<String> fugure = service.submit(() -> {
    		justSleep(1000);
    		return value;
    	});
		return Observable.from(fugure);
    }
    
    @Test
    public void enrichTest() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            return "42";
        }, service).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            return "55";
        }, service),
            (u, d) -> {
            	return String.format("%s-%s", u, d);
            })
            .thenAccept(RxTest::print).get();
    }

    public static void justSleep(int milis) {
        try {
            TimeUnit.MILLISECONDS.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
