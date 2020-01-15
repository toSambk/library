package stepTwoLinkedListTest;

import stepTwoLinkedListTest.list.CustomLinkedList;

public class Demo {

    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.addElement(1);

        list.addElement(10);

        list.addElement(158);

        list.get(0);

        list.printAll();

        list.reverse();

        list.printAll();
    }

}
