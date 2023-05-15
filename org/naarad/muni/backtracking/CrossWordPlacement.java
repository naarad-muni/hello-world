package org.naarad.muni.backtracking;

public class CrossWordPlacement {
    static final char VERTICAL_FALSE = '(';
    static final char HORIZONTAL_FALSE = ')';
    static final char HORIZONTAL_AND_VERTICAL_FALSE = '[';
    static final char ANY_CHARACTER = ' ';
    static final char NO_CHARACTER = '#';

    public static void main(String[] args) {
        CrossWordPlacement placement = new CrossWordPlacement();
        System.out.println(
                placement.placeWordInCrossword(
                        new char[][]{{'z', ' '}, {'z', ' '}}, "a"));
    }

    public boolean placeWordInCrossword(char[][] board, String word) {
        char[][] memoization = new char[board.length][board[0].length];
        final String reverseWord = getReversedWord(word);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (memoization[i][j] != HORIZONTAL_AND_VERTICAL_FALSE) {
                    if (board[i][j] == ANY_CHARACTER || Character.isLowerCase(board[i][j])) {
                        if (memoization[i][j] != HORIZONTAL_FALSE &&
                                canPlaceWordHorizontal(board, i, j, word, reverseWord, memoization)) {
                            return true;
                        }
                        if (memoization[i][j] != VERTICAL_FALSE && memoization[i][j] != HORIZONTAL_AND_VERTICAL_FALSE
                                && canPlaceWordVertical(board, i, j, word, reverseWord, memoization)) {
                            return true;
                        }

                        memoization[i][j] = HORIZONTAL_AND_VERTICAL_FALSE;
                    }
                }
            }

        }

        return false;
    }

    private String getReversedWord(final String word) {
        final StringBuilder wordBuilder = new StringBuilder();
        wordBuilder.append(word);
        wordBuilder.reverse();

        return wordBuilder.toString();
    }

    public boolean canPlaceWordHorizontal(final char[][] board, final int i, final int j, final String word,
                                          final String reverseWord, final char[][] memoization) {
        boolean wordMatchStatus = true;
        boolean reverseWordMatchStatus = true;
        int n = board[0].length;
        int k = 0;

        for (k = 0; k < word.length() && (wordMatchStatus || reverseWordMatchStatus) &&
                k + j < n && board[i][k + j] != NO_CHARACTER; k++) {
            int currentColumn = j + k;
            char currentCharacter = board[i][currentColumn];
            if (Character.isLowerCase(currentCharacter)) {
                if (word.charAt(k) != currentCharacter) {
                    wordMatchStatus = false;
                }
                if (reverseWord.charAt(k) != currentCharacter) {
                    reverseWordMatchStatus = false;
                }
            }
        }

        if ((wordMatchStatus || reverseWordMatchStatus) && k == word.length() &&
                (((k + j) < n && board[i][k + j] == NO_CHARACTER) || (k + j == n))) {
            return true;
        } else {
            for (k = 0; k + j < n && (board[i][k + j] != NO_CHARACTER); k++) {
                int currentColumn = j + k;
                if (memoization[i][currentColumn] == VERTICAL_FALSE) {
                    memoization[i][currentColumn] = HORIZONTAL_AND_VERTICAL_FALSE;
                } else {
                    memoization[i][currentColumn] = HORIZONTAL_FALSE;
                }
            }
            return false;
        }
    }

    public boolean canPlaceWordVertical(final char[][] board, final int i, final int j, final String word,
                                        final String reverseWord, final char[][] memoization) {
        boolean wordMatchStatus = true;
        boolean reverseWordMatchStatus = true;
        int m = board.length;
        int k;

        for (k = 0; k < word.length() && (wordMatchStatus || reverseWordMatchStatus)
                && k + i < m && board[k + i][j] != NO_CHARACTER; k++) {
            int currentRow = i + k;
            char currentCharacter = board[currentRow][j];

            if (Character.isLowerCase(currentCharacter)) {
                if (word.charAt(k) != currentCharacter) {
                    wordMatchStatus = false;
                }
                if (reverseWord.charAt(k) != currentCharacter) {
                    reverseWordMatchStatus = false;
                }
            }
        }

        if ((wordMatchStatus || reverseWordMatchStatus) && (k == word.length())
                && (((k + i) < m && board[k + i][j] == NO_CHARACTER) || (k + i == m))) {
            return true;
        } else {
            for (k = 0; k + i < m && (board[k + i][j] != NO_CHARACTER); k++) {
                int currentRow = k + i;
                if (memoization[currentRow][j] == HORIZONTAL_FALSE) {
                    memoization[currentRow][j] = HORIZONTAL_AND_VERTICAL_FALSE;
                } else {
                    memoization[currentRow][j] = VERTICAL_FALSE;
                }
            }
            return false;
        }
    }
}
