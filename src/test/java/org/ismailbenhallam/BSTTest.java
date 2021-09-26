package org.ismailbenhallam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    BST<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BST<>();
    }

    @Test
    void insert() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(4);
        bst.insert(6);
        bst.insert(2);
    }

    @Test
    void exists() {
        assertFalse(bst.contains(54));
        bst.insert(5);
        bst.insert(3);
        bst.insert(4);
        bst.insert(6);
        bst.insert(2);

        assertTrue(bst.contains(6));
        assertFalse(bst.contains(54));
    }

    @Test
    void inorder() {
        bst.insert(8534);
        bst.insert(5);
        bst.insert(3);
        bst.insert(4);
        bst.insert(6);
        bst.insert(2);
        bst.insert(7);

        var list = bst.valuesInOrder();
        System.out.println(Arrays.toString(list.toArray()));

        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) < list.get(i + 1));
        }
    }

    @Test
    void size() {
        assertEquals(0, bst.size());
        bst.insert(5);
        bst.insert(3);
        bst.insert(4);
        assertEquals(3, bst.size());

        bst.insert(6);
        bst.insert(2);
        assertEquals(5, bst.size());
    }

}
