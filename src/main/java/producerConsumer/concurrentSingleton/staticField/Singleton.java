package producerConsumer.concurrentSingleton.staticField;

public class Singleton {

    private static final Singleton instance = new Singleton();

    public Singleton getInstance() {
        return instance;
    }

}
