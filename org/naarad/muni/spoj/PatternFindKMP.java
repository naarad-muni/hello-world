package org.naarad.muni.spoj;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <a href="https://www.spoj.com/problems/NAJPF/">SPOJ NAJPF</a>
 */
public class PatternFindKMP {
    public static void main(String[] args) throws java.lang.Exception {
        PatternFindKMP obj = new PatternFindKMP();

        final Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        for (int i = 0; i < num; i++) {
            final String text = scanner.next();
            final String pattern = scanner.next();
            obj.printPatternMatch(text, pattern);
        }

        scanner.close();
    }

    public void printPatternMatch(final String text, final String pattern) {
        int[] lps = prepareLPSArray(pattern);
        final ArrayList<Integer> patternMatchedInfo = new ArrayList<>();
        int totalMatchesFound = 0;
        int textIndex = 0;
        int patternIndex = 0;

        while (textIndex < text.length()) {
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                textIndex++;
                patternIndex++;
                if (patternIndex == pattern.length()) {
                    totalMatchesFound++;
                    patternMatchedInfo.add(textIndex - pattern.length());
                    patternIndex = lps[lps.length - 1]; // last lps data
                }
            } else if (patternIndex == 0) {
                textIndex++;
            } else {
                patternIndex = lps[patternIndex - 1];
            }
        }

        if (totalMatchesFound == 0) {
            System.out.println("Not Found");
        } else {
            System.out.println(totalMatchesFound);
            for (Integer i : patternMatchedInfo) {
                System.out.print((i + 1) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] prepareLPSArray(String pattern) {
        final int[] lps = new int[pattern.length()];
        int i = 1;
        int len = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }
}
