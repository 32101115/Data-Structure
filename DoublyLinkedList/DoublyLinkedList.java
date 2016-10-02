
/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Jitae Kim
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("index can't be negative and "
                    + "index can't be smaller than size");
        }
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            int i = 0;
            LinkedListNode<T> curr = head;
            while (i != index) {
                i++;
                curr = curr.getNext();
            }
            curr = new LinkedListNode<T>(data,
                    curr.getPrevious(), curr);
            curr.getPrevious().setNext(curr);
            curr.getNext().setPrevious(curr);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        if (size == 0) {
            head = new LinkedListNode<T>(data, null, null);
            tail = head;
        } else {
            LinkedListNode<T> temp = head;
            head = new LinkedListNode<T>(data, null, head);
            temp.setPrevious(head);
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        if (size == 0) {
            tail = new LinkedListNode<T>(data, null, null);
            head = tail;
        } else {
            LinkedListNode<T> temp = tail;
            tail = new LinkedListNode<T>(data, tail, null);
            temp.setNext(tail);
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        T rv = null;
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("index can't be negative and "
                    + "index can't be smaller than size");
        }
        if (index == 0) {
            rv = removeFromFront();
        } else if (index == (size - 1)) {
            rv = removeFromBack();
        } else {
            int i = 0;
            LinkedListNode<T> temp = head;
            while (i != index) {
                temp = temp.getNext();
                i++;
            }
            rv = temp.getData();
            temp.getPrevious().setNext(temp.getNext());
            temp.getNext().setPrevious(temp.getPrevious());
            temp.setNext(null);
            temp.setPrevious(null);
            size--;
        }
        return rv;
    }

    @Override
    public T removeFromFront() {
        T rv;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            rv = head.getData();
            head = null;
            tail = null;
        } else {
            rv = head.getData();
            head = head.getNext();
            head.getPrevious().setNext(null);
            head.setPrevious(null);
        }
        size--;
        return rv;
    }

    @Override
    public T removeFromBack() {
        T rv;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            rv = head.getData();
            head = null;
            tail = null;
        } else {
            rv = tail.getData();
            tail = tail.getPrevious();
            tail.getNext().setPrevious(null);
            tail.setNext(null);
        }
        size--;
        return rv;
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        LinkedListNode<T> temp = head;
        int rv = 0;
        while (temp != null) {
            if (temp.getData().equals(data)) {
                if (head.equals(temp)) {
                    removeFromFront();
                    temp = head;
                } else if (tail.equals(temp)) {
                    removeFromBack();
                    temp = tail;
                } else {
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                    temp = temp.getNext();
                    size--;
                }
                rv++;
            } else {
                temp = temp.getNext();
            }
        }
        return (rv != 0);
    }

    @Override
    public T get(int index) {
        T rv = null;
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("index can't be negative and "
                    + "index can't be smaller than size");
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            int i = 0;
            LinkedListNode<T> temp = head;
            while (i != index) {
                temp = temp.getNext();
                i++;
            }
            rv = temp.getData();
        }
        return rv;
    }

    @Override
    public Object[] toArray() {
        Object[] rv = new Object[size];
        LinkedListNode<T> temp = head;
        int i = 0;
        while (temp != null) {
            rv[i] = temp.getData();
            temp = temp.getNext();
            i++;
        }
        return rv;
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
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
