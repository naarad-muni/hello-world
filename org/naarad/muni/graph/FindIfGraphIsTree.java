package org.naarad.muni.graph;

/**
 * TODO: Solution is incomplete
 * <p>
 * Leetcode practise question for graph, to find out if given graph makes a valid tree or not.
 * <p>
 * You have a graph of n nodes labeled from 0 to n - 1.
 * You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates
 * that there is an undirected edge between nodes ai and bi in the graph.
 * <p>
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 * <p>
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * <p>
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 * <p>
 * <h3>Constraints:</h3>
 * <p>
 * 1 <= n <= 2000         <br/>
 * 0 <= edges.length <= 5000       <br/>
 * edges[i].length == 2   <br/>
 * 0 <= ai, bi < n        <br/>
 * a<sub>i</sub> != b<sub>i</sub>        <br/>
 * There are no self-loops or repeated edges.
 */
public class FindIfGraphIsTree {

    int[] root;

    public void initialise(int length) {
        root = new int[length];
        for (int i = 0; i < length; i++) {
            root[i] = i;
        }
    }

    public void union(int i, int j) {
        root[j] = root[i];
    }

    public boolean validTree(int n, int[][] edges) {

        for (int i = 0; i < edges.length; i++) {
            union(edges[i][0], edges[i][1]);
        }

        return false;
    }

    public static void main(String[] args) {
        FindIfGraphIsTree graph = new FindIfGraphIsTree();

        System.out.println(graph.validTree(3, new int[][]{{1, 2}, {0, 1}}));
    }
}
