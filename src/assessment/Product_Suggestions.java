package assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  problem statement : https://leetcode.com/problems/search-suggestions-system/
 *  TC : O((N*log(N)) + (N*M)), where N is input array (products) size and M is maximum string length in products.
 *  SC : O(N*M)
 */
public class Product_Suggestions {

    Node root;

    class Node  {

        Node[] ch;
        List<String> words;
        Node() {
            ch = new Node[26];
            words = new ArrayList<>();
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        root = new Node();
        Arrays.sort(products);
        for(String w : products) {
            addToTrie(root, w);
        }
        List<List<String>> response = new ArrayList<>();
        for(char ch : searchWord.toCharArray()) {
            response.add(getSuggestionsByCharacter(ch - 'a'));
        }
        return response;
    }

    private void addToTrie(Node root, String word) {

        Node curr = root;
        for(char ch : word.toCharArray()) {
            int idx = (ch - 'a');
            if(curr.ch[idx] == null) {
                curr.ch[idx] = new Node();
            }
            curr = curr.ch[idx];
            if(curr.words.size() < 3) {
                curr.words.add(word);
            }
        }
    }

    private List<String> getSuggestionsByCharacter(int idx) {

        if(root != null) {
            root = root.ch[idx];
        }
        if(root != null) {
            return root.words;
        }
        return new ArrayList<>();
    }
}
