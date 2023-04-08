import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {

    public static void main(String[] args) {
        String words[] = new String[] { "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat",
                "ratcatdogcat" };

        ConcatenatedWords sol = new ConcatenatedWords();
        List<String> concatenatedStrings = sol.findAllConcatenatedWordsInADict(words);

        for (String string : concatenatedStrings) {
            System.out.println(string);
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie root = new Trie();

        for (String string : words) {
            root.insert(string);
        }

        List<String> concatenatedWords = new ArrayList<>();

        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (int i = (words.length - 1); i >= 0; i--) {
            if (root.isConcatenationPossible(words[i], root, true)) {
                concatenatedWords.add(words[i]);
            }
        }

        return concatenatedWords;
    }

    class Trie {

        final Trie subChild[] = new Trie[26];
        boolean isEnd = false;
        final Set<String> wordSet = new HashSet<>();

        public Trie() {
            wordSet.clear();
        }

        public void insert(String word) {
            wordSet.add(word);
            final int wordLength = word.length();
            Trie currentRoot = this;

            if (word == null || wordLength == 0) {
                return;
            }

            for (int i = 0; i < wordLength; i++) {
                if (currentRoot.subChild[word.charAt(i) - 'a'] == null) {
                    currentRoot.subChild[word.charAt(i) - 'a'] = new Trie();
                }

                currentRoot = currentRoot.subChild[word.charAt(i) - 'a'];
            }

            currentRoot.isEnd = true;
        }

        public boolean isConcatenationPossible(String word, Trie root, boolean initialRecursion) {

            int wordLength = word.length();
            Trie currentRoot = root;

            for (int i = 0; i < wordLength; i++) {
                Trie subChildReference = currentRoot.subChild[word.charAt(i) - 'a'];

                if (subChildReference != null) {
                    if (subChildReference.isEnd == true) {
                        if (i + 1 == wordLength) {
                            if (initialRecursion) {
                                return false;
                            } else {
                                return true;
                            }
                        } else if (wordSet.contains(word.substring(i + 1))) {
                            return true;
                        } else if (isConcatenationPossible(word.substring(i + 1), root, false)) {
                            wordSet.add(word.substring(i + 1));
                            return true;
                        }
                    }
                    currentRoot = subChildReference;
                } else

                {
                    return false;
                }
            }

            return false;
        }
    }
}
