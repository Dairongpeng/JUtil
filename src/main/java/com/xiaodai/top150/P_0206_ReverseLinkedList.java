package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/30 5:40 下午
 * Description：翻转链表
 */
public class P_0206_ReverseLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode next = null;

        while (head != null) {
            // 下一个位置暂存
            next = head.next;

            head.next = pre;
            // pre要向下走一步
            pre = head;
            // head要往下走一步
            head = next;
        }

        return pre;
    }

}
