package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 11:39 上午
 * Description：二叉树的最大深度
 */
public class P_0104_TreeMaxDep {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return processor(root);
    }

    public int processor(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int r = processor(root.right);
        int l = processor(root.left);
        return r >= l ? r + 1 : l + 1;
    }

}
