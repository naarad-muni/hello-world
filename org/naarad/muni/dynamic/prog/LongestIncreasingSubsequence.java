package org.naarad.muni.dynamic.prog;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence increasing = new LongestIncreasingSubsequence();
        System.out.println(increasing.lengthOfLIS(new int[]{-55, 1}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int[] evenRow = new int[nums.length];
        int[] oddRow = new int[nums.length];

        int maxSubsequence = 1;
        for (int i = 0; i < nums.length; i++) {
            evenRow[i] = nums[i] > nums[0] ? 2 : 1;
            maxSubsequence = Math.max(maxSubsequence, evenRow[i]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i % 2 == 0) {
                    // we need to fill evenRow
                    evenRow[j] = nums[j] > nums[i] && oddRow[i] + 1 > oddRow[j] ? oddRow[i] + 1 : oddRow[j];
                    maxSubsequence = Math.max(maxSubsequence, evenRow[j]);
                } else {
                    // we need to fill odd Row
                    oddRow[j] = nums[j] > nums[i] && evenRow[i] + 1 > evenRow[j] ? evenRow[i] + 1 : evenRow[j];
                    maxSubsequence = Math.max(maxSubsequence, oddRow[j]);
                }
            }
        }

        return maxSubsequence;
    }
}
