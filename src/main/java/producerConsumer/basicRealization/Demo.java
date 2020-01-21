package producerConsumer.basicRealization;

public class Demo {

    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);

        new Thread(new Producer(buffer)).start();

        new Thread(new Consumer(buffer)).start();

        new Thread(new Producer(buffer)).start();

        new Thread(new Producer(buffer)).start();

    }


}
