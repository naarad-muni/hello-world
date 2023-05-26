package org.naarad.muni.nocategory;

/**
 * <a href="https://leetcode.com/problems/string-compression/">string compression</a>
 */
public class StringCompression {
    public static void main(String[] args) {
        StringCompression compression = new StringCompression();
        compression.compress(new char[]{'a', 'b', 'c', 'd', 'd'});
    }

    public int compress(char[] chars) {

        int charArrayWriterIndex = 0;
        int charArrayReaderIndex = 0;

        while (charArrayReaderIndex < chars.length) {
            int totalConsecutiveCharsForGivenChar = getTotalConsecutiveChars(chars, charArrayReaderIndex);

            chars[charArrayWriterIndex++] = chars[charArrayReaderIndex];

            if (totalConsecutiveCharsForGivenChar > 1) {
                char[] numberArray = Integer.toString(totalConsecutiveCharsForGivenChar).toCharArray();
                for (char c : numberArray) {
                    chars[charArrayWriterIndex++] = c;
                }
            }

            charArrayReaderIndex += totalConsecutiveCharsForGivenChar;
        }

        return charArrayWriterIndex;
    }

    private int getTotalConsecutiveChars(char[] chars, int startIndex) {
        int endIndex = startIndex;

        while (endIndex < chars.length && chars[endIndex] == chars[startIndex]) {
            endIndex++;
        }

        return endIndex - startIndex;
    }
}
