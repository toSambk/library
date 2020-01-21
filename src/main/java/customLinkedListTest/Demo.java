package customLinkedListTest;

public class Demo {

    public static void main(String[] args) {
//        CustomLinkedList<Integer> list = new CustomLinkedList<>();
//
//        list.addElement(1);
//
//        list.addElement(10);
//
//        list.addElement(158);
//
//        list.get(0);
//
//        list.printAll();
//
//        list.reverse();
//
//        list.printAll();


        A a = new A();
        Single s = new Single();
        Double d = new Double();

        d = (Double)a;
        d = (Double)s;
        s = (Single)a;
        s = (Single)d;


    }
}

class A {

}

class Single extends A {

}

class Double extends Single {

}



