package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/26 4:33 下午
 * Description：合并两个有序链表
 */
public class P_0021_MergeTowSortedLists {


    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // base case
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        // 选出两个链表较小的头作为整个合并后的头结点
        ListNode head = l1.val <= l2.val ? l1 : l2;
        // 链表1的准备合并的节点，就是头结点的下一个节点
        ListNode cur1 = head.next;
        // 链表2的准备合并的节点，就是另一个链表的头结点
        ListNode cur2 = head == l1 ? l2 : l1;

        // 最终要返回的头结点，预存为head，使用引用拷贝的pre向下移动
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                // 向下滑动
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                // 向下滑动
                cur2 = cur2.next;
            }
            // pre向下滑动
            pre = pre.next;
        }

        // 有一个链表耗尽了，没耗尽的链表直接拼上
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }


}
