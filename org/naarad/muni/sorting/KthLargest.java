package org.naarad.muni.sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">Kth Largest element</a>
 */
public class KthLargest {

    public static void main(final String[] args) {
        final KthLargest find = new KthLargest();
        System.out.println(find.findKthLargest(new int[]{4, 10, 8, 2, 5}, 2));
    }

    /**
     * This method uses PriorityQueue
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(final int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.reverseOrder());

        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
        }

        while (k > 1) {
            pq.poll();
            k--;
        }

        return pq.poll();
    }

    /**
     * This method uses traditional Quick Select algorithm, and it produces a runtime of
     * 2500 ms in Leetcode, to optimise the runtime, people have suggested to use PriorityQueue.
     * Submission link using below method:
     * https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/1135443092
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestUsingQuicksort(final int[] nums, int k) {
        k = nums.length - k;
        int currentFixedIndex = k;
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex <= endIndex) {
            currentFixedIndex = getAFixedIndex(nums, startIndex, endIndex, k);
            if (currentFixedIndex == k) {
                return nums[currentFixedIndex];
            } else if (currentFixedIndex > k) {
                endIndex = currentFixedIndex - 1;
            } else {
                startIndex = currentFixedIndex + 1;
            }
        }

        return -1;
    }

    private int getAFixedIndex(final int[] numbers, final int startIndex, final int endIndex, final int k) {
        final int randomPivot = (int) (Math.random() * (endIndex - startIndex)) + startIndex;
        swap(numbers, randomPivot, endIndex);

        int i = startIndex - 1;
        int j = startIndex;

        while (j < endIndex) {
            if (numbers[j] < numbers[endIndex]) {
                swap(numbers, i + 1, j);
                i++;
            }
            j++;
        }
        swap(numbers, i + 1, endIndex);

        return i + 1;
    }

    private void swap(final int[] numbers, final int firstIndex, final int secondIndex) {
        final int temp = numbers[firstIndex];
        numbers[firstIndex] = numbers[secondIndex];
        numbers[secondIndex] = temp;
    }

}
