package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 3:46 下午
 * Description：最长递增子序列。动态规划；重要
 */
public class P_0300_LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        // 全局最大
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            // 默认每个元素的dp[i]都为1，表示自己形成的递增子序列
            dp[i] = 1;


            for (int j = 0; j < i; j++) {
                // 如果在当前位置的前面，存在一个比自己小的元素，该元素的dp[j]加上当前元素形成的新的dp[j] + 1比dp[i]大。更新这个dp[i]。否则不更新
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 最上层循环，每一轮检查是否需要更新全局max
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
