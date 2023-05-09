package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/is-subsequence/">Problem statement link</a>
 */
public class SubsequenceCheck {
    public static void main(String[] args) {
        System.out.println(isSubsequence("a", "bbac"));
    }

    public static boolean isSubsequence(String s, String t) {
        int targetStringLength = t.length();
        int subsequenceLength = s.length();

        if (subsequenceLength == 0) {
            return true;
        } else if (targetStringLength == 0) {
            return false;
        }

        int j = 0;
        for (int i = 0; i < t.length() && j < subsequenceLength; i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
            }
        }

        return j == subsequenceLength;
    }
}
