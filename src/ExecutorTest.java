import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        third();

    }

    public static void third() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i<10; i++){
            Future<?> submit = executorService.submit(new SumCallable(i, i+10));

            futures.add(submit);
        }
        while (true) {
            long countOfFinishedThreads = futures.stream().filter(f -> f.isDone()).count();
            System.out.println("countOfFinishedThreads = " + countOfFinishedThreads);

            if (countOfFinishedThreads == futures.size()) {
                for (Future f: futures) {
                    System.out.println(f.get());

                }
                System.exit(0);
            }
            Thread.sleep(500);
        }
    }


    public void second() throws InterruptedException {
        // вивід черезкожну секунду новго числа до 10
        //   ExecutorService executorService = Executors.newSingleThreadExecutor();
        // вивід по два числа , потоки
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i<10; i++){
            Future<?> submit = executorService.submit(new PrintRunnable(i));
            futures.add(submit);
        }

        while (true) {
            Thread.sleep(500);
            if (futures.stream().allMatch(f -> f.isDone())){
                System.exit(0);
            }
        }

    }

    public static void first(){
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));


    }
}
