package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
            int num_added = 0;

           for(int i = 0; i < strings.length; i++){
               if (strings[i].length() > 2) {
                   Tuple t = new Tuple(strings[i], strings[i].length());
                   trie.add(t);
                   num_added++;
               }
           }
           return num_added;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> words = trie.wordsWithPrefix(pref);

        ArrayList<String> list = new ArrayList<>();
        for(String word: words){
            list.add(word);
        }

        Collections.sort(list, Comparator.comparing(String::length));
        ArrayList<String> filtered_list = new ArrayList<>();
        int num_stages = 0;
        int init_length = 0;
        for(String word: list){

            if (num_stages < 3) {
                if (word.length() >= 2) {
                    filtered_list.add(word);
                }
            }
            if (word.length() > init_length){
                init_length = word.length();
                num_stages++;
            }
        }
        return filtered_list;
    }

}
