package ua.edu.ucu.utils;

import ua.edu.ucu.utils.ImmutableArrayList;


public class Queue {

    private ImmutableArrayList arrList;

    public Queue() {
        arrList = new ImmutableArrayList();
    }

    public Object dequeue() {
        Object first = arrList.get(0);
        arrList = arrList.remove(0);
        return first;
    }

    public Object peek() {
        return arrList.get(0);
    }

    public void enqueue(Object e) {
        arrList = arrList.add(e);
    }

    public int size(){
        return arrList.size();
    }

}

