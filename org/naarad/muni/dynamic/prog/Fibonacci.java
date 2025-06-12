package org.naarad.muni.dynamic.prog;

/**
 * <a href="https://leetcode.com/problems/fibonacci-number/">Fibonacci number</a>
 */
public class Fibonacci {
    public static void main(String[] args) {
        final Fibonacci fib = new Fibonacci();
        System.out.println(fib.fib(0));
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }

        int[] fibonacci = new int[n + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci[n];
    }
}
