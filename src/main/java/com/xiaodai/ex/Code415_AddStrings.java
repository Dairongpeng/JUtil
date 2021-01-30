package com.xiaodai.ex;

/**
 * Author ：dai
 * Date   ：2021/1/28 3:38 下午
 * Description：字符串大数相加
 */
public class Code415_AddStrings {

    public String addStrings(String num1, String num2) {
        // 结果也保存成字符串
        StringBuilder res = new StringBuilder("");
        // i和j模拟从两个字符串末尾开始，carry保存进位信息
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;

        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            // 当前两个对应位相加，加上上次的进位
            int tmp = n1 + n2 + carry;
            // 当前对应位相加后的进位情况，给下一个对应位相加使用
            carry = tmp / 10;
            // 拼上当前步骤的结果
            res.append(tmp % 10);
            // i和j向下一位移动
            i--;
            j--;
        }

        // 最后一位如果仍然存在进位，特殊处理
        if (carry == 1) res.append(1);
        return res.reverse().toString();
    }

}
