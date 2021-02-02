package com.xiaodai.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Author ：dai
 * Date   ：2021/2/1 3:05 下午
 * Description：回文链表问题。空间复杂度为O(1)的解法会破坏原有链表结构
 */
public class P_0234_PalindromeLinkedList {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    /**
     * 时间复杂度为O(N)，空间复杂度O(N)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

}
