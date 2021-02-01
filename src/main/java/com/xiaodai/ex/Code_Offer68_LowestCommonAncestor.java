package com.xiaodai.ex;


/**
 * Author ：dai
 * Date   ：2021/1/30 1:42 下午
 * Description：二叉树最近公共祖先
 */
public class Code_Offer68_LowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 以root为头的节点，求任意p和q节点的最近公共祖先
     *
     * @param root 根节点
     * @param p p节点
     * @param q q节点
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || p == root || q == root) { // 如果树为空，直接返回null； 如果 p和q中有等于 root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
            return root;
        }


        TreeNode left = lowestCommonAncestor(root.left, p, q); // 递归遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
        TreeNode right = lowestCommonAncestor(root.right, p, q); // 递归遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁


        // left和 right均不为空时，说明 p、q节点分别在 root异侧, 最近公共祖先即为 root
        if (left != null && right != null) {
            return root;
        }

        // 如果在左子树中 p和 q都找不到，则 p和 q一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        // 否则，如果 left不为空，在左子树中有找到节点（p或q），这时候要再判断一下右子树中的情况，如果在右子树中，p和q都找不到，则 p和q一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        return left == null ? right : left;
    }


}
