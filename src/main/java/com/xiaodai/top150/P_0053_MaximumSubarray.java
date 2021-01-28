package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/26 5:59 下午
 * Description：经典：最大子序和
 */
public class P_0053_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        // dp[i] 含义：子数组必须以i结尾的时候，所有可以得到的子数组中，最大累加和是多少？
        int[] dp = new int[N];
        dp[0] = nums[0];
        // 记录全局最大的子数组的和
        int max = dp[0];
        for (int i = 1; i < N; i++) {
            // 当前的值
            int p1 = nums[i];
            // 当前的值和上一个位置的最大和累加
            int p2 = nums[i] + dp[i - 1];
            // dp[i]等于，当前的值，和当前值与上一个位置最大和的累加，取大的
            dp[i] = Math.max(p1, p2);
            // 判断是否要更新全局最大值
            max = Math.max(max, dp[i]);
        }
        // 返回全局最大值
        return max;
    }

}
