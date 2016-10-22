/**
 * A class representing a MapEntry for a HashMap.
 * DO NOT EDIT THIS FILE!
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public class MapEntry<K, V> {
    private K key;
    private V value;
    private MapEntry<K, V> next;

    /**
     * Create a MapEntry object with the given key and value.
     *
     * @param key
     * @param value
     */
    public MapEntry(K key, V value) {
        this(key, value, null);
    }

    /**
     * Create a MapEntry object with the given key, value and next reference.
     *
     * @param key key for this entry
     * @param value value for this entry
     * @param next next entry in the external chain
     */
    public MapEntry(K key, V value, MapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Gets the key held by this entry.
     *
     * @return key in this entry.
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key held by this entry.
     *
     * @param key key to store in this entry.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Gets the value held by this entry.
     *
     * @return value in this entry
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value held by this entry.
     *
     * @param value value to store in this entry
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Gets the next entry in the external chain.
     *
     * @return the next entry
     */
    public MapEntry<K, V> getNext() {
        return next;
    }

    /**
     * Sets the next reference in this external chain.
     *
     * @param next the new next entry
     */
    public void setNext(MapEntry<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        // DO NOT USE THIS METHOD IN YOUR CODE!  This is for testing ONLY!
        if (!(o instanceof MapEntry)) {
            return false;
        } else {
            MapEntry<K, V> that = (MapEntry<K, V>) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s", key.toString(), value.toString());
    }
}
