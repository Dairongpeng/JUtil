package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/3/4 10:23 上午
 * Description：
 */
public class P_0038_CountAndSay {

    public static String countAndSay(int n) {
        // base case
        if (n < 1) {
            return "";
        }
        // 递归出口
        if (n == 1) {
            return "1";
        }
        // 求当前，需要拿到上一个字符序列。比如上一个字符序列为：111221。
        // 那么当前所需要返回的序列为312211
        char[] last = countAndSay(n - 1).toCharArray();
        // 拼最终结果
        StringBuilder ans = new StringBuilder();
        // 字符频率
        int times = 1;
        // 循环上一个序列，计算频率
        for (int i = 1; i < last.length; i++) {
            // 下一个和当前相等，频率加1
            if (last[i - 1] == last[i]) {
                times++;
            } else {
                // 频率
                ans.append(String.valueOf(times));
                // 拼上当前的数字。例如频率为3，数字为1，拼出来的结果就是31
                ans.append(String.valueOf(last[i - 1]));
                // 重置频率，方便下一个不同字符使用
                times = 1;
            }
        }
        // 最后一个字符的频率
        ans.append(String.valueOf(times));
        // 拼上最后一个字符
        ans.append(String.valueOf(last[last.length - 1]));
        return ans.toString();
    }

}
