package v3;

import java.util.Arrays;
import java.util.Map;

public class MyHashMap<K, V> {

    private static final int CAPACITY = 16;

    public static class MyEntry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        MyEntry next;
        int hash;

        public MyEntry(K key, V value, MyEntry next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        public MyEntry() {
        }


        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public MyEntry getNext() {
            return next;
        }

        public void setNext(MyEntry next) {
            this.next = next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;

            if (hash != myEntry.hash) return false;
            if (key != null ? !key.equals(myEntry.key) : myEntry.key != null) return false;
            if (value != null ? !value.equals(myEntry.value) : myEntry.value != null) return false;
            return next != null ? next.equals(myEntry.next) : myEntry.next == null;

        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + (next != null ? next.hashCode() : 0);
            result = 31 * result + hash;
            return result;
        }

        @Override
        public String toString() {
            return "MyEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    ", hash=" + hash +
                    '}';
        }
    }


    private MyEntry<K, V> elements[];

    public MyHashMap() {
        elements = new MyEntry[CAPACITY];
    }

    public void put(K key, V value) {
        if (key != null) {
            int newPositionHash = hash(key);
            int index = indexFor(newPositionHash);

            MyEntry<K, V> element = elements[index];

            if (element != null && key.equals(element.key) && (newPositionHash == element.hash)) {
                element.setValue(value);
            } else {
                createNewMyEntry(value, key, newPositionHash, element, index);
            }
        }
    }

    private void createNewMyEntry(V value, K key, int newPositionHash, MyEntry<K, V> element, int index) {
        MyEntry myEntry = new MyEntry(key, value, element, newPositionHash);
        elements[index] = myEntry;
    }

    private int indexFor(int newPositionHash) {
        return newPositionHash & (elements.length - 1);
    }

    private int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public void remove(K key) {
        try {
            int newPositionHash = hash(key);
            int index = indexFor(newPositionHash);

            MyEntry<K, V> previous = elements[index];
            MyEntry<K, V> current = elements[index];

            while (!(current.key.equals(key))) {
                previous = current;
                current = current.next;
            }
            if (current == elements[index]) {
                elements[index] = elements[index].next;
            } else {
                previous.next = current.next;
            }
        } catch (RuntimeException e) {
            System.out.println("Not appropriate action.\nPlease, try one more time\n");
        }
    }

    public void printByIndex(int index) {
        MyEntry temp = elements[index];
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}

