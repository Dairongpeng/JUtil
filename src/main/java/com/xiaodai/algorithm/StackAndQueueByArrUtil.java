package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date ：2020/12/25 2:56 下午
 * Description：自定义栈和队列，基于数组实现
 */
public class StackAndQueueByArrUtil {

    /**
     * 自定义栈，基于数组实现
     */
    public static class MyStack {

        private int[] stack;

        private int index = -1;

        private int limit;

        // 构造栈大小，int类型初始默认都是0
        public MyStack(int limit) {
            stack = new int[limit];
            this.limit = limit;
        }

        public void push(int value) {
            if(index >= limit) {
                System.out.println("栈满了");
                return;
            }
            stack[++index] = value;
        }

        public int pop() {
            if(index < 0) {
                throw new RuntimeException("非法下标");
            }
            return stack[index--];
        }

    }


    /**
     * 自定义队列，基于数组实现，使用技巧达到环形数组的效果
     */
    public static class MyQueue {

        private int[] queue;

        // 往当前队列添加数的下标位置
        private int pushi;
        // 当前队列需要出队列的位置
        private int polli;
        // 当前队列使用的空间大小
        private int size;
        // 数组最大大小，用户传入
        private final int limit;

        public MyQueue(int limit) {
            queue = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("栈满了，不能再加了");
            }
            size++;
            queue[pushi] = value;
            // 对于虚拟的环，求下一个位置
            pushi = nextIndex(pushi);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("栈空了，不能再拿了");
            }
            size--;
            int ans = queue[polli];
            // 对于虚拟的环，求下一个位置
            polli = nextIndex(polli);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置，该实现可以实现环形的ringbuffer
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }


    public static void main(String[] args) {
        MyStack stack = new MyStack(10);
        stack.push(1);
        stack.push(3);
        stack.push(5);


        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
