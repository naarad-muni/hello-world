package org.naarad.muni.backtracking;

public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        RepeatedSubstringPattern pattern = new RepeatedSubstringPattern();
        System.out.println(pattern.repeatedSubstringPattern("abab"));
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1) {
            return false;
        }
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return true;
            } else {
                return false;
            }
        }

        if (s.length() == 3) {
            if (s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2)) {
                return true;
            } else {
                return false;
            }
        }
        final int stringLength = s.length();
        final int halfLength = stringLength / 2;
        final char lastCharacter = s.charAt(stringLength - 1);

        for (int i = 0; i < halfLength; i++) {
            if (s.charAt(i) == lastCharacter) {
                if ((stringLength % (i + 1) == 0) && checkIfSubstringCanCreateMainString(s, s.substring(0, i + 1))) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkIfSubstringCanCreateMainString(final String mainString, final String pattern) {
        final int patternLength = pattern.length();
        final int mainStringLength = mainString.length();

        for (int i = patternLength; i < mainStringLength; i += patternLength) {
            if (pattern.compareTo(mainString.substring(i, i + patternLength)) != 0) {
                return false;
            }
        }

        return true;
    }
}
