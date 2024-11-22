package org.naarad.muni.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">Unique BST-2</a>
 * <p>
 * 1 <= n <= 8
 */

public class UniqueBST2 {
    public static void main(String[] args) {

        UniqueBST2 bst2 = new UniqueBST2();
        List<TreeNode> treeNodeList = bst2.generateTrees(3);

        for(TreeNode node : treeNodeList) {
            TreeNode.printInOrder(node);
            System.out.println();
        }
    }

    public List<TreeNode> generateTrees(int n) {
        final Map<String, List<TreeNode>> cache = new HashMap<>();
        return getRootNode(1, n, cache);
    }

    private List<TreeNode> getRootNode(int rangeStart, int rangeEnd, final Map<String, List<TreeNode>> cache) {
        String cacheKey = generateKeyFromRange(rangeStart, rangeEnd);

        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        final List<TreeNode> rootNodeList = new ArrayList<>();
        List<TreeNode> rightList;
        List<TreeNode> leftList;

        if (rangeStart == rangeEnd) {
            TreeNode root = new TreeNode(rangeStart);
            rootNodeList.add(root);
        } else {
            for (int i = rangeStart; i <= rangeEnd; i++) {
                if (i == rangeStart) {
                    for (TreeNode rightNode : getRootNode(i + 1, rangeEnd, cache)) {
                        TreeNode root = new TreeNode(i);
                        root.right = rightNode;
                        rootNodeList.add(root);
                    }
                } else if (i == rangeEnd) {
                    for (TreeNode leftNode : getRootNode(rangeStart, rangeEnd - 1, cache)) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode;
                        rootNodeList.add(root);
                    }
                } else {
                    leftList = getRootNode(rangeStart, i - 1, cache);
                    rightList = getRootNode(i + 1, rangeEnd, cache);

                    for (TreeNode leftNode : leftList) {
                        for (TreeNode rightNode : rightList) {
                            TreeNode root = new TreeNode(i);
                            root.left = leftNode;
                            root.right = rightNode;
                            rootNodeList.add(root);
                        }
                    }
                }
            }
        }

        cache.put(cacheKey, rootNodeList);
        return rootNodeList;
    }

    private String generateKeyFromRange(int rangeStart, int rangeEnd) {
        return rangeStart + "," + rangeEnd;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        public static void printInOrder(TreeNode root) {
            if (root != null) {
                System.out.print(root.val + ", ");
                printInOrder(root.left);
                printInOrder(root.right);
            } else {
                System.out.print("null" + ", ");
            }
        }
    }
}
