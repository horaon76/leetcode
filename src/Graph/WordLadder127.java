package Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 **/
public class WordLadder127 {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        // Early exit if the endWord is not in the dictionary
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Use two sets for the frontiers of the BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int steps = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller set
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] wordArray = word.toCharArray();

                for (int i = 0; i < wordArray.length; i++) {
                    char originalChar = wordArray[i];

                    // Try all possible letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue; // Skip the original character
                        wordArray[i] = c;
                        String newWord = new String(wordArray);

                        // Check if the new word exists in the other set
                        if (endSet.contains(newWord)) {
                            return steps + 1;
                        }

                        // If it exists in the word list, add it to the next set
                        if (wordSet.contains(newWord)) {
                            nextSet.add(newWord);
                            wordSet.remove(newWord); // Remove to prevent cycles
                        }
                    }

                    // Restore the original character
                    wordArray[i] = originalChar;
                }
            }

            beginSet = nextSet; // Move to the next level
            steps++;
        }

        return 0; // Return 0 if there's no transformation found
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
//        String[] wordList = new String[]{"hot", "dot", "dog", "lot", "log"};
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        int result = ladderLength(beginWord, endWord, wordList);
        System.out.println("Length of shortest transformation sequence: " + result);
    }
}
/**
 * Complexity Analysis
 * Time Complexity: The time complexity is still
 * 𝑂
 * (
 * 𝑁
 * ×
 * 𝐿
 * ×
 * 26
 * )
 * O(N×L×26), where
 * 𝑁
 * N is the number of words in the word list and
 * 𝐿
 * L is the length of each word. However, in practice, bidirectional search often finds the solution faster.
 *
 * Space Complexity: The space complexity remains
 * 𝑂
 * (
 * 𝑁
 * )
 * O(N) for the word set and the current search frontiers.
 * **/