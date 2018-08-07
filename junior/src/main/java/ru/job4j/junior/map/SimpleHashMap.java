package ru.job4j.junior.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap<K, V>.Node<K, V>> {

    private static final int MAX_TABLE_SIZE = 1 << 30;
    private int tableSize = 1 << 8; // 256
    private Node<K, V>[] hashtable = (Node<K, V>[]) new Node[tableSize];
    private int size = 0;
    private int modnum = 0;

    public boolean insert(K key, V value) {
        boolean result = false;
        Node<K, V> node = (Node<K, V>) new Node(key, value, key.hashCode());
        Node<K, V> oldNode = getNode(key);
        if (oldNode == null) {
            hashtable[node.getHash() & (hashtable.length - 1)] = node;
            result = true;
            size++;
            modnum++;
        } else if (oldNode.getHash() == node.getHash()) {
            oldNode.setValue(value);
            result = true;
        } else {
            if (resize()) {
                result = insert(key, value);
            }
        }
        return result;
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return (node != null) ? node.getValue() : null;
    }

    private Node<K, V> getNode(K key) {
        Node<K, V> node = null;
        if (key != null) {
            node = hashtable[key.hashCode() & (hashtable.length - 1)];
        }
        return node;
    }

    public boolean delete(K key) {
        boolean result = false;
        if (key != null) {
            int hash = key.hashCode();
            if (hashtable[hash & (hashtable.length - 1)] != null) {
                hashtable[hash & (hashtable.length - 1)] = null;
                size--;
                modnum++;
                result = true;
            }
        }
        return result;
    }

    private boolean resize() {
        boolean result = false;
        int oldLength = hashtable.length;
        Node<K, V>[] oldtable = hashtable;
        if (oldLength < Integer.MAX_VALUE) {
            if (oldLength < MAX_TABLE_SIZE) {
                tableSize = tableSize << 1;
            } else {
                tableSize = Integer.MAX_VALUE;
            }
            hashtable = (Node<K, V>[]) new Node[tableSize];
            redistribute(oldtable);
            result = true;
        }
        return result;
    }

    private void redistribute(final Node<K, V>[] oldtable) {
        for (Node<K, V> node : oldtable) {
            if (node != null) {
                hashtable[node.getHash() & (hashtable.length - 1)] = node;
            }
        }
    }

    @Override
    public String toString() {
        String strmap = "[\n";
        for (Node<K, V> el : this) {
            strmap += el.getHash() + "[" + Integer.toBinaryString(el.getHash()) + "]" + ";" + el.getKey() + ";" + el.getValue() + "\n";
        }
        strmap += "]";
        return "SimpleHashMap[inner=" + hashtable.length + "]" + "{"
                + strmap
                + '}';
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<SimpleHashMap<K, V>.Node<K, V>> iterator() {
        return new SimpleHashMapIterator();
    }

    private class SimpleHashMapIterator implements Iterator<SimpleHashMap<K, V>.Node<K, V>> {

        private final Node<K, V>[] table;
        private int currentIndex = 0;
        private final int modn;

        public SimpleHashMapIterator() {
            table = hashtable;
            this.modn = modnum;
        }

        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int i = currentIndex; i < table.length; i++) {
                if (table[i] != null) {
                    currentIndex = i;
                    result = true;
                    break;
                }
            }
            return result;
        }

        @Override
        public Node<K, V> next() {
            if (modn != modnum) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return table[currentIndex++];
        }
    }

    public class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;

        Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public int getHash() {
            return hash;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
