package com.xiaodai.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author ：dai
 * Date   ：2021/2/2 4:18 下午
 * Description：二叉树按层遍历；锯齿打印。跟102题比较理解
 */
public class P_0103_BinaryTreeZigzagLevelOrderTraversal {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int size = 0;
        // 开关每次切换，打印顺序每次变化
        boolean isHead = true;
        while (!deque.isEmpty()) {
            size = deque.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = isHead ? deque.pollFirst() : deque.pollLast();
                curLevel.add(cur.val);
                if(isHead) {
                    if (cur.left != null) {
                        deque.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addLast(cur.right);
                    }
                }else {
                    if (cur.right != null) {
                        deque.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        deque.addFirst(cur.left);
                    }
                }
            }
            ans.add(curLevel);
            isHead = !isHead;
        }
        return ans;
    }


}
