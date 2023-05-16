package com.infix.topostfix.dataproject;


public class Stack {
    private final LinkedList ll;

    public Stack(){ll = new LinkedList();}


    boolean isEmpty() {return ll.isEmpty();}

    public void push(Node n){ll.addFirst(n);}
    public Node pop(){
        if(isEmpty()){return null;}
        else
        {
            Node temp = ll.getFirst();
            ll.removeFirst();
            return temp;
        }
    }
    public Node peak()
    {
        if(isEmpty()){return null;}
        else{return ll.getFirst();}
    }
    public int getSize(){
        return ll.getSize();}
}
