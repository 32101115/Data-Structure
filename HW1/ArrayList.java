/**
 * Your implementation of an ArrayList.
 *
 * @author Jitae Kim
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index can't be negative "
                    + "or bigger than size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data"
                    + " into data structure.");
        }
        if (size == backingArray.length) {
            reGrow();
        }
        if (backingArray[index] == null) {
            backingArray[index] = data;
            size++;
        } else if (backingArray[index] != null) {
            int temp = size;
            while (temp != index) {
                backingArray[temp] = backingArray[temp - 1];
                temp--;
            }
            backingArray[index] = data;
            size++;
        }
    }
    /**
     * helper method for doubling size array
     *
     */
    private void reGrow() {
        T[] tempArray = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            tempArray[i] = backingArray[i];
        }
        backingArray = tempArray;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data"
                    + " into data structure.");
        }
        if (size == backingArray.length) {
            reGrow();
        }
        int temp = size;
        while (temp != 0) {
            backingArray[temp] = backingArray[temp - 1];
            temp--;
        }
        backingArray[0] = data;
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data"
                    + " into data structure.");
        }
        if (size == backingArray.length) {
            reGrow();
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index can't be negative "
                    + "or bigger than size");
        }
        T rv;
        int temp = index;
        if (index == (size - 1)) {
            rv = backingArray[index];
            backingArray[size - 1] = null;
        } else {
            rv = backingArray[index];
            while (temp != (size - 1)) {
                backingArray[temp] = backingArray[temp + 1];
                temp++;
            }
            backingArray[size - 1] = null;
        }
        size--;
        return rv;
    }

    @Override
    public T removeFromFront() {
        T rv;
        int temp = 0;
        if (size == 0) {
            return null;
        }
        rv = backingArray[0];
        while (temp != (size - 1)) {
            backingArray[temp] = backingArray[temp + 1];
            temp++;
        }
        backingArray[size - 1] = null;
        size--;
        return rv;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T rv = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return rv;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index can't be negative "
                    + "or bigger than size");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
