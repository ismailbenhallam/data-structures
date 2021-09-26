package org.ismailbenhallam;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    private Heap<Integer> heap;
    private final List<Integer> dummyData = List.of(7, 3, 5, 2, 6);

    private void insertData() {
        dummyData.forEach(heap::add);
    }

    @Test
    void collectionConstructor() {
        heap = new Heap<>(Heap.HeapType.MAX);
        assertEquals(0, heap.getSize());
        heap = new Heap<>(dummyData);
        assertEquals(dummyData.size(), heap.getSize());
    }

    @Test
    void size() {
        heap = new Heap<>();
        assertEquals(0, heap.getSize());

        insertData();
        assertEquals(5, heap.getSize());

        heap.add(80);
        assertEquals(6, heap.getSize());
    }

    @Test
    void add() {
        heap = new Heap<>(Heap.HeapType.MAX);
        insertData();
    }

    @Test
    void peekWithMinHeap() {
        heap = new Heap<>();
        assertNull(heap.peek());
        insertData();
        assertEquals(2, heap.peek());
        assertEquals(2, heap.peek());
        assertEquals(2, heap.peek());

        heap.add(80);
        heap.add(-1);
        assertEquals(-1, heap.peek());
        assertEquals(-1, heap.peek());
        assertEquals(-1, heap.peek());
    }

    @Test
    void peekWithMaxHeap() {
        heap = new Heap<>(Heap.HeapType.MAX);
        assertNull(heap.peek());
        insertData();
        assertEquals(7, heap.peek());
        assertEquals(7, heap.peek());
        assertEquals(7, heap.peek());

        heap.add(80);
        heap.add(-1);
        assertEquals(80, heap.peek());
        assertEquals(80, heap.peek());
        assertEquals(80, heap.peek());
    }

    @Test
    void removeWithMinHeap() {
        heap = new Heap<>();
        assertThrows(NoSuchElementException.class, heap::remove);
        assertThrows(NoSuchElementException.class, heap::remove);
        insertData();
        assertEquals(2, heap.remove());
        assertEquals(3, heap.remove());
        assertEquals(5, heap.remove());

        heap.add(-1);
        heap.add(0);
        heap.add(-75);
        assertEquals(-75, heap.remove());
        assertEquals(-1, heap.remove());
        assertEquals(0, heap.remove());

        heap.add(0);
        heap.add(-1);
        assertEquals(-1, heap.remove());
        assertEquals(0, heap.remove());

        assertEquals(6, heap.remove());
        assertEquals(7, heap.remove());
        assertThrows(NoSuchElementException.class, heap::remove);
    }

    @Test
    void removeWithMaxHeap() {
        heap = new Heap<>(Heap.HeapType.MAX);
        assertThrows(NoSuchElementException.class, heap::remove);
        assertThrows(NoSuchElementException.class, heap::remove);
        insertData(); // 7, 3, 5, 2, 6
        assertEquals(7, heap.remove());
        assertEquals(6, heap.remove());
        assertEquals(5, heap.remove());

        heap.add(-1);
        heap.add(0);
        assertEquals(3, heap.remove());
        assertEquals(2, heap.remove());

        heap.add(70);
        heap.add(15);
        assertEquals(70, heap.remove());
        assertEquals(15, heap.remove());
        assertEquals(0, heap.remove());
        assertEquals(-1, heap.remove());

        assertThrows(NoSuchElementException.class, heap::remove);
    }
}