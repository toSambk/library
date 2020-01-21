package dynamicProxy;

public class OriginalReader implements Reader {
    @Override
    public void close() {
        System.out.println("Call for original reader...");
    }
}
