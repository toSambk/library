package producerConsumer.basicRealization;

import java.util.Random;

public class Producer implements Runnable {

    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        int i = 0;
        try {
            while (true) {
                System.out.println("basicRealization.Producer: adding element " + i + "...");
                buffer.addElement(i);
                i++;
                Thread.sleep(new Random().nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
