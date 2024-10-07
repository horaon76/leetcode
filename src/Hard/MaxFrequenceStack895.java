package Hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaxFrequenceStack895 {

    private Map<Integer, Integer> freqMap;  // stores the frequency of each element
    private Map<Integer, Stack<Integer>> freqStacks;  // stores stacks for each frequency
    private int maxFreq;  // stores the current maximum frequency

    public MaxFrequenceStack895() {
        freqMap = new HashMap<>();
        freqStacks = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        // Get the current frequency of x and increase it by 1
        int freq = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, freq);

        // Update the max frequency
        if (freq > maxFreq) {
            maxFreq = freq;
        }

        // Push x into the stack corresponding to its new frequency
        freqStacks.computeIfAbsent(freq, z -> new Stack<>()).push(x);
    }

    public int pop() {
        // Get the most frequent element (top of the stack at maxFreq)
        Stack<Integer> stack = freqStacks.get(maxFreq);
        int x = stack.pop();

        // Decrease the frequency of the popped element
        freqMap.put(x, freqMap.get(x) - 1);

        // If no more elements are left at maxFreq, reduce maxFreq
        if (stack.isEmpty()) {
            maxFreq--;
        }

        return x;
    }
}


