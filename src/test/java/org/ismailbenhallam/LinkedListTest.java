package org.ismailbenhallam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedListTest {
    private LinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void add() {
        list.add("Value 1");
        list.add("Value 2");
        list.add("Value 3");
        list.add("Value 4");

        assertEquals("Value 1", list.get(0));
        assertEquals("Value 2", list.get(1));
        assertEquals("Value 3", list.get(2));
        assertEquals("Value 4", list.get(3));
    }

    @Test
    void addFirst() {
        list.add("Value 1");
        list.addFirst("Value 2");
        list.addFirst("Value 3");
        list.addFirst("Value 4");

        assertEquals("Value 4", list.get(0));
        assertEquals("Value 3", list.get(1));
        assertEquals("Value 2", list.get(2));
        assertEquals("Value 1", list.get(3));
    }

    @Test
    void get() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(542));

        list.add("Value 1");
        assertEquals(1, list.size());

        list.add("Value 2");
        assertEquals(2, list.size());
    }

    @Test
    void remove() {
        list.add("Value 1");
        list.add("Value 2");
        list.add("Value 3");
        list.add("Value 4");

        list.remove("Value 3");
        assertEquals(3, list.size());

        assertEquals("Value 1", list.get(0));
        assertEquals("Value 2", list.get(1));
        assertEquals("Value 4", list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void size() {
        assertEquals(0, list.size());

        list.add("Node 1");
        assertEquals(1, list.size());
    }

    @Test
    void iterator() {
        list.add("Value 1");
        list.add("Value 2");
        list.add("Value 3");
        list.add("Value 4");

        var iterator = list.iterator();
        for (int i = 1; iterator.hasNext(); i++) {
            assertEquals(String.format("Value %d", i), iterator.next());
        }

        list.add("Value 5");
        iterator = list.iterator();
        for (int i = 1; iterator.hasNext(); i++) {
            assertEquals(String.format("Value %d", i), iterator.next());
        }
    }
}