package customLinkedListTest.list;

import customLinkedListTest.node.Node;

public class CustomLinkedList<T> {

    private Node<T> first;

    private Node<T> last;

    private int size;

    public CustomLinkedList() {
        size = 0;
    }

    public void addElement(T element) {
        if (first == null) {
            first = new Node<>(element);
            last = first;
            size = 1;
            return;
        }
        Node<T> node = new Node<>(element);
        node.setPrevious(last);
        last.setNext(node);
        last = node;
        size++;
    }


    public T get(int index) {
        if (index >= size || index < 0) throw new UnsupportedOperationException();
        Node<T> node = first;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                return node.getItem();
            }
            node = node.getNext();
        }
        return null;
    }

    public void set(int index, T element) {
        if (index >= size || index < 0 || element == null) throw new UnsupportedOperationException();
        Node<T> node = first;
        Node<T> nodeToInsert = new Node<>(element);
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                Node<T> previous = node.getPrevious();
                previous.setNext(nodeToInsert);
                nodeToInsert.setPrevious(previous);
                nodeToInsert.setNext(node);
                node.setPrevious(nodeToInsert);
                size++;
                return;
            }
            node = node.getNext();
        }
    }


    public void remove(int index) {
        if (index >= size || size <= 0) throw new UnsupportedOperationException();
        Node<T> node = first;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                Node<T> previous = node.getPrevious();
                Node<T> next = node.getNext();
                if (previous != null) {
                    previous.setNext(next);
                } else {
                    first = next;
                    first.setPrevious(null);
                }

                if (next != null) {
                    next.setPrevious(previous);
                }

                size--;
                return;
            }
            node = node.getNext();
        }
    }

    public void reverse() {

        if (size == 0) throw new UnsupportedOperationException();

        if (size == 1) return;

        Node<T> current = first;

        while (current != null) {

            Node<T> temp = current.getNext();

            current.setNext(current.getPrevious());
            current.setPrevious(temp);

            current = temp;

        }

        current = last;
        last = first;
        first = current;

    }


    public void clear() {
        first = last = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public void printAll() {

        if (first == null) {
            System.out.println("Список пуст");
            return;
        }

        System.out.println("--- Список элементов (количество = " + size + "):");
        Node<T> node = first;
        System.out.println(node.getItem());
        while (node.getNext() != null) {
            node = node.getNext();
            System.out.println(node.getItem());
        }
        System.out.println("-----");
    }


}
