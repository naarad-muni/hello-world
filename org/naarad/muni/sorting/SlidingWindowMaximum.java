package org.naarad.muni.sorting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        SlidingWindowMaximum max = new SlidingWindowMaximum();

        System.out.println(Arrays.toString(max.maxSlidingWindow(new int[]{1, -1}, 1)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] output = new int[nums.length - k + 1];
        final Deque<Node> integerDeque = new ArrayDeque<>();

        integerDeque.add(new Node(nums[0], 0));
        for (int i = 1; i < k; i++) {
            while (!integerDeque.isEmpty() && integerDeque.peekLast().getData() <= nums[i]) {
                integerDeque.removeLast();
            }
            integerDeque.addLast(new Node(nums[i], i));
        }

        int j = 0;
        output[j++] = integerDeque.peekFirst().getData();

        for (int i = k; i < nums.length; i++) {
            while (!integerDeque.isEmpty() && integerDeque.peekLast().getData() <= nums[i]) {
                integerDeque.removeLast();
            }

            integerDeque.addLast(new Node(nums[i], i));

            if (i - k == integerDeque.peekFirst().getIndex()) {
                integerDeque.removeFirst();
            }

            output[j++] = integerDeque.peekFirst().getData();
        }

        return output;
    }

    protected class Node {
        int data;
        int index;

        Node(int val, int index) {
            data = val;
            this.index = index;
        }

        public int getData() {
            return data;
        }

        public int getIndex() {
            return index;
        }
    }
}
