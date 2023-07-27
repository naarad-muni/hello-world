package org.naarad.muni.graph;

public class SmallestEquivalentString {
    public static void main(String[] args) {
        SmallestEquivalentString s = new SmallestEquivalentString();
        System.out.println(s.smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        char[] lexicographyParent = new char[26];
        final char LOWERCASE_A = 'a';

        for (int i = 0; i < lexicographyParent.length; i++) {
            lexicographyParent[i] = (char) (LOWERCASE_A + i);
        }

        for (int i = 0; i < s1.length(); i++) {
            char characterToReplace = (char) Math.max(lexicographyParent[s1.charAt(i) - LOWERCASE_A],
                    lexicographyParent[s2.charAt(i) - LOWERCASE_A]);
            char replacementCharacter = (char) Math.min(lexicographyParent[s1.charAt(i) - LOWERCASE_A],
                    lexicographyParent[s2.charAt(i) - LOWERCASE_A]);

            if (lexicographyParent[characterToReplace - LOWERCASE_A] !=
                    lexicographyParent[replacementCharacter - LOWERCASE_A]) {
                lexicographyParent[characterToReplace - LOWERCASE_A] =
                        lexicographyParent[replacementCharacter - LOWERCASE_A];
                updateAllOccurrence(lexicographyParent, characterToReplace,
                        lexicographyParent[replacementCharacter - LOWERCASE_A]);
            }
        }

        StringBuilder lexicographicallySmallestString = new StringBuilder();

        for (int i = 0; i < baseStr.length(); i++) {
            lexicographicallySmallestString.append(lexicographyParent[baseStr.charAt(i) - LOWERCASE_A]);
        }

        return lexicographicallySmallestString.toString();
    }

    private void updateAllOccurrence(final char[] lexicographyParent, final char characterToReplace,
                                     final char replacementCharacter) {
        for (int i = 0; i < lexicographyParent.length; i++) {
            if (lexicographyParent[i] == characterToReplace) {
                lexicographyParent[i] = replacementCharacter;
            }
        }
    }
}
