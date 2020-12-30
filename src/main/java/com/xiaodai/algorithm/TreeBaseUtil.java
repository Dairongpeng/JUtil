package com.xiaodai.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Author ：dai
 * Date ：2020/12/25 2:56 下午
 * Description：二叉树基本结构和算法
 */
public class TreeBaseUtil {

    /**
     * 二叉树节点定义
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            this.value = v;
        }
    }


    /**
     * 递归先序遍历
     *
     * @param head
     */
    public static void pre(Node head) {

        if (head == null) {
            return;
        }

        System.out.println(head.value);

        pre(head.left);

        pre(head.right);

    }

    /**
     * 递归中序遍历
     *
     * @param head
     */
    public static void mid(Node head) {
        if (head == null) {
            return;
        }

        mid(head.left);

        System.out.println(head.value);

        mid(head.right);
    }

    /**
     * 递归后续遍历
     *
     * @param head
     */
    public static void pos(Node head) {
        if (head == null) {
            return;
        }

        pos(head.left);

        pos(head.right);

        System.out.println(head.value);
    }

    /**
     * 非递归先序
     *
     * @param head
     */
    public static void NotRRre(Node head) {

        System.out.print("pre-order: ");

        if (head != null) {
            // 借助栈结构，手动压栈
            Stack<Node> stack = new Stack<>();
            stack.add(head);

            while (!stack.isEmpty()) {
                // 弹出就打印
                head = stack.pop();
                System.out.println(head.value);

                // 右孩子不为空，先压入右孩子。右孩子就会后弹出
                if (head.right != null) {
                    stack.push(head.right);
                }

                // 左孩子不为空，压入左孩子，左孩子在右孩子之后压栈，先弹出
                if (head.left != null) {
                    stack.push(head.left);
                }
            }

        }

    }

    /**
     * 非递归中序
     *
     * @param head
     */
    public static void NotRMid(Node head) {

        System.out.print("mid-order: ");

        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {

                // 整条左边界以此入栈
                if (head != null) {
                    stack.push(head);
                    // head滑到自己的左孩子，左孩子有可能为空，但空的节点不会加入栈，下一个分支会判空处理
                    head = head.left;
                    // 左边界到头弹出一个打印，来到该节点右节点，再把该节点的左树以此进栈
                } else {// head为空的情况，栈顶是上次头结点的现场，head等于栈顶，回到上一个现场。打印后，head往右树上滑动
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }

    }


    /**
     * 非递归后序，借助两个栈，比借助一个栈容易理解
     *
     * @param head
     */
    public static void NotRPos(Node head) {
        System.out.print("pos-order: ");

        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            // 辅助栈
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }


    /**
     * 非递归后序,仅借助一个栈，比较有技巧
     *
     * @param head
     */
    public static void NotRPos2(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }


    /**
     * 按层遍历，即宽度优先遍历
     *
     * @param head
     */
    public static void level(Node head) {

        if (head == null) {
            return;
        }

        // 借助队列
        Queue<Node> queue = new LinkedList<>();

        queue.add(head);

        while (!queue.isEmpty()) {

            Node cur = queue.poll();
            // 打印当前
            System.out.println(cur.value);

            // 当前节点有左孩子，加入左孩子进队列
            if (cur.left != null) {
                queue.add(cur.left);
            }

            // 当前节点有右孩子，加入右孩子进队列
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

    }


    /**
     * 二叉树先序序列化；除了先序，中序，后续，按层都可，但是序列化和反序列化规则要对应
     * @param head
     * @return
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();

        pres(head, ans);

        return ans;

    }

    private static void pres(Node head, Queue<String> ans) {

        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }


    /**
     * 根据先序序列化的结构，还原树
     *
     * @param prelist
     * @return
     */
    public static Node buildByPreQueue(Queue<String> prelist) {

        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);
    }

    public static Node preb(Queue<String> prelist) {
        String value = prelist.poll();
        // 如果头节点是空的话，返回空
        if (value == null) {
            return null;
        }
        // 否则根据第一个值构建先序的头结点
        Node head = new Node(Integer.valueOf(value));
        // 递归建立左树
        head.left = preb(prelist);
        // 递归建立右树
        head.right = preb(prelist);
        return head;
    }


    /**
     * 直观打印一颗二叉树
     *
     * @param head
     */
    public static void printTree(Node head) {

        System.out.println("Binary Tree:");

        printInOrder(head, 0, "H", 17);

    }

    /**
     * 打印方法
     *
     * @param head 表示当前传入节点
     * @param height 当前节点的高度
     * @param to 表示当前节点的指向信息
     * @param len 表示打印当前值，需要填充到多少位当成一个完整的值
     */
    private static void printInOrder(Node head, int height, String to, int len) {

        if(head == null) {
            return;
        }

        // 递归右树，右树向下指
        printInOrder(head.right, height + 1, "v", len);

        // 打印时机，val表示需要打印的内容，实际需要进行填充之后再打印
        String val = to + head.value + to;

        // 没填充前的值长度
        int lenM = val.length();
        // 按照len算出值的左侧需要填充多少空格
        int lenL = (len - lenM) / 2;
        // 按照len算该值右侧需要填充多少空格
        int lenR = len - lenM - lenL;

        val = getSpace(lenL) + val + getSpace(lenR);

        System.out.println(getSpace(height * len) + val);

        printInOrder(head.left, height + 1, "^", len);

    }

    /**
     * 填充空格
     *
     * @param num
     * @return
     */
    private static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

}
