import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // Assuming lowercase a-z
        isEndOfWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }
}

public class WordBreak {
    private static Trie trie;
    private static Map<String, Boolean> memo;

    public static boolean wordBreak(String s, List<String> wordDict) {
        trie = new Trie();
        memo = new HashMap<>();

        // Insert all words into Trie
        for (String word : wordDict) {
            trie.insert(word);
        }

        return canBreak(s);
    }

    private static boolean canBreak(String s) {
        if (s.isEmpty()) return true;
        if (memo.containsKey(s)) return memo.get(s);

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (trie.search(prefix) && canBreak(s.substring(i))) {
                memo.put(s, true);
                return true;
            }
        }
        
        memo.put(s, false);
        return false;
    }

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("leet", "code", "apple", "pen");
        String s = "leetcode";
        
        System.out.println("Can break word? " + wordBreak(s, wordDict)); // true
    }
}