package org.ismailbenhallam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashtableTest {
    private Hashtable<String, Integer> hashtable;

    @BeforeEach
    void setUp() {
        hashtable = new Hashtable<>();
    }

    private void putSomeValues() {
        hashtable.put("six", 6);
        hashtable.put("one", 11);
        hashtable.put("one", 1);
        hashtable.put("three", 3);
        hashtable.put("four", 4);
        hashtable.put("two", 2);
    }

    @Test
    void put() {
        putSomeValues();
    }

    @Test
    void putIfAbsent() {
        putSomeValues();
        hashtable.putIfAbsent("six", 684486);
        hashtable.putIfAbsent("zero", 0);
        hashtable.putIfAbsent("one", 116844468);
        hashtable.putIfAbsent("one", 1684884664);
        hashtable.put("eleven", 11);
        assertEquals(0, hashtable.get("zero"));
        assertEquals(6, hashtable.get("six"));
        assertEquals(1, hashtable.get("one"));
        assertEquals(11, hashtable.get("eleven"));
    }

    @Test
    void get() {
        assertNull(hashtable.get("one"));
        putSomeValues();
        assertEquals(1, hashtable.get("one"));
        assertEquals(6, hashtable.get("six"));
        assertEquals(2, hashtable.get("two"));
        assertNull(hashtable.get("five"));
    }

    @Test
    void size() {
        assertEquals(0, hashtable.size());
        putSomeValues();
        assertEquals(5, hashtable.size());
        putSomeValues();
        assertEquals(5, hashtable.size());
    }

    @Test
    void remove() {
        assertThrows(NoSuchElementException.class, () -> hashtable.remove("one"));
        putSomeValues();
        assertEquals(1, hashtable.remove("one"));
        assertThrows(NoSuchElementException.class, () -> hashtable.remove("one"));
    }
}