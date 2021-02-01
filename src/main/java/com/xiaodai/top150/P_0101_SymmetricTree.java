package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 2:45 下午
 * Description：对称二叉树
 */
public class P_0101_SymmetricTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        // 自身，和自身的镜像树去递归比较
        return isMirror(root, root);
    }

    // 一棵树是原始树  head1
    // 另一棵是翻面树  head2
    public static boolean isMirror(TreeNode head1, TreeNode head2) {
        // base case 当前镜像的节点都为空，也算合法的镜像
        if (head1 == null && head2 == null) {
            return true;
        }
        // 互为镜像的两个点不为空
        if (head1 != null && head2 != null) {
            // 当前两个镜像点要是相等的，
            // A树的左树和B树的右树互为镜像且满足，且A树的右树和B树的左树互为镜像，且满足。
            // 那么当前的镜像点下面的都是满足的
            return head1.val == head2.val
                    && isMirror(head1.left, head2.right)
                    && isMirror(head1.right, head2.left);
        }
        // 一个为空，一个不为空 肯定不构成镜像  false
        return false;
    }


}
