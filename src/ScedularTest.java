import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScedularTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
//        scheduler.schedule(
//                () -> System.out.println("text"),
//                2,
//                // можна замість чекунд вибрати , хвилини, години, дні
//                TimeUnit.SECONDS
//
//        );

        // scheduleWithFixedDelay метод циклу черезкожні 2 сек виводить текс, після того як завершилась інша задача
        // scheduleAtFixedRate  відлік часупочинається як тільки попередня задача почала виконання
        scheduler.scheduleAtFixedRate(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("text" +System.currentTimeMillis());

                },
                0,
                1,
                // можна замість чекунд вибрати , хвилини, години, дні
                TimeUnit.SECONDS

        );
    }
}
