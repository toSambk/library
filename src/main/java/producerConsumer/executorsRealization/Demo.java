package producerConsumer.executorsRealization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        Demo demo = new Demo();

        Buffer<Integer> buffer = new Buffer<>(50);

        demo.executor.submit(new Producer(buffer));

        demo.executor.submit(new Producer(buffer));

        demo.executor.submit(new Producer(buffer));

        demo.executor.submit(new Consumer(buffer));

    }

}
