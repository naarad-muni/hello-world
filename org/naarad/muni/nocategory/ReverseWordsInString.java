package org.naarad.muni.nocategory;

/**
 * <a href="https://leetcode.com/problems/reverse-words-in-a-string/">reverse words in a string</a>
 */
public class ReverseWordsInString {

    private static final char WHITESPACE = ' ';

    public String reverseWords(String s) {
        StringBuilder newString = new StringBuilder(s);

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char characterAtI = newString.charAt(i);
            newString.setCharAt(i, newString.charAt(j));
            newString.setCharAt(j, characterAtI);

            i++;
            j--;
        }

        i = 0;
        boolean sentenceStart = true;
        while (i < s.length()) {
            removeAllWhitespacesExceptOne(newString, i);
            if (sentenceStart) {

                sentenceStart = false;
            }
        }
        return newString.toString();
    }

    private void removeAllWhitespacesExceptOne(StringBuilder newString, int startIndex) {
        if (newString.charAt(startIndex) != WHITESPACE) {
            return;
        }
        int totalWhitespaceCounter = 0;
        for (int i = startIndex; i < newString.length() && newString.charAt(i) == WHITESPACE; i++) {
            totalWhitespaceCounter++;
        }

        if (totalWhitespaceCounter > 1) {
            
        }
    }
}
