package org.ismailbenhallam;

import java.util.NoSuchElementException;

public class LinkedList<T> {
    private LinkedList<T> next;
    private T data;

    public LinkedList() {
    }

    public LinkedList(T data) {
        this.data = data;
    }

    public void add(T t) {
        if (data == null) {
            data = t;
        } else if (next == null) {
            next = new LinkedList<>(t);
        } else next.add(t);
    }

    public T get(int index) {
        if (data != null && index == 0) {
            return data;
        }
        if (next == null) {
            throw new IndexOutOfBoundsException();
        }
        return next.get(index - 1);
    }

    public int size() {
        if (data == null) {
            return 0;
        }
        if (next == null) {
            return 1;
        }
        return next.size() + 1;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    public void addFirst(T data) {
        var node = new LinkedList<>(this.data);
        node.next = next;
        next = node;
        this.data = data;
    }

    public void remove(T data) {
        if (data.equals(this.data)) {
            this.data = next != null ? next.data : null;
            next = next != null ? next.next : null;
        } else if (next == null) {
            throw new NoSuchElementException();
        } else {
            next.remove(data);
        }
    }

    /* Iterator */
    public interface Iterator<T> {

        boolean hasNext();

        T next();
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private LinkedList<T> pointer;

        public LinkedListIterator(LinkedList<T> list) {
            pointer = list;
        }

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public T next() {
            var data = pointer.data;
            pointer = pointer.next;
            return data;
        }
    }
}
