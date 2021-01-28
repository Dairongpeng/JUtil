package com.xiaodai;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author ：dai
 * Date   ：2021/1/14 9:38 下午
 * Description：
 */
public class Test {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        for (int i = 0, j = list.size() - 1; i < list.size() && j >= i; i++, j--) {
            if(!list.get(i).equals(list.get(j))) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(2);
        node.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(node));
    }

}
