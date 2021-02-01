package com.xiaodai.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author ：dai
 * Date   ：2021/2/1 11:42 上午
 * Description：二叉树按层遍历（重点）
 */
public class P_0102_BinaryTreeLevelOrderTraversal {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 结果。多少层是一个list，每一层的节点仍是个List
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }


        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int size = 0;
        while(!deque.isEmpty()) {
            // 当前层的节点个数，要弹出size个。
            size = deque.size();
            List<Integer> curLevel = new ArrayList<>();
            for(int i = 0 ; i< size;i++) {
                TreeNode cur = deque.pollLast();
                curLevel.add(cur.val);
                // 当前层的节点不管有左孩子还是右孩子，都从队列头部加入。先加入左后加入右，都是下一层的节点
                if(cur.left != null) {
                    deque.addFirst(cur.left);
                }
                if(cur.right != null) {
                    deque.addFirst(cur.right);
                }
            }
            // 这一层全部收集到curLevel里面，放入ans中
            ans.add(curLevel);
        }
        return ans;
    }

}
