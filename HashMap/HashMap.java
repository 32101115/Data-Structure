import java.util.List;
import java.util.Set;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 * 
 * @author Jitae Kim
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key or value is null");
        }
        if (((size + 1) / (double) table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int index = Math.abs(key.hashCode()) % table.length;
        if (table[index] == null) {
            table[index] = new MapEntry<K, V>(key, value);
            size++;
            return null;
        } else {
            MapEntry<K, V> temp = table[index];
            while (temp != null) {
                if (temp.getKey().equals(key)) {
                    V rv = temp.getValue();
                    temp.setValue(value);
                    return rv;
                } else {
                    temp = temp.getNext();
                }
            }
            MapEntry<K, V> newEntry = new MapEntry<K, V>(
                    key, value, table[index]);
            table[index] = newEntry;
            size++;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        V rv = null;
        int index = Math.abs(key.hashCode()) % table.length;
        if (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                rv = table[index].getValue();
                table[index].setValue(null);
                table[index].setKey(null);
                size--;
                if (table[index].getNext() == null) {
                    table[index] = null;
                } else {
                    table[index] = table[index].getNext();
                }
            } else if ((!(table[index].getKey().equals(key)))
                    && (table[index].getNext() != null)) {
                MapEntry<K, V> temp = table[index].getNext();
                MapEntry<K, V> prev = table[index];
                while (temp != null) {
                    if (temp.getKey().equals(key)) {
                        rv = temp.getValue();
                        temp.setValue(null);
                        temp.setKey(null);
                        if (temp.getNext() != null) {
                            prev.setNext(temp.getNext());
                        } else {
                            prev.setNext(null);
                        }
                        size--;
                        return rv;
                    } else {
                        temp = temp.getNext();
                        prev = prev.getNext();
                    }
                }
            } else {
                throw new NoSuchElementException("key is not founded");
            }
        } else {
            throw new NoSuchElementException("key is not founded");
        }
        return rv;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        V rv = null;
        int index = Math.abs(key.hashCode()) % table.length;
        if (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                rv = table[index].getValue();
            } else if ((!(table[index].getKey().equals(key)))
                    && (table[index].getNext() != null)) {
                MapEntry<K, V> temp = table[index].getNext();
                while (temp != null) {
                    if (temp.getKey().equals(key)) {
                        rv = temp.getValue();
                        return rv;
                    } else {
                        temp = temp.getNext();
                    }
                }
            } else {
                throw new NoSuchElementException("key is not founded");
            }
        } else {
            throw new NoSuchElementException("key is not founded");
        }
        return rv;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        if (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return true;
            } else if ((!(table[index].getKey().equals(key)))
                    && (table[index].getNext() != null)) {
                MapEntry<K, V> temp = table[index];
                while (temp.getNext() != null) {
                    if (temp.getNext().getKey().equals(key)) {
                        return true;
                    } else {
                        temp = temp.getNext();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> rv = new java.util.HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                rv.add(table[i].getKey());
                MapEntry<K, V> temp = table[i].getNext();
                while (temp != null) {
                    rv.add(temp.getKey());
                    temp = temp.getNext();
                }
            }
        }
        return rv;
    }

    @Override
    public List<V> values() {
        List<V> rv = new java.util.ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                rv.add(table[i].getValue());
                MapEntry<K, V> temp = table[i].getNext();
                while (temp != null) {
                    rv.add(temp.getValue());
                    temp = temp.getNext();
                }
            }
        }
        return rv;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("length is less than 0 "
                + "or less than size");
        }
        MapEntry<K, V>[] temp = (MapEntry<K, V>[]) new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int index = Math.abs(table[i].getKey().hashCode())
                        % temp.length;
                if (temp[index] == null) {
                    temp[index] = new MapEntry<K, V>(
                            table[i].getKey(), table[i].getValue());
                } else {
                    MapEntry<K, V> newEntry = new MapEntry<K, V>(
                            table[i].getKey(), table[i].getValue(), table[i]);
                    table[i] = newEntry;
                }
                if (table[i].getNext() != null) {
                    MapEntry<K, V> tempNext = table[i].getNext();
                    while (tempNext != null) {
                        int newIndex = Math.abs(tempNext.getKey().hashCode())
                                % temp.length;
                        if (temp[newIndex] == null) {
                            temp[newIndex] = new MapEntry<K, V>(
                                    tempNext.getKey(), tempNext.getValue());
                        } else {
                            MapEntry<K, V> newEntry = new MapEntry<K, V>(
                                    tempNext.getKey(), tempNext.getValue(),
                                    temp[newIndex]);
                            temp[newIndex] = newEntry;
                        }
                        tempNext = tempNext.getNext();
                    }
                }
            }
        }
        table = temp;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
