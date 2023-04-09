import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-substring-of-one-repeating-character/
 * 
 * At this point I am getting Time Limit exceeded, and only 47 out of 57
 * testcases
 * are passing, thus it is evident that we need to update the algorithm.
 * 
 */
public class LongestSubstringOfOneRepeatingCharacter {
    public static void main(String[] args) {
        LongestSubstringOfOneRepeatingCharacter obj = new LongestSubstringOfOneRepeatingCharacter();
        int a[] = obj.longestRepeating(
                "iiiiiccmmmmmmmmggg",
                "i", new int[] { 0 });

        for (int i : a) {
            System.out.print(i + ",");
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        List<Integer> longestLengths = new ArrayList<>();

        StringBuilder string = new StringBuilder(s);

        for (int i = 0; i < queryIndices.length; i++) {
            string.setCharAt(queryIndices[i], queryCharacters.charAt(i));
            longestLengths.add(getLengthOfLongestSubstringWithOneRepeatingCharacter(string.toString()));
        }

        return longestLengths.stream().mapToInt(Integer::intValue).toArray();
    }

    public int getLengthOfLongestSubstringWithOneRepeatingCharacter(String s) {
        if (s == null) {
            return 0;
        }
        int stringLength = s.length();

        if (stringLength == 1) {
            return 1;
        }

        int longestRepeatingLength = 1;
        char previousCharacter = s.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < stringLength; i++) {
            if (previousCharacter == s.charAt(i)) {
                currentLength++;
            } else {
                if (currentLength >= longestRepeatingLength) {
                    longestRepeatingLength = currentLength;
                }
                currentLength = 1;
            }
            previousCharacter = s.charAt(i);
        }

        if (currentLength > longestRepeatingLength) {
            longestRepeatingLength = currentLength;
        }
        return longestRepeatingLength;

    }
}

/*
 * 
 * "geuqjmt"
 * 
 * "bgemoegklm"
 * 
 * [3,4,2,6,5,6,5,4,3,2]
 * 
 * My output[] = [1,2,2,2,2,2,3,2,2,2]
 * Expected [] = [1,1,2,2,2,2,2,2,2,1]
 */