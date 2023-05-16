package com.infix.topostfix.dataproject;

public class LinkedListNode {
    private LinkedListNode next;
    private Node value;

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode(Node value)
    {
        this.value = value;
        this.next = null;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public Node getValue() {
        return value;
    }
}
