package org.naarad.muni.dynamic.prog;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/word-break/description/">word break problem</a>
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wB = new WordBreak();
        System.out.println(wB.wordBreak("leetcode", List.of("leet", "code")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        final int stringLen = s.length();
        boolean[] dp = new boolean[stringLen];
        final Map<Character, Node> wordMap = new HashMap<>();

        buildWordMapFromDictionary(wordMap, wordDict);

        if (!wordMap.containsKey(s.charAt(0))) {
            return false;
        } else {
            boolean canStart = false;
            for (int length : wordMap.get(s.charAt(0)).getWordLengths()) {
                int endIndex = length - 1;
                if (endIndex < stringLen &&
                        wordMap.get(s.charAt(0)).getWordSet().contains(s.substring(0, length))) {
                    canStart = true;
                    dp[endIndex] = true;
                }
            }

            if (!canStart) {
                return false;
            }
        }

        for (int i = 1; i < stringLen; i++) {
            final char currentCharacter = s.charAt(i);

            if (dp[i - 1] && wordMap.containsKey(currentCharacter)) {
                for (int wordLength : wordMap.get(currentCharacter).getWordLengths()) {
                    final int endIndexOfWord = i + wordLength - 1;
                    if (endIndexOfWord < stringLen && !dp[endIndexOfWord] &&
                            wordMap.get(currentCharacter).getWordSet().contains(s.substring(i, endIndexOfWord + 1))) {
                        dp[endIndexOfWord] = true;
                    }
                }
            }
        }

        return dp[stringLen - 1];
    }

    private void buildWordMapFromDictionary(final Map<Character, Node> wordMap, final List<String> wordDict) {
        for (String word : wordDict) {
            final char firstCharacter = word.charAt(0);

            if (!wordMap.containsKey(firstCharacter)) {
                wordMap.put(firstCharacter, new Node());
            }

            wordMap.get(firstCharacter).addWord(word);
        }
    }

    protected class Node {
        private Set<Integer> wordLengths;
        private Set<String> wordSet;

        Node() {
            wordLengths = new HashSet<>();
            wordSet = new HashSet<>();
        }

        public void addWord(String word) {
            if (word == null || word.length() == 0) {
                return;
            }

            wordLengths.add(word.length());
            wordSet.add(word);
        }

        public Set<Integer> getWordLengths() {
            return this.wordLengths;
        }

        public Set<String> getWordSet() {
            return this.wordSet;
        }
    }
}
