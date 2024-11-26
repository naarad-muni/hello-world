package org.naarad.muni.tree;

/**
 * Constraints:
 * <p>
 * - The number of nodes in the tree is in the range [1, 3 * 104].
 * - (-1000 <= Node.val <= 1000)
 */
public class BinaryTreeMaxPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(-1, new TreeNode(5), new TreeNode(3)), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        BinaryTreeMaxPathSum a = new BinaryTreeMaxPathSum();
        System.out.println(a.maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        return recurse(root).max;
    }

    private Result recurse(TreeNode r) {
        if (r == null) {
            return null;
        }
        Result lD = recurse(r.left);
        Result rD = recurse(r.right);
        Result res = new Result();

        if (lD != null && rD != null) {
            res.max = Math.max(lD.max, Math.max(rD.max, Math.max(rD.maxPath + lD.maxPath + r.val, Math.max(lD.maxPath + r.val, Math.max(rD.maxPath + r.val, r.val)))));
            res.maxPath = Math.max(lD.maxPath + r.val, Math.max(rD.maxPath + r.val, r.val));
        } else if (lD != null) {
            res.max = Math.max(lD.max, Math.max(lD.maxPath + r.val, r.val));
            res.maxPath = Math.max(lD.maxPath + r.val, r.val);
        } else if (rD != null) {
            res.max = Math.max(rD.max, Math.max(rD.maxPath + r.val, r.val));
            res.maxPath = Math.max(rD.maxPath + r.val, r.val);
        } else {
            res.max = r.val;
            res.maxPath = r.val;
        }

        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private class Result {
        int max;
        int maxPath;
    }

}
