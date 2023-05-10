import java.util.*;

// 208. Implement Trie (Prefix Tree)
// https://leetcode.com/problems/implement-trie-prefix-tree/
class Trie {
    private class Node{
        Node[] child;
        boolean isEnd;

        Node(){
            child = new Node[26];
        }
    }
    final private Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node curr = root;
        for(int i = 0;i < word.length();i++){
            char ch = word.charAt(i);

            if(curr.child[ch-'a'] == null){
                curr.child[ch - 'a'] = new Node();
            }
            curr = curr.child[ch - 'a'];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        Node curr = root;
        for(int i = 0;i < word.length();i++){
            char ch = word.charAt(i);

            if(curr.child[ch-'a'] == null) return false;
            curr = curr.child[ch - 'a'];
        }

        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = root;

        for(int i = 0;i < prefix.length();i++){
            char ch = prefix.charAt(i);

            if(curr.child[ch- 'a'] == null) return false;
            curr = curr.child[ch - 'a'];
        }

        return true;
    }
}

// 720. Longest Word in Dictionary
// https://leetcode.com/problems/longest-word-in-dictionary/
class Solution {
    class Node {
        Node[] childs;
        boolean isEnd;

        Node() {
            childs = new Node[26];
        }
    }
    public String longestWord(String[] words) {
        Node root = new Node();
        for(String word: words){
            addToTrie(word,root);
        }

        String largestString = dfs(root,0);
        return largestString;
    }

    private String dfs(Node root, int height){
        String curr = null;
        int idx = -1;
        for(int i = 0;i < 26;i++){
            if(root.childs[i] != null && root.childs[i].isEnd){
                
                String temp = dfs(root.childs[i],height+1);
                if(curr == null ) { curr = temp; idx = i;}
                else if(temp.length() > curr.length()){ 
                    curr = temp; 
                    idx = i;
                }
            }
        }

        if(curr == null && idx != -1) return ""+(char)('a'+idx);

        if(idx != -1) return curr = (char)('a'+idx) + curr;
        return "";
    }

    private void addToTrie(String word,Node root){
        Node curr = root;
        for(int i = 0;i < word.length();i++){
            char ch = word.charAt(i);
            if(curr.childs[ch - 'a'] == null) curr.childs[ch - 'a'] = new Node();
            curr = curr.childs[ch - 'a'];
        }
        curr.isEnd = true;
        return;
    }
}

// 211. Design Add and Search Words Data Structure
// https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
class WordDictionary {
    private WordDictionary[] children;
    boolean isEndOfWord;
    // Initialize your data structure here. 
    public WordDictionary() {
        children = new WordDictionary[26];
        isEndOfWord = false;
    }
    
    // Adds a word into the data structure. 
    public void addWord(String word) {
        WordDictionary curr = this;
        for(char c: word.toCharArray()){
            if(curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new WordDictionary();
            curr = curr.children[c - 'a'];
        }
        curr.isEndOfWord = true;
    }
    
    // Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. 
    public boolean search(String word) {
        WordDictionary curr = this;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(c == '.'){
                for(WordDictionary ch: curr.children)
                    if(ch != null && ch.search(word.substring(i+1))) return true;
                return false;
            }
            if(curr.children[c - 'a'] == null) return false;
            curr = curr.children[c - 'a'];
        }
        return curr != null && curr.isEndOfWord;
    }
}