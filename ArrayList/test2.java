/**
 * Created by jitaekim on 8/28/16.
 */
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * some super-hot junit tests
 *
 * referenced tflynn34's junit tests for exception catching
 *
 * @version 0.1
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test2 {


    private ArrayList<Integer> testList;
    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        testList = new ArrayList<Integer>();
    }

    private int manualSize(ArrayList<Integer> l) {
        int size = 0;
        for (Object i :  l.getBackingArray()) {
            if (i != null) {
                size++;
            }
        }
        return size;
    }

    // composite tests
    private int arraySum(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;

    }

    private void loadArrayList(Integer[] arr) {
        testList.clear();
        for (Integer i : arr) {
            testList.addToBack(i);
        }
    }

    //over bounds
    @Test(expected = IndexOutOfBoundsException.class)
    public void test0_get() {
        testList.addToBack(0);
        assertEquals((Integer)testList.get(0), (Integer) 0);
        testList.get(1);
    }
    //under bounds
    @Test(expected = IndexOutOfBoundsException.class)
    public void test1_get() {
        testList.get(-1);
    }

    @Test(timeout = TIMEOUT)
    public void test2_addToBack() {
        Integer[] model = {92, 24, 5};
        int counter = 0;
        for (Integer i : model) {
            testList.addToBack(i);
            assertEquals(model[counter], testList.getBackingArray()[counter]);
            counter++;
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void test3_addToBack_exception() {
        testList.addToBack(null);
    }




    @Test(timeout = TIMEOUT)
    public void test4_clear() {
        testList.clear();
        assertEquals(ArrayListInterface.INITIAL_CAPACITY
                , testList.getBackingArray().length);
        for (int i = 0; i < manualSize(testList); i++) {
            assertNull(testList.getBackingArray()[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void test5_compositeAllOfAbove() {
        Integer[] model = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        loadArrayList(model);
        assertEquals(arraySum(testList), 55);
        assertEquals(testList.get(4), new Integer(4));
        testList.clear();
        assertEquals(testList.getBackingArray().length
                , ArrayListInterface.INITIAL_CAPACITY);
    }
    @Test(timeout = TIMEOUT)
    public void test6_addAtIndex() {
        testList.addAtIndex(0, 9000);
        testList.addAtIndex(0, 9001);
        assertEquals(true, (Integer) testList.getBackingArray()[0]
                > (Integer) testList.getBackingArray()[1]);

    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void test7_addAtIndex_exception() {
        testList = new ArrayList<Integer>();
        testList.addAtIndex(0, 0);
        assertEquals(0, testList.getBackingArray()[0]);

        testList.addAtIndex(-1, 0);
    }
    @Test(timeout = TIMEOUT)
    public void test8_addAtFront() {
        testList = new ArrayList<Integer>();
        Integer[] model = {0, 1, 2, 3, 4, 5};

        for (Integer i : model) {
            testList.addToFront(i);
            assertEquals(i, testList.getBackingArray()[0]);
        }
        assertEquals(6, manualSize(testList));
    }

    @Test(timeout = TIMEOUT)
    public void test9_size() {
        testList = new ArrayList<Integer>();
        Integer[] model = {0, 1, 2, 3, 4, 5, 6, 7};
        loadArrayList(model);
        assertEquals(testList.size(), manualSize(testList));
    }

}