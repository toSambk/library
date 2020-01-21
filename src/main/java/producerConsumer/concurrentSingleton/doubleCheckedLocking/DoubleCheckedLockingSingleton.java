package producerConsumer.concurrentSingleton.doubleCheckedLocking;

public class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    static DoubleCheckedLockingSingleton getInstance() {

        DoubleCheckedLockingSingleton current = instance;

        if(current == null) {

            synchronized (DoubleCheckedLockingSingleton.class) {

                current = instance;
                if(current == null) {
                    instance = current = new DoubleCheckedLockingSingleton();
                }
            }

        }

        return instance;
    }

}
