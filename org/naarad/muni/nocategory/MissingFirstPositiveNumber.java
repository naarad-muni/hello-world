package org.naarad.muni.nocategory;

/**
 * <a href="https://leetcode.com/problems/first-missing-positive/">first missing positive number</a>
 */
public class MissingFirstPositiveNumber {

    public static void main(String[] args) {
        MissingFirstPositiveNumber missingFirstPositiveNumber = new MissingFirstPositiveNumber();
        System.out.println(
                missingFirstPositiveNumber.firstMissingPositive(new int[]{1, 2, 0}));
        // 0, 1, 5, -3, -2, -100, 6, 8, 2
        // 0, -1, -2
        // 3,4,-1,1
    }

    public int firstMissingPositive(int[] nums) {
        int newLength = pushAllNegativeNumbersTillEnd(nums);

        if (newLength < 0) {
            return 1; // all numbers in the array are less than 1
        }

        for (int i = 0; i <= newLength; i++) {
            int positiveValue = Math.abs(nums[i]) - 1;
            if (positiveValue <= newLength) {
                nums[positiveValue] = nums[positiveValue] < 0 ? nums[positiveValue] : nums[positiveValue] * -1;
            }
        }

        for (int i = 0; i <= newLength; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return newLength + 2;
    }

    private int pushAllNegativeNumbersTillEnd(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            while (i < nums.length && nums[i] >= 1) {
                i++;
            }

            while (j >= 0 && nums[j] < 1) {
                j--;
            }

            if (i < nums.length && j >= 0 && i < j && nums[i] < 1 && nums[j] >= 1) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        return i == j && nums[i] >= 1 ? i : i - 1;
    }

}
