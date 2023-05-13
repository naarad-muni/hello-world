package org.naarad.muni.backtracking;

/**
 * <a href="https://leetcode.com/problems/wildcard-matching/">wildcard matching</a>
 * Code is working but not optimised. Thus getting Time limit exceeded exception.
 */
public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching matcher = new WildcardMatching();
        System.out.println(matcher.isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));

    }

    public boolean isMatch(String s, String p) {
        StringBuilder pattern = new StringBuilder();
        boolean foundStar = false;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                if (!foundStar) {
                    pattern.append(p.charAt(i));
                    foundStar = true;
                }
            } else {
                pattern.append(p.charAt(i));
                foundStar = false;
            }

        }
        return matches(s, pattern.toString());
    }

    private boolean matches(String string, String pattern) {
        int stringLength = string.length();
        int j = 0;
        int i = 0;
        for (; i < stringLength && j < pattern.length(); i++) {
            if (pattern.charAt(j) == '?') {
                j++;
            } else if (pattern.charAt(j) == '*') {
                if (matches(string.substring(i), pattern.substring(j + 1))) {// don't use the *
                    return true;
                }
            } else if (string.charAt(i) == pattern.charAt(j)) {
                j++;
            } else {
                return false;
            }
        }

        if (i < stringLength && j == pattern.length()) {
            return false;
        } else if (i == stringLength && j < pattern.length()) {
            for (; j < pattern.length(); j++) {
                if (pattern.charAt(j) != '*') {
                    return false;
                }
            }
            return true;
        }
        return true;
    }
}
