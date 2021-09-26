package org.ismailbenhallam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());

        stack.push("Value");
        assertFalse(stack.isEmpty());
    }

    @Test
    void peek() {
        stack.push("First Value");
        stack.push("Second Value");
        stack.push("Third Value");

        assertEquals("Third Value", stack.peek());
        assertEquals("Third Value", stack.peek());
    }

    @Test
    void add() {
        assertNull(stack.peek());

        stack.push("First Value");
        assertEquals("First Value", stack.peek());

        stack.push("Second Value");
        assertEquals("Second Value", stack.peek());

        stack.push("Third Value");
        assertEquals("Third Value", stack.peek());
    }

    @Test
    void remove() {
        stack.push("First Value");
        stack.push("Second Value");
        stack.push("Third Value");

        assertEquals("Third Value", stack.pop());
        assertEquals("Second Value", stack.pop());
        assertEquals("First Value", stack.pop());
        assertThrowsExactly(NullPointerException.class, stack::pop, "The stack is empty");
    }

}