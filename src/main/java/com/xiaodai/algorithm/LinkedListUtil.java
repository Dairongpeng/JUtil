package com.xiaodai.algorithm;

import com.xiaodai.top150.P_0021_MergeTowSortedLists;

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
            if (fast == null || fast.next == null) {
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
     * <p>
     * 例如：1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
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
            if (cur.value == num) { // 快指针cur向下滑动，如果值等于num，则暂时把下一个节点给慢指针的下一个指向。从而跳过等于num的节点
                pre.next = cur.next;
            } else { // cur此时到了不等于num的节点，则慢指针追赶上去。达到的效果就是等于num的节点都被删掉了
                pre = cur;
            }
            // 快指针向下滑动
            cur = cur.next;
        }
        return head;
    }

    /**
     * 4、打印两个有序链表的公共部分
     * 例如：head1: 1->2->3->3->4->5 head2: 0->0->1->2->3->3->7->9
     * 公共部分为：1 2 3 3
     *
     * @param head1
     * @param head2
     */
    public void printCommonPart(Node head1, Node head2) {

        System.out.println("Common Part: ");

        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    /**
     * 5、删除单链表的倒数第k个节点
     *
     * @param head
     * @param lastKth
     * @return
     */
    public Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        // cur指针也指向链表头节点
        Node cur = head;
        // 检查倒数第lastKth个节点的合法性
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }

        // 需要删除的是头结点
        if (lastKth == 0) {
            head = head.next;
        }

        if (lastKth < 0) {
            // cur回到头结点
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            // 次吃cur就是要删除的前一个节点。把原cur.next删除
            cur.next = cur.next.next;
        }

        // lastKth > 0的情况，表示倒数第lastKth节点比原链表程度要大，即不存在
        return head;
    }

    /**
     * 6、删除链表中间节点
     * 思路：如果链表为空或者只有一个节点，不做处理。链表两个节点删除第一个节点，链表三个节点，删除中间第二个节点，链表四个节点，删除上中点
     *
     * @param head
     * @return
     */
    public Node removeMidNode(Node head) {
        // 无节点，或者只有一个节点的情况，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 链表两个节点，删除第一个节点
        if (head.next.next == null) {
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;

        // 快慢指针
        if (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }

        // 快指针走到尽头，慢指针奇数长度停留在中点，偶数长度停留在上中点。删除该节点
        pre.next = pre.next.next;

        return head;
    }

    /**
     * 7、给定一个链表，如果成环，返回成环的那个节点
     * <p>
     * 思路：
     * 1. 快慢指针fast和slow，开始时，fast和slow都指向头节点，fast每次走两步，slow每次走一步
     * 2. 快指针向下移动的过程中，如果提前到达null，则链表无环，提前结束
     * 3. 如果该链表成环，那么fast和slow一定在环中的某个位置相遇
     * 4. 相遇后，立刻让fast回到head头结点，slow不动，fast走两步改为每次走一步。fast和slow共同向下滑动，再次相遇，就是成环节点
     *
     * @param head
     * @return
     */
    public Node getLoopNode(Node head) {
        // 节点数目不足以成环，返回不存在成环节点
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node n1 = head.next; // slow指针
        Node n2 = head.next.next; // fast指针

        while (n1 != n2) {
            // 快指针提前到达终点，该链表无环
            if (n2.next == null || n2.next.next == null) {
                return null;
            }

            n2 = n2.next.next;
            n1 = n1.next;
        }

        // 确定成环，n2回到头节点
        n2 = head;

        while (n1 != n2) {
            n2 = n2.next;
            n1 = n1.next;
        }

        // 再次相遇节点，就是成环节点
        return n1;
    }

    /**
     * 由于单链表，两个链表相交要不然两个无环链表相交，最后是公共部分；要不然两个链表相交，最后是成环部分
     * <p>
     * 8、判断两个无环链表是否相交，相交则返回相交的第一个节点
     * <p>
     * 思路：
     * 1. 链表1从头结点遍历，统计长度，和最后节点end1
     * 2. 链表2从头结点遍历，统计长度，和最后节点end2
     * 3. 如果end1不等一end2则一定不相交，如果相等则相交，算长度差，长的链表遍历到长度差的长度位置，两个链表就汇合在该位置
     *
     * @param head1
     * @param head2
     * @return
     */
    public Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;

        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        // 最终没汇聚，说明两个链表不相交
        if(cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? cur1 : cur2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);

        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;

    }

    /**
     * 9、合并两个有序链表
     * @param head1
     * @param head2
     * @return
     */
    public Node mergeTwoList(Node head1, Node head2) {
        // base case
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        // 选出两个链表较小的头作为整个合并后的头结点
        Node head = head1.value <= head2.value ? head1 : head2;
        // 链表1的准备合并的节点，就是头结点的下一个节点
        Node cur1 = head.next;
        // 链表2的准备合并的节点，就是另一个链表的头结点
        Node cur2 = head == head1 ? head2 : head1;

        // 最终要返回的头结点，预存为head，使用引用拷贝的pre向下移动
        Node pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
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
