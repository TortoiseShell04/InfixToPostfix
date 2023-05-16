package com.infix.topostfix.dataproject;

public class Node {
    private String data;
    private Node left,right;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public Node(String data) {
        this.data = data;
    }
}
