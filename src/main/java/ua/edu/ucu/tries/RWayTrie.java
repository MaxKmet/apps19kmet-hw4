package ua.edu.ucu.tries;


import ua.edu.ucu.autocomplete.PrefixMatches;
import ua.edu.ucu.utils.Queue;

import java.util.ArrayList;


public class  RWayTrie implements Trie{

    public static void main(String[] args) {
        PrefixMatches pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
        System.out.println(pm.wordsWithPrefix(""));
    }

    private static int R = 256; // radix
    private Node root;          // root of trie

    private static class Node   {
        private Object val;
        public Node[] next = new Node[R];

    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt; }


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
    {  // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d+1);
    }

    @Override
    public void add(Tuple t) {
        root = add(root, t.term, t.weight, 0);
    }

    private Node add(Node x, String key, int val, int d)   {  // Change value associated with key if in subtrie rooted at x.
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        // Use dth key char to identify subtrie.
        x.next[c] = add(x.next[c], key, val, d+1);
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
        System.out.println(q.size());
        int size = q.size();
        for(int i = 0; i < size; i++){
            words.add((String) q.dequeue());
        }

        //while(q.peek() != null){
        //    words.add((String)q.dequeue());
        //}
        System.out.println(words);
        return words;
    }

    private void collect(Node x, String pre,Queue q) {
        if (x == null) return;
        if (x.val != null)
            q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }


    public void collect(Node x, String pre, String pat, Queue q)
    {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if (d == pat.length()) return;

        char next = pat.charAt(d);
        for (char c = 0; c < R; c++)
            if (next == '.' || next == c)
                collect(x.next[c], pre + c, pat, q);
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
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
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.val != null) return x;

        for (char c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }


}
