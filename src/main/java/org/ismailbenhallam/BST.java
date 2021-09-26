package org.ismailbenhallam;

import java.util.ArrayList;
import java.util.List;

// Binary Search Tree
public class BST<T extends Comparable<T>> {
    private T data;
    private BST<T> left;
    private BST<T> right;

    public BST() {
    }

    public BST(T t) {
        this.data = t;
    }

    public void insert(T data) {
        if (this.data == null) {
            this.data = data;
        } else if (this.data.compareTo(data) > 0) {
            if (left == null) {
                left = new BST<>(data);
            } else {
                left.insert(data);
            }
        } else if (this.data.compareTo(data) < 0) {
            if (right == null) {
                right = new BST<>(data);
            } else {
                right.insert(data);
            }
        }
    }

    public boolean contains(T data) {
        if (this.data == null) {
            return false;
        }
        if (this.data.equals(data)) {
            return true;
        }
        if (this.data.compareTo(data) > 0) {
            return left != null && left.contains(data);
        } else if (this.data.compareTo(data) < 0) {
            return right != null && right.contains(data);
        }
        return false;
    }

    public int size() {
        if (data == null) {
            return 0;
        }
        return (left != null ? left.size() : 0) + (right != null ? right.size() : 0) + 1;
    }

    public List<T> valuesInOrder() {
        var list = new ArrayList<T>();
        if (left != null) {
            list.addAll(left.valuesInOrder());
        }
        if (data != null) {
            list.add(data);
        }
        if (right != null) {
            list.addAll(right.valuesInOrder());
        }
        return list;
    }

}
