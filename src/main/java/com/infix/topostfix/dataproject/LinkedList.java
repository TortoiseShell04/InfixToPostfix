package com.infix.topostfix.dataproject;

public class LinkedList {
    private LinkedListNode head;
    public LinkedList(LinkedListNode head)
    {
        this.head = head;
    }
    public LinkedList()
    {
        this.head = null;
    }


    public void add(Node n)
    {
        if (isEmpty())
        {
            head = new LinkedListNode(n);
        }
        else {
            LinkedListNode temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            temp.setNext(new LinkedListNode(n));
        }
    }
    public void addFirst(Node n)
    {
        if(isEmpty())
        {
            head = new LinkedListNode(n);
        }
        else {
            LinkedListNode temp = new LinkedListNode(n);
            temp.setNext(head);
            head = temp;
        }
    }
    public Node getFirst()
    {
        return head.getValue();
    }
    public void removeFirst()
    {
        head = head.getNext();
    }
    public Node getLast()
    {
        LinkedListNode temp = head;
        while(temp.getNext()!=null)
        {
            temp = temp.getNext();
        }
        return temp.getValue();
    }
    public int getSize()
    {
        if(isEmpty())
        {
            return 0;
        }
        else{
            int size = 0;
            LinkedListNode temp = head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
                size++;
            }
            size++;
            return size;
        }
    }
    public boolean isEmpty(){return head == null;}
}
