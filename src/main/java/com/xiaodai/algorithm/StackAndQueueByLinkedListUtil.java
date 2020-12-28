package com.xiaodai.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Author ：dai
 * Date ：2020/12/25 2:56 下午
 * Description：队列和栈可以用双向列表，也可以用数组实现。该类通过双向链表实现
 */
public class StackAndQueueByLinkedListUtil {

    // 节点类型
    public static class Node<T> {
        public T value;
        // 向上指针
        public Node<T> last;
        // 向下指针
        public Node<T> next;


        public Node(T data) {
            this.value = data;
        }

    }


    /**
     * 自定义双向链表队列的实现，基于双向链表
     * @param <T>
     */
    public static class DoubleEndsQueue<T> {

        // 队列头
        public Node<T> head;
        // 队列尾
        public Node<T> tail;

        // 从头部加节点
        public void addFromHead(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        // 从尾部加节点
        public void addFromBottom(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        // 从头部弹出节点
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        // 从尾部弹出节点
        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        // 该双向链表结构是否为空
        public boolean isEmpty() {
            return head == null;
        }

    }


    /**
     * 自定义栈，基于上述双向队列类型实现
     *
     * @param <T>
     */
    public static class MyStack<T> {

        // 用队列作为我们栈的承载结构
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }


    /**
     * 自定义队列，基于上述双向队列类型实现。
     * @param <T>
     */
    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;

        for (int i = 0; i < testTimes; i++) {
            // 自定义的栈
            MyStack<Integer> myStack = new MyStack<>();
            // 自定义的队列
            MyQueue<Integer> myQueue = new MyQueue<>();
            // 系统栈
            Stack<Integer> stack = new Stack<>();
            // 系统队列
            Queue<Integer> queue = new LinkedList<>();

            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
