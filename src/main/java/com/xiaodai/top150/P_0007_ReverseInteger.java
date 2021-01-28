package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/19 2:57 下午
 * Description：
 */
public class P_0007_ReverseInteger {

    public static int reverse(int x) {
        // x是否为负数。X无符号右移31，来到符号位，跟1做与运算
        boolean neg = ((x >>> 31) & 1) == 1;
        // 是负数保持不变，不是负数转为相应的负数。由于负数的绝对值域比正数的绝对值域大1，转成负数更安全
        x = neg ? x : -x;
        // m等于系统最小除10，o等于系统最小模10。用来检测溢出
        int m = Integer.MIN_VALUE / 10;
        int o = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            // 如果res已经小于系统最小除10，那么res后续乘以10一定是系统越界。
            // 如果res等于系统最小除10，res成10之后加上x%10，如果x%10比系统最小模10还小，那么会溢出
            // 两种溢出情况，返回0
            if (res < m || (res == m && x % 10 < o)) {
                return 0;
            }
            // res乘10，再加上x % 10。
            res = res * 10 + x % 10;
            // x缩小一位
            x /= 10;
        }
        // x原来等于负数，取结果本身，x原来为正数，取结果的绝对值
        return neg ? res : Math.abs(res);
    }

}
