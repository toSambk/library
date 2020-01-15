package stepTwoLinkedListTest.listTest;

import org.junit.Before;
import org.junit.Test;
import stepTwoLinkedListTest.list.CustomLinkedList;
import stepTwoLinkedListTest.listTest.testNode.Car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> emptyList;

    private CustomLinkedList<Integer> list;

    private CustomLinkedList<Car> carList;

    @Before
    public void setup() {
        emptyList = new CustomLinkedList<>();
        list = new CustomLinkedList<>();
        list.addElement(1);
        list.addElement(10);
        list.addElement(50);
        carList = new CustomLinkedList<>();
        carList.addElement(new Car("audi", "150"));
        carList.addElement(new Car("bmv", "300"));
    }

    @Test
    public void getSizeTest() {

        assertEquals(3, list.size());
        assertEquals(0, emptyList.size());

    }

    @Test
    public void addAndGetElementTest() {

        assertEquals(0, emptyList.size());

        emptyList.addElement(150);

        assertEquals(1, emptyList.size());
        assertEquals(Integer.valueOf(150), emptyList.get(0));

        emptyList.addElement(300);

        assertEquals(2, emptyList.size());
        assertEquals(Integer.valueOf(300), emptyList.get(1));

    }

    @Test
    public void getElementError() {

        try {
            list.get(100000);
            fail();
        } catch (UnsupportedOperationException ignored) {
        }

    }


    @Test
    public void setElementSuccessTest() {

        assertEquals(3, list.size());
        list.set(1, 1_000_000);
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(1_000_000), list.get(1));

    }

    @Test
    public void setElementFailureTest() {
        try {
            list.set(1000, 50);
            fail();
        } catch (UnsupportedOperationException ignored) {
        }

        try {
            list.set(-1000, 50);
            fail();
        } catch (UnsupportedOperationException ignored) {
        }

    }

    @Test
    public void removeFailure() {

        try {
            emptyList.remove(0);
            fail();
        } catch (UnsupportedOperationException ignored) {
        }

        try {
            list.remove(10);
            fail();
        } catch (UnsupportedOperationException ignored) {
        }

    }

    @Test
    public void removeSuccessTest() {

        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));

        list.remove(0);
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));

        list.remove(1);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));

    }

    @Test
    public void reverseFailureTest() {
        try {
            emptyList.reverse();
            fail();
        } catch (UnsupportedOperationException ignored) {
        }

    }

    @Test
    public void reverseSuccessTest() {

        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(10), list.get(1));
        assertEquals(Integer.valueOf(50), list.get(2));

        list.reverse();

        assertEquals(Integer.valueOf(50), list.get(0));
        assertEquals(Integer.valueOf(10), list.get(1));
        assertEquals(Integer.valueOf(1), list.get(2));

    }

    @Test
    public void clearTest() {

        assertEquals(3, list.size());

        list.clear();

        assertEquals(0, list.size());

    }


    @Test
    public void addElementFailureTest() {
        try {
            carList.addElement(null);
        } catch (UnsupportedOperationException ignored) {
        }

    }

    @Test
    public void setElementNullFailureTest() {
        try {
            carList.set(1, null);
        } catch (UnsupportedOperationException ignored) {
        }
    }


}
