package org.naarad.muni.tree;

/**
 * <a href="https://leetcode.com/problems/unique-binary-search-trees">Unique BST</a>
 * <p>
 * 1 <= n <= 19
 */

public class UniqueBinarySearchTree {
    public static void main(String[] args) {
        UniqueBinarySearchTree tree = new UniqueBinarySearchTree();
        System.out.println(tree.numTrees(6));
    }

    public int numTrees(int n) {
        if ( n <= 2) {
            return n;
        }

        int[] memoization = new int[n + 1];
        // base values
        memoization[1] = 1;
        memoization[2] = 2;

        for (int i = 3; i <= n; i++) {
            memoization[i] = calculateUniqueBST(i, memoization);
        }

        return memoization[n];
    }

    private int calculateUniqueBST(int n, int[] memoization) {
        int total = memoization[n - 1] * 2;
        int left = 1;
        int right = n - 2;

        for (int i = 2; i <= n / 2; i++) {
            total += memoization[left] * memoization[right] * 2;
            left++;
            right--;
        }

        if (n % 2 != 0) {
            total += memoization[left] * memoization[right];
        }
        return total;
    }
}
