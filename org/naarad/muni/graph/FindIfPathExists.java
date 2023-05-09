package org.naarad.muni.graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/find-if-path-exists-in-graph/description/">Graph problem</a>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2 * 10<sup>5</sup> <br />
 * 0 <= edges.length <= 2 * 10<sup>5</sup>    <br />
 * edges[i].length == 2    <br />
 * 0 <= u<sub>i</sub>, v<sub>i</sub> <= n - 1    <br />
 * u<sub>i</sub> != v<sub>i</sub>      <br />
 * 0 <= source, destination <= n - 1      <br />
 * There are no duplicate edges.        <br />
 * There are no self edges.
 */
public class FindIfPathExists {
    public static void main(String[] args) {
        final FindIfPathExists pathExists = new FindIfPathExists();
        System.out.println(pathExists.validPath(1, new int[][]{}, 0, 0));
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }

        final boolean[] visitedNode = new boolean[n];
        final Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }

        final Stack<Integer> stackTrace = new Stack<>();
        stackTrace.add(source);

        while (!stackTrace.isEmpty()) {
            int currentNode = stackTrace.pop();

            if (graph.containsKey(currentNode)) {
                for (int node : graph.get(currentNode)) {
                    if (node == destination) {
                        return true;
                    } else if (!visitedNode[node]) {
                        stackTrace.add(node);
                    }
                }
            }
            visitedNode[currentNode] = true;
        }

        return false;
    }
}
