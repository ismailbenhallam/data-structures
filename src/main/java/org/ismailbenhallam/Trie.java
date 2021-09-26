package org.ismailbenhallam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private static final int NUMBER_LETTERS = 26;
    private final Map<Character, Trie> children = new HashMap<>(NUMBER_LETTERS);
    private boolean isWord;

    public void insert(String word) {
        var ch = word.charAt(0);
        var node = children.get(ch);
        if (node == null) {
            node = new Trie();
            children.put(ch, node);
        }

        if (word.length() == 1) {
            node.isWord = true;
            return;
        }

        node.insert(word.substring(1));
    }

    public boolean searchCompleteWord(String word) {
        var ch = word.charAt(0);
        var child = children.get(ch);
        if (child == null) {
            return false;
        }

        if (word.length() == 1) {
            return child.isWord;
        }

        return child.searchCompleteWord(word.substring(1));
    }

    public boolean startWith(String prefix) {
        var ch = prefix.charAt(0);
        var child = children.get(ch);
        if (child == null) {
            return false;
        }

        if (prefix.length() == 1) {
            return true;
        }

        return child.startWith(prefix.substring(1));
    }

    public List<String> getAllWords() {
        var list = new ArrayList<String>();
        return getAll(list, "");
    }

    private List<String> getAll(List<String> strings, String computedString) {
        if (isWord) {
            strings.add(computedString);
        }
        for (Map.Entry<Character, Trie> entry : children.entrySet()) {
            entry.getValue().getAll(strings, computedString + entry.getKey());
        }

        return strings;
    }


}
