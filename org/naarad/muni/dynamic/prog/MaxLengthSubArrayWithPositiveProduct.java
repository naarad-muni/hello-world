package org.naarad.muni.dynamic.prog;

import java.util.Arrays;

/**
 * <a href=" https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product">Problem Statement link</a>/
 */

public class MaxLengthSubArrayWithPositiveProduct {
    public static void main(String[] args) {
        MaxLengthSubArrayWithPositiveProduct a = new MaxLengthSubArrayWithPositiveProduct();
        System.out.println(a.getMaxLen(new int[]{-1, 2}));
    }

    public int getMaxLen(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            while (i < nums.length && nums[i] == 0) {
                i++;
            }

            if (i < nums.length) {
                int subArrayStart = i;
                while (i < nums.length && nums[i] != 0) {
                    i++;
                }

                maxLength = Math.max(calculateMaxPositiveProductLength(Arrays.copyOfRange(nums, subArrayStart, i)),
                        maxLength);
            }
        }

        return maxLength;
    }

    /**
     * Input array to this method will not have any zero's
     *
     * @return
     */
    public int calculateMaxPositiveProductLength(int[] nums) {
        if (nums.length == 1) {
            if (nums[0] < 0) {
                return 0;
            } else {
                return 1;
            }
        }

        int maxLength = 0;
        int firstNegativeIndex, lastNegativeIndex;

        int calculateTotalNegativeElements = 0;
        firstNegativeIndex = lastNegativeIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                calculateTotalNegativeElements++;

                if (lastNegativeIndex == -1) {
                    firstNegativeIndex = lastNegativeIndex = i;
                } else {
                    lastNegativeIndex = i;
                }
            }
        }

        if (calculateTotalNegativeElements % 2 == 0) {
            /* if the count of negative elements is even or zero, then we need to return the entire array length */
            maxLength = nums.length;
        } else {
            if (calculateTotalNegativeElements == 1) {
                maxLength = Math.max(firstNegativeIndex, nums.length - lastNegativeIndex - 1);
            } else {
                int maxOfLeftOrRightSubArray = Math.max(firstNegativeIndex + 1, nums.length - lastNegativeIndex);
                int lengthInBetween =
                        (firstNegativeIndex == lastNegativeIndex ? 0 : lastNegativeIndex - firstNegativeIndex - 1);

                maxLength = lengthInBetween + maxOfLeftOrRightSubArray;
            }
        }
        return maxLength;
    }
}
