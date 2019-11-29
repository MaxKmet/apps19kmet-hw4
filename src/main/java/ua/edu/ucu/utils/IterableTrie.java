package ua.edu.ucu.utils;

import ua.edu.ucu.iterator.Iterator;
import ua.edu.ucu.iterator.TrieIterator;
import ua.edu.ucu.tries.RWayTrie;

public class IterableTrie implements IterableCollection{

    @Override
    public Iterator createIterator() {
        return new TrieIterator();
    }
}
