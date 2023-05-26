package org.naarad.muni.nocategory;

public class PeakElementIndex {
    public static void main(String[] args) {

    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int first = 0;
        if (nums[first] > nums[1]) {
            return first;
        }

        int last = nums.length - 1;
        if (nums[last] > nums[last - 1]) {
            return last;
        }

        int mid = nums.length / 2;

        while (first < last) {
            if (nums[mid] < nums[first]) {

            }
        }

        return -1;
    }
}
