package com.xiaodai.ex;

import java.util.Stack;

/**
 * Author ：dai
 * Date   ：2021/1/26 5:25 下午
 * Description：括号配对问题
 */
public class Code01_Parentheses {


    /**
     * 不使用栈判断一个字符串是否是括号配对正确。
     * 遍历该字符串，遇到左括号count++，遇到右括号count--。在整个过程中，1、如果count小于0，直接返回无效 2、整个过程结束count如果正好等于0，表示有效，否则无效
     *
     * @param s
     * @return
     */
    public boolean isMatchNotUseStack(String s) {

        if (s.length() % 2 == 1) // base case 奇数个字符的字符串 显然无法完成括号匹配
            return false;

        char[] str = s.toCharArray();

        int count = 0;

        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }

        return count == 0;
    }


    /**
     * 使用栈判断一个字符串是否是括号配对正确。遇到左括号入栈，右括号弹出。该方法可以检测多个不同括号的场景
     *
     * @param s
     * @return
     */
    public boolean isMatchUseStack(String s) {

        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {

            char theChar = str[i];
            if (theChar == '(' || theChar == '{' || theChar == '[') {
                stack.push(str[i]);
                //  右括号的场景，屏蔽掉其他非括号字符
            } else if (theChar == ')' || theChar == '}' || theChar == ']') {

                // 栈中已无左括号，此时字符为右括号，无法匹配。
                if (stack.empty()) {
                    return false;
                }

                // 此时为右括号，看栈顶元素
                char preChar = stack.peek();
                if ((preChar == '{' && theChar == '}') || (preChar == '(' && theChar == ')') || (preChar == '[' && theChar == ']')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        // 全部字符判断完，检查此时栈是否刚好为空、为空则匹配
        return stack.empty();

    }

}
