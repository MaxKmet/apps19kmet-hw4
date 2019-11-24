package ua.edu.ucu.tries;


public class Node {
    private char c;
    public Node prev;
    public Node[] links;

    public Node(char c, Node prev){
        this.c = c;
        this.prev = prev;
        links = new Node[26];
    }

    public char getC(){
        return c;
    }

    public void addChild(Node child){
        int asci = (int) Character.toLowerCase(child.c);
        int asci_a = 97; //for lower case
        if(asci<=122 & asci>=97)
            links[asci-asci_a] = child;
        else
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
    }



}
