package terlan.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDictionary {
    private ArrayList<String> dictionary;
    public MyDictionary(List<String> words) {
        //TODO
        //Filling words of the dictionary from the given list
        dictionary = new ArrayList<>();
        dictionary.addAll(words);
    }
    /**
     * adds word into dictionary
     * @param word
     * @return true if new word has been added, false if such word already exists
     *
     */
    public boolean addWord(String word) {
        //TODO
        boolean flag = false;
        if (!dictionary.contains(word)){
            dictionary.add(word);
            flag = true;
        }
        return flag;
    }
    /**
     * looking for the words with a given prefix
     * @param prefix
     * @return array of words with the given prefix
     */
    public String [] getWordsByPrefix(String prefix) {

        ArrayList<String> list = new ArrayList<>();
        for (String word: dictionary) {
            int accum = 0;
            if (word.length() >= prefix.length()) {
                for (int i = 0; i < prefix.length(); i++) {
                    if (word.charAt(i) == prefix.charAt(i)) {
                        accum++;
                    }
                }
                if (accum == prefix.length()) {
                    list.add(word);
                }
            }
            list.sort(String::compareTo);
        }
        return list.toArray(new String[0]);
    }
}