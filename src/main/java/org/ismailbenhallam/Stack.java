package org.ismailbenhallam;

import java.util.Objects;

public class Stack<T> {
    private static final String REMOVE_EXCEPTION_MESSAGE = "The stack is empty";

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T t) {
            this.data = t;
        }
    }

    private Node<T> top;

    public boolean isEmpty() {
        return top == null;
    }

    public T peek() {
        return top != null ? top.data : null;
    }

    public void push(T t) {
        Node<T> node = new Node<>(t);
        node.next = top;
        top = node;
    }

    public T pop() {
        T data = Objects.requireNonNull(top, REMOVE_EXCEPTION_MESSAGE).data;
        top = top.next;
        return data;
    }
}
