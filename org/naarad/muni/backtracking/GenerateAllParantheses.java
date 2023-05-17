package org.naarad.muni.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href=" https://leetcode.com/problems/generate-parentheses">generate parentheses</a>
 * <p>
 * 1 <= n <= 8
 */
public class GenerateAllParantheses {
    public static void main(String[] args) {
        GenerateAllParantheses generate = new GenerateAllParantheses();
        for (String answer :
                generate.generateParenthesis(2)) {
            System.out.println(answer);
        }
    }

    public List<String> generateParenthesis(int n) {

        List<String> validList = new ArrayList<>();
        char[] parentheses = new char[n * 2];

        generate(parentheses, 0, n * 2, n, n, validList);

        return validList;
    }

    private void generate(final char[] parentheses, final int currentPosition, final int length,
                          final int positiveCharLeft, final int negativeCharLeft, final List<String> validList) {
        if (currentPosition == length) {
            if (validateParenthesis(parentheses)) {
                String s = new String(parentheses);
                validList.add(s);
            }
            return;
        }

        // closing brackets are lesser than opening brackets, that means we have added a closing bracket in advance
        if (positiveCharLeft > negativeCharLeft) {
            return;
        }

        // put positive char first
        if (positiveCharLeft > 0) {
            parentheses[currentPosition] = '(';
            generate(parentheses, currentPosition + 1, length, positiveCharLeft - 1, negativeCharLeft, validList);
        }

        if (negativeCharLeft > 0) {
            parentheses[currentPosition] = ')';
            generate(parentheses, currentPosition + 1, length, positiveCharLeft, negativeCharLeft - 1, validList);
        }
    }

    private boolean validateParenthesis(char[] parenthesis) {
        int parenthesesValidator = 0;

        for (char c : parenthesis) {
            if (c == '(') {
                parenthesesValidator++;
            } else if (c == ')') {
                parenthesesValidator--;
            }
            if (parenthesesValidator < 0) {
                return false;
            }
        }

        return parenthesesValidator == 0;
    }
}
