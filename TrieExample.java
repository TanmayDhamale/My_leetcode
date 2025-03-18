class TrieNode {
    TrieNode[] children = new TrieNode[26]; // Array for 'a' to 'z'
    boolean isEndOfWord;

    public TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(); // Create new node if missing
            }
            node = node.children[index];
        }
        node.isEndOfWord = true; // Mark end of word
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) return false; // Word not found
            node = node.children[index];
        }
        return node.isEndOfWord; // Return true if it's a complete word
    }

    // Delete a word from the Trie
    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int depth) {
        if (node == null) return false;

        if (depth == word.length()) {
            if (!node.isEndOfWord) return false; // Word not found
            node.isEndOfWord = false; // Unmark word
            return isEmpty(node); // Check if node has no children
        }

        int index = word.charAt(depth) - 'a';
        if (deleteHelper(node.children[index], word, depth + 1)) {
            node.children[index] = null;
            return !node.isEndOfWord && isEmpty(node); // Remove node if empty
        }
        return false;
    }

    // Helper function to check if a TrieNode has children
    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }
}

public class TrieExample {
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words
        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");

        // Search words
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("app"));   // true
        System.out.println(trie.search("bat"));   // true
        System.out.println(trie.search("cat"));   // false

        // Delete a word
        trie.delete("apple");
        System.out.println(trie.search("apple")); // false
        System.out.println(trie.search("app"));   // true (app is still there)
    }
}