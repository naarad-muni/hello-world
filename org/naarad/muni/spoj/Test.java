package org.naarad.muni.spoj;

import java.util.Scanner;

/**
 * <a href="https://www.spoj.com/problems/TEST/">SPOJ TEST</a>
 */
public class Test {
    public static void main(String[] args) throws java.lang.Exception {
        final Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        while (num != 42) {
            System.out.println(num);
            num = scanner.nextInt();
        }

        scanner.close();
    }
}
