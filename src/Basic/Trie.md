A Trie (pronounced as "try") is a tree-like data structure used for efficiently storing and retrieving a dynamic set of strings, typically for tasks such as autocomplete, spell checking, and IP routing. Below, I’ll describe a common problem that can be solved using a Trie, along with the implementation in Java.

### Problem: Implement a Phone Directory

**Description**:  
Design a phone directory that supports the following operations:
1. **Insert a phone number** into the directory.
2. **Search for a phone number** to see if it exists.
3. **Find all numbers** that start with a given prefix.

### Example:
**Input**:
```
Insert: "12345"
Insert: "12346"
Insert: "12347"
Insert: "56789"
Search: "12345"  -> returns true
Search: "1234"   -> returns false
StartsWith: "123" -> returns ["12345", "12346", "12347"]
```

### Trie Node Definition
First, we define a Trie node that will represent each character in the phone numbers.

### Java Implementation

```java
import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] children = new TrieNode[10]; // Only digits 0-9
    boolean isEndOfNumber; // True if the node marks the end of a phone number
}

class PhoneDirectory {
    private TrieNode root;

    public PhoneDirectory() {
        root = new TrieNode();
    }

    // Insert a phone number into the Trie
    public void insert(String number) {
        TrieNode node = root;
        for (char digit : number.toCharArray()) {
            int index = digit - '0'; // Convert char to int
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfNumber = true; // Mark the end of the phone number
    }

    // Search for a phone number
    public boolean search(String number) {
        TrieNode node = root;
        for (char digit : number.toCharArray()) {
            int index = digit - '0';
            if (node.children[index] == null) {
                return false; // Number not found
            }
            node = node.children[index];
        }
        return node.isEndOfNumber; // Return true if it's a complete number
    }

    // Find all numbers that start with a given prefix
    public List<String> startsWith(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;

        // Traverse the Trie to find the prefix
        for (char digit : prefix.toCharArray()) {
            int index = digit - '0';
            if (node.children[index] == null) {
                return results; // No numbers with this prefix
            }
            node = node.children[index];
        }

        // Perform DFS to find all numbers that start with the prefix
        dfs(node, prefix, results);
        return results;
    }

    // Helper method for DFS traversal to collect all numbers
    private void dfs(TrieNode node, String currentPrefix, List<String> results) {
        if (node.isEndOfNumber) {
            results.add(currentPrefix); // Add current prefix to results
        }

        for (int i = 0; i < 10; i++) {
            if (node.children[i] != null) {
                dfs(node.children[i], currentPrefix + i, results); // Explore further
            }
        }
    }

    // Main method to test the Phone Directory
    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory();
        directory.insert("12345");
        directory.insert("12346");
        directory.insert("12347");
        directory.insert("56789");

        System.out.println(directory.search("12345")); // Output: true
        System.out.println(directory.search("1234"));  // Output: false

        List<String> results = directory.startsWith("123");
        System.out.println("Numbers starting with '123': " + results); // Output: [12345, 12346, 12347]
    }
}
```

### Explanation of the Code

1. **TrieNode Class**:
    - Contains an array of children (for digits 0-9) and a boolean `isEndOfNumber` to mark the end of a phone number.

2. **PhoneDirectory Class**:
    - **Insert**: Adds a phone number to the Trie. It traverses each digit, creating new nodes as necessary, and marks the end of the number.
    - **Search**: Checks if a phone number exists in the Trie. It traverses through the digits and returns true if it finds a complete number.
    - **startsWith**: Finds all phone numbers that start with a given prefix. It first traverses the Trie to reach the end of the prefix, then uses DFS to collect all numbers that branch off from that point.

3. **Main Method**:
    - Tests the functionality of the Phone Directory by inserting numbers, searching for specific numbers, and finding all numbers with a given prefix.

### Output
When you run the program, the output will be:
```
true
false
Numbers starting with '123': [12345, 12346, 12347]
```

# Use Cases

A **Trie** is a versatile data structure commonly used for various applications in string processing and searching. Here are some notable use cases and related patterns where Tries can be effectively utilized:

### Use Cases of Trie

1. **Autocomplete System**:
    - Tries are often used to implement autocomplete features in text editors, search engines, and mobile keyboards. By storing words in a Trie, the system can quickly retrieve suggestions based on the prefix typed by the user.

2. **Spell Checker**:
    - Tries can be employed to create a dictionary of valid words. When a user types a word, the spell checker can quickly verify if it exists in the Trie and suggest corrections for misspelled words.

3. **IP Routing**:
    - In networking, Tries can be used for IP routing. The prefixes of IP addresses can be stored in a Trie to efficiently route packets based on their destination addresses.

4. **Pattern Matching**:
    - Tries can be utilized for string matching algorithms, such as the Aho-Corasick algorithm, which searches for multiple patterns simultaneously. This is useful in applications like search engines and data mining.

5. **Finding Longest Common Prefix**:
    - Tries are effective in finding the longest common prefix among a set of strings. This can be useful in various applications like DNA sequence analysis.

6. **Word Search Puzzles**:
    - In problems where you need to find all words in a grid (like Boggle), Tries can help efficiently search for valid words based on prefixes during a depth-first search (DFS).

7. **Data Compression**:
    - Tries can be used in algorithms like Huffman coding for compressing data by storing variable-length codes for symbols.

### Patterns Related to Trie

1. **Insert/Search Prefix**:
    - The fundamental operations of a Trie involve inserting and searching strings based on prefixes. This pattern is crucial for any application that requires quick retrieval of data based on string prefixes.

2. **Count Words with a Prefix**:
    - An extension of the basic Trie operations where you count the number of words that start with a given prefix. This is particularly useful in applications like search suggestions and user input predictions.

3. **Store Additional Information**:
    - Tries can be extended to store more than just strings. For instance, you can store metadata along with each word (e.g., frequency of usage, associated definitions) in the Trie nodes.

4. **Trie of Sets**:
    - Sometimes, you may want to store sets of items (like a set of users associated with a keyword). You can use a Trie to represent these sets, allowing efficient prefix searches and lookups.

5. **LCP Array Generation**:
    - When building a Suffix Array, you can use a Trie to help generate the Longest Common Prefix (LCP) array efficiently. This is useful in various string processing problems.

6. **Palindrome Checks**:
    - Tries can be used to store strings in a way that helps in identifying palindromic substrings or words efficiently. This is beneficial in applications that deal with palindromes.

### Example Problems Involving Tries

1. **Word Break Problem**:
    - Given a string and a dictionary of words, determine if the string can be segmented into space-separated words from the dictionary. A Trie can efficiently check if prefixes exist in the dictionary.

2. **Replace Words in a Sentence**:
    - Given a sentence and a list of roots, replace all occurrences of the roots with the shortest prefix in the Trie. This is useful for text processing tasks like censorship or highlighting keywords.

3. **Longest Unique Prefix**:
    - Given a set of words, find the longest unique prefix for each word. This can be implemented using a Trie to track occurrences of prefixes.

4. **Finding Anagrams**:
    - Store anagrams of words in a Trie to quickly retrieve them. This can be particularly useful in word games or language processing.