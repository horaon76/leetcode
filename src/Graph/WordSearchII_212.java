package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 * <p>
 * <p>
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 **/


class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word = null; // To store the complete word
}

public class WordSearchII_212 {
    private TrieNode root;
    private Set<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        root = buildTrie(words);
        result = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root);
            }
        }

        return new ArrayList<>(result);
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.word = word; // Mark the end of the word
        }
        return root;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node) {
        char ch = board[r][c];

        // Check if the cell is out of bounds or has been visited or is not in the Trie
        if (ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }

        // Move to the next Trie node
        node = node.children[ch - 'a'];

        // Check if a word is found
        if (node.word != null) {
            result.add(node.word);
        }

        // Mark the cell as visited
        board[r][c] = '#';

        // Explore neighbors: down, up, right, left
        if (r > 0) dfs(board, r - 1, c, node); // up
        if (r < board.length - 1) dfs(board, r + 1, c, node); // down
        if (c > 0) dfs(board, r, c - 1, node); // left
        if (c < board[0].length - 1) dfs(board, r, c + 1, node); // right

        // Restore the cell's original value
        board[r][c] = ch;
    }
}
/**
 * Time Complexity:
 * The overall time complexity can be approximated as
 * 𝑂
 * (
 * 𝑁
 * ×
 * 𝑀
 * ×
 * 4
 * 𝐿
 * +
 * 𝐾
 * )
 * O(N×M×4
 * L
 *  +K), where
 * 𝑁
 * N and
 * 𝑀
 * M are the dimensions of the board,
 * 𝐿
 * L is the maximum length of words in the Trie, and
 * 𝐾
 * K is the total number of characters in the input words.
 * Space Complexity:
 * The space complexity is
 * 𝑂
 * (
 * 𝐾
 * )
 * O(K) for storing the Trie and
 * 𝑂
 * (
 * 𝑊
 * )
 * O(W) for the result set, where
 * 𝑊
 * W is the number of found words.
 * **/