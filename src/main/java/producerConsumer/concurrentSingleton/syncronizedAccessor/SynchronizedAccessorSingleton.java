package producerConsumer.concurrentSingleton.syncronizedAccessor;

public class SynchronizedAccessorSingleton {

    private static SynchronizedAccessorSingleton instance;

    public synchronized static SynchronizedAccessorSingleton getInstance() {

        if(instance == null) {
            instance = new SynchronizedAccessorSingleton();
        }

        return instance;

    }

}
