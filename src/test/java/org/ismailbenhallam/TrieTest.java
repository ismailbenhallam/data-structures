package org.ismailbenhallam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private Trie trie;
    private static final List<String> DATA_SET = List.of(
            "Hello",
            "Helicopter",
            "Hi",
            "Cat",
            "Car",
            "Code",
            "Core");

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    private void insertSomeData(Trie t) {
        DATA_SET.forEach(trie::insert);
    }

    @Test
    void insert() {
        insertSomeData(trie);
    }

    @Test
    void searchForWord() {
        assertFalse(trie.searchCompleteWord("word"));
        insertSomeData(trie);
        assertFalse(trie.searchCompleteWord("word"));
        assertFalse(trie.searchCompleteWord("H"));
        assertTrue(trie.searchCompleteWord("Hello"));
        assertFalse(trie.searchCompleteWord("Hellow"));
        assertFalse(trie.searchCompleteWord("Hell"));
    }

    @Test
    void startWith() {
        assertFalse(trie.startWith("word"));
        assertFalse(trie.startWith("Hello"));

        insertSomeData(trie);
        assertFalse(trie.startWith("word"));
        assertTrue(trie.startWith("H"));
        assertTrue(trie.startWith("He"));
        assertTrue(trie.startWith("Hell"));
        assertTrue(trie.startWith("Hello"));
        assertFalse(trie.startWith("Ha"));
        assertFalse(trie.startWith("Hammer"));
        assertFalse(trie.startWith("Hellow"));
        assertTrue(trie.startWith("Hell"));
    }


    @Test
    void getAllWords() {
        assertTrue(trie.getAllWords().isEmpty());

        insertSomeData(trie);
        assertFalse(trie.getAllWords().isEmpty());
        assertEquals(DATA_SET.size(), trie.getAllWords().size());
        trie.getAllWords().forEach(string -> assertTrue(DATA_SET.contains(string)));

        trie.insert("Hello");
        assertEquals(7, trie.getAllWords().size());

        trie.insert("New");
        assertEquals(8, trie.getAllWords().size());

        // Print all words in the trie
        System.out.println(trie.getAllWords());
    }
}