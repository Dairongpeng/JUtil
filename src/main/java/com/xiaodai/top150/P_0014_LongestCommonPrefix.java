package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/20 5:35 下午
 * Description：最长公共前缀
 */
public class P_0014_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 拿出第一个字符串。当成初始值
        char[] chs = strs[0].toCharArray();
        // 所有字符串都匹配的最大长度，等同于每个字符串和初始字符串匹配的全局最小长度
        int min = Integer.MAX_VALUE;

        for (String str : strs) {

            char[] tmp = str.toCharArray();
            int index = 0;
            while (index < tmp.length && index < chs.length) {
                if (chs[index] != tmp[index]) {
                    break;
                }
                index++;
            }
            // 更新min
            min = Math.min(index, min);
            // 如果有任意一个字符串和初始串不匹配，直接返回""
            if (min == 0) {
                return "";
            }
        }
        // 截取min的长度，就是所有字符串共同匹配的最大长度
        return strs[0].substring(0, min);
    }

}
