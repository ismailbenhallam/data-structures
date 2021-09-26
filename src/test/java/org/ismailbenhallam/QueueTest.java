package org.ismailbenhallam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void isEmpty() {
        assertTrue(queue.isEmpty());

        queue.add("Value");
        assertFalse(queue.isEmpty());
    }

    @Test
    void peek() {
        var firstValue = "First Value";
        queue.add(firstValue);
        queue.add("Second Value");
        queue.add("Third Value");

        assertEquals(firstValue, queue.peek());
        assertEquals(firstValue, queue.peek());
    }

    @Test
    void add() {
        assertNull(queue.peek());

        queue.add("First Value");
        assertEquals("First Value", queue.peek());

        queue.add("Second Value");
        assertEquals("First Value", queue.peek());

        queue.add("Third Value");
        assertEquals("First Value", queue.peek());
    }

    @Test
    void remove() {
        queue.add("First Value");
        queue.add("Second Value");
        queue.add("Third Value");

        assertEquals("First Value", queue.remove());
        assertEquals("Second Value", queue.remove());
        assertEquals("Third Value", queue.remove());
        assertThrowsExactly(NullPointerException.class, queue::remove, "This queue is empty");
    }

}