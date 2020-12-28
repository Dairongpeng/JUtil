package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date   ：2020/12/25 5:04 下午
 * Description：
 */
public class LinkedListUtil {

    /**
     * 传入头节点，翻转单项链表
     * @param head
     * @return
     */
//    public static Node reverseLinkedList(Node head) {
//
//    }

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


}
