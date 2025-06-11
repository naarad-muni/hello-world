package org.naarad.muni.dynamic.prog;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/pascals-triangle-ii/">[LC] Pascals Triangle 2</a>
 */
public class PascalsTriangle2 {
    public static void main(String[] args) {
        final PascalsTriangle2 triangle2 = new PascalsTriangle2();
        System.out.println(triangle2.getRow(1));
    }

    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        List<Integer> previousList = List.of(1);

        for (int i = 1; i < rowIndex; i++) {
            final List<Integer> currentList = new ArrayList<>();
            currentList.add(1);

            for (int j = 0; j < i-1; j++) {
                currentList.add(previousList.get(j) + previousList.get(j+1));
            }

            currentList.add(1);
            previousList = currentList;
        }

        return previousList;
    }
}
