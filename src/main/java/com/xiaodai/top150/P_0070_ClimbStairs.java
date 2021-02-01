package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 10:58 上午
 * Description：爬楼梯问题。暴力递归到动态规划
 */
public class P_0070_ClimbStairs {

    public int climbStairs(int n) {

        if(n == 0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        if(n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }


}
