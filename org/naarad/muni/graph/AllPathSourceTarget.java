package org.naarad.muni.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/all-paths-from-source-to-target/">All Path from Source To Target</a>
 */
public class AllPathSourceTarget {
    public static void main(String[] args) {
        AllPathSourceTarget target = new AllPathSourceTarget();
        int[][] graph = new int[][]{{2}, {}, {1}};
        List<List<Integer>> output = target.allPathsSourceTarget(graph);
        for (:
             ) {

        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        final List<List<Integer>> output = new ArrayList<>();
        final List<Integer> visitedNodes = new ArrayList<>();
        visitedNodes.add(0);

        dfs(output, visitedNodes, graph, 0);

        return output;
    }

    private void dfs(List<List<Integer>> output, List<Integer> visitedNodes, int[][] graph, int i) {
        if (visitedNodes.get(visitedNodes.size() - 1) == graph.length - 1) {
            List<Integer> clonedList = new ArrayList<>(visitedNodes);
            output.add(clonedList);
        }

        for (int vertex : graph[i]) {
            visitedNodes.add(vertex);
            dfs(output, visitedNodes, graph, vertex);
            visitedNodes.remove(visitedNodes.size() - 1);
        }
    }
}
