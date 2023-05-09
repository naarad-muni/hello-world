package org.naarad.muni.dynamic.prog;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.com/problems/longest-string-chain/">Problem Statement</a>
 */

public class LongestStringChain {

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
    }

    public static int longestStrChain(String[] words) {
        int longestChain = 1;

        Arrays.sort(words, Comparator.comparingInt(String::length));

        int[] longestStringChain = new int[words.length];

        Arrays.fill(longestStringChain, 1);

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length && (words[i].length() + 1 >= words[j].length()); j++) {
                if (checkIdPredecessor(words[i], words[j])) {
                    longestStringChain[j] = Math.max(longestStringChain[i] + 1, longestStringChain[j]);
                    longestChain = Math.max(longestChain, longestStringChain[j]);
                }
            }
        }
        return longestChain;
    }

    /**
     * Checks if "wordA" is a predecessor of "wordB".
     * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without
     * changing the order of the other characters to make it equal to wordB.
     * <p>
     * For example: "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
     *
     * @return
     */
    protected static boolean checkIdPredecessor(String wordA, String wordB) {
        int wordALength = wordA.length();

        final int wordBLength = wordB.length();
        if (wordALength + 1 != wordBLength) {
            return false;
        }

        int i = 0;
        int j = 0;
        for (i = 0, j = 0; j < wordALength && i < wordBLength; i++) {
            if (wordA.charAt(j) == wordB.charAt(i)) {
                j++;
            }
        }

        return j == wordALength;
    }
}
