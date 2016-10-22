import java.util.NoSuchElementException;

/**
 * Your implementation of a min priority queue.
 * 
 * @author Jitae Kim
 * @version 1.0
 */
public class MinPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MinPriorityQueue() {
        backingHeap = new MinHeap<T>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null, ®can't enqueue");
        }
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {
        if (backingHeap.size() == 0) {
            throw new NoSuchElementException("priority queue is empty");
        }
        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        return (backingHeap.isEmpty());
    }

    @Override
    public int size() {
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT CHANGE THIS METHOD!
        return backingHeap;
    }

}
