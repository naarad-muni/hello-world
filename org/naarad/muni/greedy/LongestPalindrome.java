package org.naarad.muni.greedy;

public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome palin = new LongestPalindrome();
        System.out.println(palin.longestPalindrome("aaaa"));
    }

    public String longestPalindrome(String s) {
        int i = 0;
        int len = s.length();
        final ReturnType palindromeDetail = new ReturnType(0, 1);

        if (len == 1) {
            return s;
        } else if (len == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return String.valueOf(s.charAt(0));
            }
        }

        while (i < len - 1) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                ReturnType temp = getPalindromeLength(s, i, i + 1);

                if (temp.length > palindromeDetail.length) {
                    palindromeDetail.length = temp.length;
                    palindromeDetail.startIndex = temp.startIndex;
                }
            }

            if (i - 1 >= 0 && s.charAt(i - 1) == s.charAt(i + 1)) {
                ReturnType temp = getPalindromeLength(s, i - 1, i + 1);

                if (temp.length > palindromeDetail.length) {
                    palindromeDetail.length = temp.length;
                    palindromeDetail.startIndex = temp.startIndex;
                }
            }

            i++;
        }

        return s.substring(palindromeDetail.startIndex, (palindromeDetail.startIndex + palindromeDetail.length));
    }

    private ReturnType getPalindromeLength(String s, int leftIndex, int rightIndex) {
        int length = s.length();

        while (leftIndex >= 0 && rightIndex < length && s.charAt(leftIndex) == s.charAt(rightIndex)) {
            leftIndex--;
            rightIndex++;
        }

        return new ReturnType(leftIndex + 1, rightIndex - leftIndex - 1);
    }

    class ReturnType {
        int startIndex;

        int length;

        ReturnType(int start, int len) {
            startIndex = start;
            length = len;
        }

    }
}
