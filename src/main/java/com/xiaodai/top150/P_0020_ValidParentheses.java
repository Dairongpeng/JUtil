package com.xiaodai.top150;

import java.util.Stack;

/**
 * Author ：dai
 * Date   ：2021/1/30 2:43 下午
 * Description：有效的括号
 */
public class P_0020_ValidParentheses {


    /**
     * 输入一个字符串，包含'(','[','{',')',']','}'几种括号，求是否是括号匹配的结果。
     * @param s
     * @return
     */
    public static boolean isValid(String s) {

        if (s == null || s.length() == 0) {
            return true;
        }

        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {
            char cha = str[i];
            // 遇到左括号，添加相应的右括号
            if (cha == '(' || cha == '[' || cha == '{') {
                stack.add(cha == '(' ? ')' : (cha == '[' ? ']' : '}'));
            } else { // 遇到右括号，弹出栈，比对相等
                if (stack.isEmpty()) {
                    return false;
                }
                char last = stack.pop();
                if (cha != last) {
                    return false;
                }
            }
        }

        // 遍历结束，栈刚好为空。满足匹配要求
        return stack.isEmpty();
    }

}
