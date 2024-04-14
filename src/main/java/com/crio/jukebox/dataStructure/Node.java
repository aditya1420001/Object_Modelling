package com.crio.jukebox.dataStructure;

public class Node<T> {
    private T data;
    public Node<T> prev;
    public Node<T> next;

    Node() {
        data = null;
        next = prev = null;
    }

    public Node(T data) {
        this();
        this.data = data;
    }

    public T value() {
        return data;
    }

}
