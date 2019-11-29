package ua.edu.ucu.iterator;


import ua.edu.ucu.utils.IterableTrie;


public class TrieIterator implements Iterator{
    private IterableTrie trie;

    public Object getNext(){

        System.out.println("a");
        return 1;
    }

    public boolean hasMore(){
        return true;

    }

}
