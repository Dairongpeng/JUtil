package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/30 3:14 下午
 * Description：最长回文子串问题
 */
public class P_0005_LongestPalindromicSubstring {



    // 解法一：暴力解法O(N*N*N)时间超时

    /**
     * 解法二： 扩散法。时间复杂度为O(N * N)
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {

        if(s.length() == 0) {
            return s;
        }

        // 全局最大回文长度
        int res = 1;
        // 全局最大回文长度对应的左位置
        int ll = 0;
        // 全局最大回文长度对应的右位置
        int rr = 0;


        for (int i = 0; i < s.length(); i++) {

            // 以i为下标的奇数情况，是否有更大的len来更新res
            int l = i - 1;
            int r = i + 1;

            // l和r都在合法范围。且l和r位置字符相等，可以继续扩散
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                int len = (r - l + 1);
                // 更新最长回文串的长度
                if(len > res) {
                    res = len;
                    ll = l;
                    rr = r;
                }
                // 扩散
                l--;
                r++;
            }

            // 以i为下标偶数的情况。是否有更大的len来更新全局res
            l = i;
            r = i + 1;
            // l和r都在合法范围。且l和r位置字符相等，可以继续扩散
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                int len = (r - l + 1);
                // 更新最长回文串的长度
                if(len > res) {
                    res = len;
                    ll = l;
                    rr = r;
                }
                // 扩散
                l--;
                r++;
            }

        }

        return s.substring(ll, rr + 1);

    }





    /**
     * 解法三：马拉车算法。时间复杂度为O(N)
     * @param str
     * @return
     */
    public static String longestPalindrome3(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        int mid = 0;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (max < pArr[i]) {
                max = pArr[i];
                mid = i;
            }
        }
        mid = (mid - 1) / 2;
        max = max - 1;
        return str.substring((max & 1) == 0 ? mid - (max / 2) + 1 : mid - (max / 2), mid + (max / 2) + 1);
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

}
