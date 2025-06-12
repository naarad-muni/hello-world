package org.naarad.muni.dynamic.prog;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/is-subsequence/">Is Subsequence</a>
 */
public class IsSubsequence {
    public static void main(String[] args) {
        final IsSubsequence subsequence = new IsSubsequence();
        System.out.println(subsequence.isSubsequence("abc", "ahbgdc"));
    }

    public boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (t == null || t.isEmpty()) {
            return false;
        }

        boolean[] previousFlag = new boolean[t.length() + 1];
        Arrays.fill(previousFlag, true);

        for (int i = 0; i < s.length(); i++) {
            boolean[] currentFlag = new boolean[t.length() + 1];
            currentFlag[0] = false;
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i) == t.charAt(j - 1)) {
                    currentFlag[j] = previousFlag[j - 1];
                } else {
                    currentFlag[j] = currentFlag[j - 1];
                }
            }
            previousFlag = currentFlag;
        }
        return previousFlag[previousFlag.length - 1];
    }
}
