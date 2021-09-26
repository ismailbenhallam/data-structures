package org.ismailbenhallam;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private static final int CAPACITY_INCREMENTATION = 16;
    private int capacity = CAPACITY_INCREMENTATION;
    private Object[] nodes = new Object[capacity];
    private int size = 0;
    private final Comparator<T> comparator;

    public Heap(HeapType strategy) {
        comparator = strategy.equals(HeapType.MIN) ? new MinComparator<>() : new MaxComparator<>();
    }

    public Heap() {
        this(HeapType.MIN);
    }

    public Heap(Collection<T> collection, HeapType strategy) {
        this(strategy);
        collection.forEach(this::add);
    }

    public Heap(Collection<T> collection) {
        this(collection, HeapType.MIN);
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalStateException();
        }
        size++;
        ensureCapacity();
        nodes[size - 1] = data;
        heapifyUp();
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) nodes[0];
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        var data = nodes[0];
        nodes[0] = nodes[size - 1];
        nodes[size - 1] = null;
        size--;
        heapifyDown();
        return (T) data;
    }

    public int getSize() {
        return size;
    }


    /* Utilities Methods */
    private void ensureCapacity() {
        if (size > capacity) {
            capacity += CAPACITY_INCREMENTATION;
            nodes = Arrays.copyOf(nodes, capacity);
        }
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private boolean hasParent(int index) {
        return parentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }

    @SuppressWarnings("unchecked")
    private T parent(int index) {
        return (T) nodes[parentIndex(index)];
    }

    @SuppressWarnings("unchecked")
    private T leftChild(int index) {
        return (T) nodes[leftChildIndex(index)];
    }

    @SuppressWarnings("unchecked")
    private T rightChild(int index) {
        return (T) nodes[rightChildIndex(index)];
    }

    @SuppressWarnings("unchecked")
    private void swap(int i1, int i2) {
        T tmp = (T) nodes[i1];
        nodes[i1] = nodes[i2];
        nodes[i2] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void heapifyUp() {
        var index = size - 1;
        while (hasParent(index) && comparator.compare(((T) nodes[index]), parent(index)) < 0) {
            var parentIndex = parentIndex(index);
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    @SuppressWarnings("unchecked")
    private void heapifyDown() {
        var index = 0;
        while (hasLeftChild(index)) {
            int childIndexToSwap = leftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(rightChild(index), leftChild(index)) < 0) {
                childIndexToSwap = rightChildIndex(index);
            }
            if (comparator.compare(((T) nodes[childIndexToSwap]), ((T) nodes[index])) < 0) {
                swap(index, childIndexToSwap);
                index = childIndexToSwap;
            } else {
                break;
            }
        }
    }

    /* Strategy */
    public enum HeapType {
        MIN, MAX
    }

    /* Comparator for MIN & MAX strategies */
    private static class MinComparator<T extends Comparable<T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    private static class MaxComparator<T extends Comparable<T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o2.compareTo(o1);
        }
    }

}
