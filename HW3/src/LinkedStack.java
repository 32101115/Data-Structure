import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack.
 *
 * @author Jitae Kim
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T pop() {
        T rv;
        if (size == 0) {
            throw new NoSuchElementException("stack is empty"
                    + " can't be poped");
        } else {
            rv = head.getData();
            head = head.getNext();
            size--;
            if (size == 0) {
                head = null;
            }
        }
        return rv;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        LinkedNode<T> temp = new LinkedNode<T>(data);
        if (head == null) {
            head = temp;
        } else {
            temp.setNext(head);
            head = temp;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
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
}
