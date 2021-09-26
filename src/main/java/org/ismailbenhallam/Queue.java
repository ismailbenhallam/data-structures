package org.ismailbenhallam;

import java.util.Objects;

public class Queue<T> {
    private static final String REMOVE_EXCEPTION_MESSAGE = "The queue is empty";

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T t) {
            this.data = t;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public T peek() {
        return head != null ? head.data : null;
    }

    public void add(T t) {
        var node = new Node<>(t);
        if (tail != null) {
            tail.next = node;
            tail = tail.next;
        } else {
            tail = node;
        }
        if (head == null) {
            head = tail;
        }
    }

    public T remove() {
        T data = Objects.requireNonNull(head, REMOVE_EXCEPTION_MESSAGE).data;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        return data;
    }

    @Override
    public String toString() {
        var node = this.head;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (node != null) {
            builder.append(node.data).append(" ");
            node = node.next;
        }
        return builder.toString().trim() + "]";
    }

}
