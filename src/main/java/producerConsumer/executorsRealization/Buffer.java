package producerConsumer.executorsRealization;

import java.util.ArrayList;
import java.util.List;

public class Buffer <T> {

    private List<T> buffer = new ArrayList<>();

    private int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(T element) {
        buffer.add(element);
    }

    public T getFirstElement() {
        T result = buffer.get(0);
        buffer.remove(0);
        return result;
    }

    public int getSize() {
        return buffer.size();
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    public boolean isFull() {
        return buffer.size() == maxSize;
    }
}
