package org.naarad.muni.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a leetcode graph practise question, involving disjoint set.
 **/
public class FindProvinces {
    int[] root;

    public void initialiseRoot(final int length) {
        root = new int[length];

        for (int i = 0; i < length; i++) {
            root[i] = i;
        }
    }

    public void union(int i, int j) {
        int previousRoot = root[j];
        root[j] = root[i];

        for (int k = 0; k < root.length; k++) {
            if (root[k] == previousRoot) {
                root[k] = root[i];
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int length = isConnected[0].length;
        initialiseRoot(length);

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        Map<Integer, Boolean> provincesTracker = new HashMap<>();

        for (int i = 0; i < root.length; i++) {
            provincesTracker.put(root[i], true);
        }

        return provincesTracker.size();
    }

    public static void main(String[] args) {
        FindProvinces provinces = new FindProvinces();
        System.out.println(
                provinces.findCircleNum(new int[][]{
                        {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                        {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}
                }));
    }
}
