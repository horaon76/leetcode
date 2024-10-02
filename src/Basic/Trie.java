package Basic;

import java.util.HashMap;

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isEndOfWord;
    }

    // Delete a word from the Trie
    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int depth) {
        if (node == null) {
            return false;
        }
        if (depth == word.length()) {
            if (node.isEndOfWord) {
                node.isEndOfWord = false; // Unmark the end of the word
                return node.children.isEmpty(); // Return true if node has no children
            }
            return false; // Word not found
        }
        char ch = word.charAt(depth);
        TrieNode childNode = node.children.get(ch);
        if (childNode == null) {
            return false; // Word not found
        }
        boolean shouldDeleteCurrentNode = deleteHelper(childNode, word, depth + 1);

        // If true is returned, delete the mapping of character from current node
        if (shouldDeleteCurrentNode) {
            node.children.remove(ch);
            return node.children.isEmpty(); // Return true if node has no children
        }
        return false; // Return false if not able to delete
    }
}

// Example usage
class TrieExample {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("app")); // false
        trie.insert("app");
        System.out.println(trie.search("app")); // true
        trie.delete("apple");
        System.out.println(trie.search("apple")); // false
    }
}
