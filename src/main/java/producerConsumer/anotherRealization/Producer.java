package producerConsumer.anotherRealization;

import java.util.Random;

public class Producer implements Runnable {

    private final Buffer<Integer> buffer;

    public Producer(Buffer<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            while (true) {

                synchronized (buffer) {
                    System.out.println("basicRealization.Producer: buffer size is " + buffer.getSize());
                    while (buffer.isFull()) {
                        System.out.println("basicRealization.Producer: buffer is full. Waiting...");
                        buffer.wait();
                        System.out.println("basicRealization.Producer: stop waiting");
                    }
                    System.out.println("basicRealization.Producer: adding element " + counter + "...");
                    buffer.add(counter);

                    buffer.notify();
                }
                counter++;
                Thread.sleep(new Random().nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
