import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 *
 * @author Jitae Kim
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null"
                    + " so can't be added");
        }
        if ((size + 1) == (backingArray.length)) {
            T[] tempArray = (T[]) new Comparable[backingArray.length * 3 / 2];
            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        size++;
        int index = size;
        backingArray[index] = item;
        int parentIndex = (index) / 2;
        while ((parentIndex >= 1)
                && (backingArray[parentIndex]
                .compareTo(backingArray[index]) > 0)) {
            T temp = backingArray[parentIndex];
            backingArray[parentIndex] = backingArray[index];
            backingArray[index] = temp;
            index = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty"
                    + " so can't be removed");
        }
        int index = size;
        T rv = backingArray[1];
        backingArray[1] = backingArray[index];
        backingArray[index] = null;
        size--;
        if (backingArray[2] != null && backingArray[3] != null) {
            removeHelper(1, 2, 3);
        } else if (backingArray[2] != null && backingArray[3] == null) {
            removeOne(1, 2);
        }
        return rv;
    }


    /**
     * Helper method for remove from heap
     * @param parent parent
     * @param left child
     * @param right child
     */
    private void removeHelper(int parent, int left, int right) {
        if (backingArray[left] != null && backingArray[right] != null) {
            if (backingArray[left].compareTo(backingArray[right]) > 0) {
                if (backingArray[right].compareTo(backingArray[parent]) < 0) {
                    T temp = backingArray[parent];
                    backingArray[parent] = backingArray[right];
                    backingArray[right] = temp;
                    if (size >= (right * 2 + 1)) {
                        removeHelper(right, right * 2, right * 2 + 1);
                    } else if (size == (right * 2)) {
                        removeOne(right, right * 2);
                    }
                }
            } else {
                if (backingArray[left].compareTo(backingArray[parent]) < 0) {
                    T temp = backingArray[parent];
                    backingArray[parent] = backingArray[left];
                    backingArray[left] = temp;
                    if (size >= (left * 2 + 1)) {
                        removeHelper(left, left * 2, left * 2 + 1);
                    } else if (size == (left * 2)) {
                        removeOne(left, left * 2);
                    }
                }
            }
        } else if (backingArray[left] != null && backingArray[right] == null) {
            removeOne(parent, left);
        }
    }
    /**
     * Helper method to remove one child from heap
     * @param parent parent
     * @param left child
     */
    private void removeOne(int parent, int left) {
        if (backingArray[left].compareTo(backingArray[parent]) < 0) {
            T temp = backingArray[parent];
            backingArray[parent] = backingArray[left];
            backingArray[left] = temp;
        }
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
