import java.util.NoSuchElementException;
/**
 * Your implementation of a linked queue.
 *
 * @author Jitae Kim
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        T rv;
        if (size == 0) {
            throw new NoSuchElementException("queue is empty"
                    + " can't dequeue");
        } else {
            rv = head.getData();
            head = head.getNext();
            size--;
            if (size == 0) {
                head = null;
                tail = null;
            }
        }
        return rv;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        LinkedNode<T> temp = new LinkedNode<T>(data);
        if (head == null) {
            head = temp;
            tail = head;
        } else {
            tail.setNext(temp);
            tail = temp;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
