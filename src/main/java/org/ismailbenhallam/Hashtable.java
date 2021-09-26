package org.ismailbenhallam;

import java.util.NoSuchElementException;

public class Hashtable<K, V> {
    private final int BUCKETS = 32;
    private final LinkedList<Node<K, V>>[] array = new LinkedList[BUCKETS];
    private int size = 0;

    public void put(K key, V value) {
        var newNode = new Node<>(key, value);
        var bucketIndex = getIndexFromHashCode(key);
        var bucket = array[bucketIndex];

        if (putIfNullBucket(bucket, newNode)) {
            return;
        }

        var iterator = bucket.iterator();
        while (iterator.hasNext()) {
            var currentNode = iterator.next();
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
                return;
            }
        }

        bucket.add(newNode);
        size++;
    }

    public void putIfAbsent(K key, V value) {
        var newNode = new Node<>(key, value);
        var bucketIndex = getIndexFromHashCode(newNode.key);
        var bucket = array[bucketIndex];

        if (putIfNullBucket(bucket, newNode)) {
            return;
        }

        var iterator = bucket.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().key.equals(key)) {
                return;
            }
        }

        bucket.add(newNode);
        size++;
    }

    public V get(K key) {
        var list = array[getIndexFromHashCode(key)];
        if (list == null) {
            return null;
        }
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (next.key.equals(key)) {
                return next.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        var list = array[getIndexFromHashCode(key)];
        if (list == null) {
            throw new NoSuchElementException();
        }
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (next.key.equals(key)) {
                list.remove(next);
                size--;
                return next.value;
            }
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return size;
    }

    /* Helpers methods */
    private boolean putIfNullBucket(LinkedList<Node<K, V>> bucket, Node<K, V> node) {
        if (bucket == null) {
            array[getIndexFromHashCode(node.key)] = bucket = new LinkedList<>();
            bucket.add(node);
            size++;
            return true;
        }
        return false;
    }

    private int getIndexFromHashCode(K key) {
        return Math.abs(key.hashCode()) % BUCKETS;
    }

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
