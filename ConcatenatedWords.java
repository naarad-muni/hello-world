import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/concatenated-words/
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie root = new Trie();

        for (String string : words) {
            root.insert(string);
        }

        List<String> concatenatedWords = new ArrayList<>();

        for (String string : words) {
            if (root.isConcatenationPossible(string, root)) {
                concatenatedWords.add(string);
            }
        }
        return concatenatedWords;
    }

    class Trie {

        final Trie subChild[] = new Trie[26];
        boolean isEnd = false;

        public void insert(String word) {
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

        public boolean isConcatenationPossible(String word, Trie root) {
            boolean concatenationAvailable = false;
            int wordLength = word.length();
            Trie currentRoot = root;

            for (int i = 0; i < wordLength; i++) {
                Trie subChildReference = currentRoot.subChild[word.charAt(i) - 'a'];
                if (subChildReference != null) {
                    if (subChildReference.isEnd == true) {
                        if (isConcatenationPossible(word.substring(i + 1), root) == true) {
                            return true;
                        }
                    }
                }
            }

            return concatenationAvailable;
        }
    }
}
