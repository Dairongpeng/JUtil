package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date   ：2020/12/25 5:04 下午
 * Description：
 */
public class LinkedListUtil {


    /**
     * 链表的节点，可实现成泛型
     */
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    /**
     * 双向列表的节点结构，可实现成泛型
     */
    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }


    /**
     * 1、检测链表是否成环。返回成环是否，第一次相遇并不保证是成环的节点
     *
     * @param head
     * @return
     */
    public boolean hasCycle(Node head) {

        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head.next;

        while (slow != fast) {
            if(fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        // 有环的话一定追的上，但不一定是第一次成环的节点
        return true;
    }





    /**
     * 2、传入头节点，翻转单项链表
     *
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 3、移除链表中等于值的节点
     *
     * @param head
     * @param num
     * @return
     */
    public static Node removeValue(Node head, int num) {

        // 从链表的头开始，舍弃掉开头的且连续的等于num的节点
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        // head来到 第一个不需要删的位置
        Node pre = head;
        Node cur = head;

        // 快慢指针
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {

        int len = 10;
        int value = 100;
        // 随机生成一个链表
        Node head = generateRandomLinkedList(len, value);

//        while (head != null) {
//            System.out.println(head.value);
//            head = head.next;
//        }

        System.out.println("翻转后===");
        Node reverseHead = reverseLinkedList(head);

        while (reverseHead != null) {
            System.out.println(reverseHead.value);
            reverseHead = reverseHead.next;
        }

    }

    /**
     * 随机生成一个链表
     *
     * @param len
     * @param value
     * @return
     */
    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

}
