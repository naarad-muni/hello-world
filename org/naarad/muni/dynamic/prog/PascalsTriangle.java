package org.naarad.muni.dynamic.prog;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/pascals-triangle/">[LC] Pascals Triangle</a>
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        final PascalsTriangle triangle = new PascalsTriangle();
        final List<List<Integer>> totalLists = triangle.generate(10);
        for (List<Integer> list : totalLists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> generate(int numRows) {
        final List<List<Integer>> output = new ArrayList<>();
        output.add(List.of(1));

        for (int i = 1; i < numRows; i++) {
            final List<Integer> lastList = output.get(output.size()-1);
            final List<Integer> currentList = new ArrayList<>();
            currentList.add(1);

            for (int j = 0; j < i-1; j++) {
                currentList.add(lastList.get(j) + lastList.get(j+1));
            }

            currentList.add(1);
            output.add(currentList);
        }

        return output;
    }
}
