package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date ：2020/12/30 2:56 下午
 * Description：解决二叉树的具体问题，递归思维的建立
 */
public class TreeSolvingUtil {

    /**
     * 节点信息
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    /**
     * 判断二叉树是否是平衡的
     *
     * @param head
     * @return
     */
    public static boolean isBalanced(Node head) {

        return isBalancedProcess(head).isBalanced;

    }

    /**
     * 1、递归调用，head传入整体需要返回一个信息
     * 2、解决当前节点的Info信息怎么得来
     * @param head
     * @return
     */
    private static IsBalancedInfo isBalancedProcess(Node head) {

        if(head == null) {
            return new IsBalancedInfo(true, 0);
        }

        IsBalancedInfo leftInfo = isBalancedProcess(head.left);

        IsBalancedInfo rightInfo = isBalancedProcess(head.right);

        // 当前节点的高度，等于左右树最大的高度，加上当前节点高度1
        int currentHeight = Math.max(leftInfo.height , rightInfo.height) + 1;

        boolean isBalanced = true;

        // 左树不平衡，或者右树不平衡，或者左右树高度差大于1
        if(!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }

        return new IsBalancedInfo(isBalanced, currentHeight);
    }

    /**
     * 递归过程中需要整合的信息体
     */
    public static class IsBalancedInfo {

        // 是否平衡
        boolean isBalanced;

        // 高度多少
        int height;

        IsBalancedInfo(boolean b, int height) {
            this.isBalanced = b;
            this.height = height;
        }
    }





}
