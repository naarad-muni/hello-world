package org.naarad.muni.dynamic.prog;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/">problem statement link</a>
 * <br />
 * 1 <= n <= 2 * 10<sup>9</sup>
 */
public class MinimumDayToEatOranges {
    public static void main(String[] args) {
        MinimumDayToEatOranges a = new MinimumDayToEatOranges();
        System.out.println(a.minDays(61455274));
    }

    public int minDays(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2 || n == 3) {
            return 2;
        }

        final Map<Integer, Integer> orangesToMinimumDays = new HashMap<>();
        orangesToMinimumDays.put(0, 0);
        orangesToMinimumDays.put(1, 1);
        orangesToMinimumDays.put(2, 2);
        orangesToMinimumDays.put(3, 2);

        return calculateMinCost(n, orangesToMinimumDays);
    }

    public int calculateMinCost(int n, final Map<Integer, Integer> orangeToMinimumDay) {
        if (orangeToMinimumDay.containsKey(n)) {
            return orangeToMinimumDay.get(n);
        }

        int minimumDays = n;

        if (n % 3 == 0) {
            minimumDays = Math.min(minimumDays, 1 + calculateMinCost(n - (2 * (n / 3)), orangeToMinimumDay));
        }

        if (n % 2 == 0) {
            minimumDays = Math.min(minimumDays, 1 + calculateMinCost(n - (n / 2), orangeToMinimumDay));
        }

        minimumDays = Math.min(minimumDays, 1 + calculateMinCost(n - 1, orangeToMinimumDay));

        orangeToMinimumDay.put(n, minimumDays);

        return minimumDays;
    }
}
