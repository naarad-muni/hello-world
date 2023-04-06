/**
 * Leetcode program link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 */
import java.util.HashMap;
import java.util.Map;

class LongestSubstingWithoutRepeat {
    public static int lengthOfLongestSubstringWithoutRepeatingCharacter(String s) {
        int longestUniqueStringLength = 0;
        int n = s.length();
        int currentStartIndex = 0;

        if (n == 0) {
            return longestUniqueStringLength;
        }

        Map<Character, Integer> charToOccurrenceMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

           
            if (charToOccurrenceMap.containsKey(c)) {
                int currentBreakPointIndex = charToOccurrenceMap.get(c);
                int currentUniqueStringLength = i - currentStartIndex;
                longestUniqueStringLength = Math.max(longestUniqueStringLength, currentUniqueStringLength);

                for (int j = currentStartIndex; j <= currentBreakPointIndex; j++) {
                    charToOccurrenceMap.remove(s.charAt(j));
                }

                currentStartIndex = currentBreakPointIndex + 1;
                charToOccurrenceMap.put(c, i);
            } else 
                charToOccurrenceMap.put(c, i);
            
        }

        longestUniqueStringLength = Math.max(longestUniqueStringLength, (n - currentStartIndex));

        return longestUniqueStringLength;
    }

    public static void main(String[] args) {
        System.out.println(LongestSubstingWithoutRepeat.lengthOfLongestSubstringWithoutRepeatingCharacter("abcabcbb"));
    }  
}