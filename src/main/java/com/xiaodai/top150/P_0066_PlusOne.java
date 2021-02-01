package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 2:26 下午
 * Description：数组表示的一个整数，加1，继续返回一个加1后的数用数组表示
 */
public class P_0066_PlusOne {


    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        // 从后往前加，如果小于9直接在当前位置加1返回数组
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // 如果等于9，那么当前位加1后变为0。循环继续向下。下一个位置不需要进位的时候加1后直接返回，逻辑上没问题
            digits[i] = 0;
        }

        // 能够跳出循环，表示的是全9的情况。需要补一个长度，设置为1
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
