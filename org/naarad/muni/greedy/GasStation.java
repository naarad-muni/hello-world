package org.naarad.muni.greedy;

/**
 * <a href="https://leetcode.com/problems/gas-station/description/">Gas Station</a>
 */
public class GasStation {
    public static void main(final String[] args) {
        final GasStation gas = new GasStation();
        System.out.println(gas.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    public int canCompleteCircuit(final int[] gas, final int[] cost) {
        int totalLeftOverGas = 0;

        for (int i = 0; i < gas.length; i++) {
            cost[i] = gas[i] - cost[i];
            totalLeftOverGas += cost[i];
        }

        if (totalLeftOverGas < 0) {
            return -1; // i.e., total gas is less than total cost
        }

        int start = 0;
        while (true) {
            int i = start;
            while (cost[i] < 0) {
                i = (i + 1) % gas.length;
            }
            start = i;

            int currentSum = cost[start];
            int end = (start + 1) % gas.length;
            while (end != start && currentSum >= 0) {
                currentSum += cost[end];
                end = (end + 1) % gas.length;
            }
            if (end == start) {
                return start;
            }
            // move start to the next negative index
            i = start + 1;
            while (cost[i] >= 0) {
                i = (i + 1) % gas.length;
            }
            start = i;
        }
    }
}
