package org.naarad.muni.nocategory;

/**
 * <a href="https://leetcode.com/problems/maximize-the-topmost-element-after-k-moves/">MAX TOPMOST</a>
 */
public class MaximumTop {

    public static void main(final String[] args) {
        final MaximumTop top = new MaximumTop();
        System.out.println(top.maximumTop(new int[]{91, 98, 17, 79, 15}, 2));
    }

    public int maximumTop(final int[] nums, final int k) {
        if (nums.length == 1) {
            if (k % 2 == 1) {
                return -1;
            } else {
                return nums[0];
            }
        }

        int maximum = nums[0];

        if (nums.length < k) {
            for (int i = 1; i < nums.length; i++) {
                if (maximum < nums[i]) maximum = nums[i];
            }

            return maximum;
        }

        int maxIndex = 0;
        int secondMaxIndex = 0;

        int i = 1;
        for (; i < k; i++) {
            if (nums[maxIndex] < nums[i]) {
                secondMaxIndex = maxIndex;
                maxIndex = i;
            } else if (nums[secondMaxIndex] < nums[i]) {
                secondMaxIndex = i;
            }
        }

        if (maxIndex == k - 1) {
            if (k < nums.length) {
                return maxIndex == secondMaxIndex ? nums[k] : Math.max(nums[secondMaxIndex], nums[k]);
            } else {
                return nums[secondMaxIndex];
            }
        } else {
            if (k < nums.length) {
                return Math.max(nums[maxIndex], nums[k]);
            } else {
                return nums[maxIndex];
            }
        }
    }
}
