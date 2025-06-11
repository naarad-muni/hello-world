package org.naarad.muni.dynamic.prog;

/**
 * TODO: Code Not working
 * Need to revisit:
 * <a href="https://leetcode.com/problems/maximum-repeating-substring/">[LC] Max Repeating Substring</a>
 */
public class MaximumRepeatingSubstring {
    public static void main(String[] args) {
        MaximumRepeatingSubstring s = new MaximumRepeatingSubstring();
        System.out.println(s.maxRepeatingDP("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));
    }

    public int maxRepeatingDP(String sequence, String word) {
        int[] dp = new int[sequence.length()];
        int maxSequence = 0;

        for (int i = 0; i <= sequence.length() - word.length(); i++) {
            if (sequence.startsWith(word, i)) {
                int endIndex = i + word.length() - 1;
                dp[endIndex] = maxSequence == 0 ? 1 : dp[i - 1] + 1;
                maxSequence = Math.max(maxSequence, dp[endIndex]);
            }
        }

        return maxSequence;
    }
}
