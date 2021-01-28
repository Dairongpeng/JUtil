package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/19 11:24 上午
 * Description： 本题特别经典。求一个字符串的无重复字符，且最长的子串
 */
public class P_0003_LongestSubstringWithoutRepeatingCharacters {

    /**
     * 子串和子序列的区别，子串必须要连续，子序列不一定要连续。
     * 遇到子串和子序列的问题，可以按照一种经典思路：
     * 按照i位置结尾的情况下答案是什么？求所有可能的结尾即可，所有位置结尾的答案都求出，最大的就是我们的目标答案
     * 时间复杂度O(N)，空间复杂度O(1)，由于申请的空间是固定长度256
     */
    public static int lengthOfLongestSubstring(String s) {
        // base case 过滤无效参数
        if (s == null || s.equals("")) {
            return 0;
        }

        char[] str = s.toCharArray();
        int[] map = new int[256];
        // 辅助数组。保存字符出现的位置，字符的范围为可显示字符0~127，扩展ascii字符128~255
        for (int i = 0; i < 256; i++) {
            // 默认所有的字符都没出现过
            map[i] = -1;
        }
        // i位置往左推，推不动的位置第一个因素是再次遇到了i位置上的元素，第二个因素是i-1位置当初推了多远。
        // 这两个因素的限制，哪个限制位置离当前i位置近，就是当前字符i最远推到的位置，map[i]
        // 收集答案。len是收集全局的最大长度
        int len = 0;
        int pre = -1; // i-1位置结尾的情况下，往左推，推不动的位置是谁。用来每次保存i之前一个位置的答案
        int cur = 0;
        for (int i = 0; i != str.length; i++) {
            // i位置结尾的情况下，往左推，推不动的位置是谁
            // pre (i-1信息) 更新成 pre(i 结尾信息)
            // 上次推不动的，和当前字符上次出现的位置map[str[i]]的位置，取大的
            pre = Math.max(pre, map[str[i]]);
            // 找到了当前推不动的位置，当前不重复子串的长度就是i-pre
            cur = i - pre;
            // 全局最大的子串长度，是否被更新，决定是否要收集
            len = Math.max(len, cur);
            // 更新当前字符出现的位置是当前位置
            map[str[i]] = i;
        }
        return len;
    }

}
