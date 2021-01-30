package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/30 4:03 下午
 * Description：删除链表的倒数第N个节点
 */
public class P_0019_RemoveNthNodeFromEndOfList {


    public static class ListNode {
        public int val;
        public ListNode next;
    }

    /**
     * 传入链表头节点；删除倒数第n个节点。双指针法，让快指针先走N步骤，之后慢指针和快指针共同向下滑动。快指针来到尾结点，慢指针来到倒数第N个节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode pre = null;
        // cur是快指针，pre是慢指针。cur先走n步。cur为null的时候跳出循环
        while (cur != null) {
            n--;
            if (n == -1) {
                pre = head;
            }
            if (n < -1) {
                pre = pre.next;
            }
            cur = cur.next;
        }

        // 跳出循环后
        // 1、如果n大于0，说明此时链表总长度不到n，直接返回头结点；
        // 2、n == 0，此时pre是null，需要删除pre下一个理论节点，也就是原链表头结点。返回新的头部为head.next
        // 3、正常情况，需要删除pre节点的下一个节点，那么pre.next = pre.next.next;
        if (n > 0) {
            return head;
        }
        if (pre == null) {
            return head.next;
        }
        pre.next = pre.next.next;
        return head;
    }

}
