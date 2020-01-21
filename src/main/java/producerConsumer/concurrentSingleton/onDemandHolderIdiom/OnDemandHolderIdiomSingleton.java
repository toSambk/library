package producerConsumer.concurrentSingleton.onDemandHolderIdiom;

public class OnDemandHolderIdiomSingleton {

    static class SingletonHolder {
        public static final OnDemandHolderIdiomSingleton HOLDER_INSTANCE = new OnDemandHolderIdiomSingleton();
    }

    public static OnDemandHolderIdiomSingleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

}
