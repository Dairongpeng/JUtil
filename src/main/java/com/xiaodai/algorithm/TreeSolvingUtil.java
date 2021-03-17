package com.xiaodai.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
     * 1、判断二叉树是否是平衡的
     *
     * @param head
     * @return
     */
    public static boolean isBalanced(Node head) {

        return isBalancedProcess(head).isBalanced;

    }

    /**
     * 1. 递归调用，head传入整体需要返回一个信息
     * 2. 解决当前节点的Info信息怎么得来
     *
     * @param head
     * @return
     */
    private static IsBalancedInfo isBalancedProcess(Node head) {

        if (head == null) {
            return new IsBalancedInfo(true, 0);
        }

        IsBalancedInfo leftInfo = isBalancedProcess(head.left);

        IsBalancedInfo rightInfo = isBalancedProcess(head.right);

        // 当前节点的高度，等于左右树最大的高度，加上当前节点高度1
        int currentHeight = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isBalanced = true;

        // 左树不平衡，或者右树不平衡，或者左右树高度差大于1
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
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


    /**
     * 2、二叉树中，获取任意两个节点的最大距离
     *
     * @param head
     * @return
     */
    public static int maxDistance(Node head) {

        return maxDistanceProcess(head).maxDistance;

    }

    /**
     * 任意节点需要返回的信息体：以该节点为头的高度，整棵树的最大距离
     */
    public static class MaxDistanceInfo {
        public int maxDistance;
        public int height;

        public MaxDistanceInfo(int dis, int h) {
            this.maxDistance = dis;
            this.height = h;
        }
    }

    private static MaxDistanceInfo maxDistanceProcess(Node head) {

        if (head == null) {
            return new MaxDistanceInfo(0, 0);
        }

        MaxDistanceInfo leftInfo = maxDistanceProcess(head.left);

        MaxDistanceInfo rightInfo = maxDistanceProcess(head.right);

        // 当前节点为头的情况下，高度等于左右树较大的高度，加上1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        // 当前节点为头的情况下，最大距离等于，左右树距离较大的那个距离(与当前节点无关的情况)
        // 和左右树高度相加再加上当前节点距离1的距离(与当前节点有关的情况)取这两种情况较大的那个
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance)
                , (leftInfo.height + rightInfo.height + 1));

        return new MaxDistanceInfo(maxDistance, height);
    }


    /**
     * 3、判断一颗树是否是满二叉树
     *
     * @param head
     * @return
     */
    public static boolean isFull(Node head) {

        if (head == null) {
            return true;
        }

        IsFullInfo all = isFullProcess(head);

        // 递归拿到整棵树的头结点个数，根据满二叉树的公式，验证。（高度乘以2） - 1 = 节点个数
        return (1 << all.height) - 1 == all.nodes;
    }


    /**
     * 判断一棵树是否是满二叉树，每个节点需要返回的信息
     */
    public static class IsFullInfo {
        public int height;

        public int nodes;

        public IsFullInfo(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    private static IsFullInfo isFullProcess(Node head) {

        // base 空节点的高度为0，节点数量也0
        if(head == null) {
            return new IsFullInfo(0,0);
        }

        // 左树信息
        IsFullInfo leftInfo = isFullProcess(head.left);

        // 右树信息
        IsFullInfo rightInfo = isFullProcess(head.right);

        // 当前节点为头的树，高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        // 当前节点为头的树，节点数量
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;

        return new IsFullInfo(height, nodes);

    }

    /**
     * 4、找到二叉树中节点和等于sum的最长路径
     * @param head
     * @param sum
     * @return
     */
    public int getMaxLength(Node head, int sum) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0); // 重要
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    private int preOrder(Node head, int sum, int preSum, int level, int maxLen, Map<Integer, Integer> sumMap) {
        if(head == null) {
            return maxLen;
        }

        int curSum = preSum + head.value;
        if(!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }

        if(sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);

        if(level == sumMap.get(curSum)) {
            sumMap.remove(curSum);
        }

        return maxLen;
    }

    /**
     * 5、二叉树按层打印
     *
     *  last：表示正在打印的当前行的最右节点
     *  nLast：表示下一行的最右节点
     */
    public void printByLevel(Node head) {
        if(head == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node last = head;
        Node nLast = null;
        queue.offer(head);
        System.out.println("Level " + (level++) + " : ");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.println(head.value + " ");
            if(head.left != null) {
                queue.offer(head.left);
                nLast = head.left;
            }
            if(head.right != null) {
                queue.offer(head.right);
                nLast = head.right;
            }
            if(head == last && !queue.isEmpty()) {
                System.out.println("\nLevel " + (level++) + " ");
                last = nLast;
            }
        }
        System.out.println();
    }

    /**
     * 6、二叉树zigzag打印
     */

    /**
     * 7、二叉树，给定量给节点，求这两个节点的最近公共祖先
     */

    /**
     * 随机生成一颗二叉树
     *
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        // 层数不超过最大层数，概率0.5的创建每个树节点
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        // 最大层数
        int maxLevel = 5;
        // 每个节点的最大值范围
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            System.out.println(String.valueOf(head.value));
        }
    }

}
