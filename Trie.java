/**
 * <a href="https://leetcode.com/problems/implement-trie-prefix-tree/">Prefix tree</a>
 */

public class Trie {

    final Trie subChild[] = new Trie[26];
    boolean isEnd = false;

    public Trie() {
    }

    public static void main(final String[] args) {
        final Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // return True
        System.out.println(trie.search("app")); // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app")); // return True
    }

    public void insert(final String word) {
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

    public boolean search(final String word) {
        final int wordLength = word.length();
        Trie currentRoot = this;

        if (word == null || wordLength == 0) {
            return false;
        }

        for (int i = 0; i < wordLength; i++) {
            if (currentRoot.subChild[word.charAt(i) - 'a'] == null) {
                return false;
            }

            currentRoot = currentRoot.subChild[word.charAt(i) - 'a'];
        }

        if (currentRoot.isEnd == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(final String prefix) {
        final int prefixLength = prefix.length();
        Trie currentRoot = this;

        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        for (int i = 0; i < prefixLength; i++) {
            if (currentRoot.subChild[prefix.charAt(i) - 'a'] == null) {
                return false;
            }

            currentRoot = currentRoot.subChild[prefix.charAt(i) - 'a'];
        }

        return true;
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */