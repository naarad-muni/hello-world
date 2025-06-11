package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/counting-bits/">[LC] Counting Bits</a>
 */

public class CountingBits {
    public static void main(String[] args) {
        int[] a = countBits(0);
        for (int b : a) {
            System.out.print(b + ", ");
        }
    }

    public static int[] countBits(int n) {
        if (n == 0) {
            return new int[]{0};
        }

        int[] numberOfOnes = new int[n + 1];

        numberOfOnes[0] = 0;
        numberOfOnes[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                numberOfOnes[i] = numberOfOnes[i / 2];
            } else {
                numberOfOnes[i] = numberOfOnes[i / 2] + 1;
            }
        }
        return numberOfOnes;
    }
}
