package ua.edu.ucu.tries;
import java.util.HashMap;

public class Node  {

    public Object val;
    public HashMap<Character, Node> next;

    public Node(){

        next = new HashMap<>();
    }

}
