package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/19 10:56 上午
 * Description：链表结构两数相加
 */
public class P_0002_AddTwoNumbers {


    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }

    /**
     * 对于长短不一的链表，1->9->2 和2->1 相加得到 3->0->3
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 谁长度不够，谁补0后继续相加
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            // 初始化头结点，头结点等于两链表头部相加进位后留下的值
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                // 相加的结果考虑进位后当前留下的结果，追加到结果指针的尾部
                tail.next = new ListNode(sum % 10);
                // 结果指针向下移动
                tail = tail.next;
            }
            // 当前是否有进位，保存供下个节点使用
            carry = sum / 10;
            // 两个链表分别往下移动
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 最后一位，存在进位，补充到尾部指针
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

}
