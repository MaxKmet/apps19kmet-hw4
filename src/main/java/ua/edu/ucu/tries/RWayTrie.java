package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;

import java.util.ArrayList;
import java.util.HashMap;

public class  RWayTrie implements Trie{

    private static int R = 256;
    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next.get(c));
        return cnt;
    }


    public Tuple get(String key){
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return (Tuple) x.val;
    }


    @Override
    public boolean contains(String word) {
        return  (get(word) != null);
    }

    private Node get(Node x, String key, int d)
    {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next.get(c), key, d+1);
    }

    @Override
    public void add(Tuple t) {
        root = add(root, t.term, t.weight, 0);
    }

    private Node add(Node x, String key, int val, int d)   {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next.put(c, add(x.next.get(c), key, val, d+1));
        return x;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pre) {
        Queue q = new Queue();
        ArrayList<String>words = new ArrayList<>();
        collect(get(root, pre, 0), pre, q);

        int size = q.size();
        for(int i = 0; i < size; i++){
            words.add((String) q.dequeue());
        }

        return words;
    }

    private void collect(Node x, String pre,Queue q) {
        if (x == null) return;
        if (x.val != null)
            q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next.get(c), pre + c, q);
    }

    public boolean delete(String key) {
        root = delete(root, key, 0);
        return (root != null);
    }


    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length())
            x.val = null;
        else{
            char c = key.charAt(d);
            x.next.put(c, delete(x.next.get(c), key, d+1));
        }
        if (x.val != null) return x;

        for (char c = 0; c < R; c++)
            if (x.next.get(c) != null)
                return x;
        return null;
    }


}
