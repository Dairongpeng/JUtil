package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 4:36 下午
 * Description：打家劫舍；动态规划
 */
public class P_0198_HouseRobber {


    public int rob(int[] nums) {

        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {

            if(i == 0) {
                dp[0] = nums[i];
            }

            if(i == 1) {
                dp[1] = Math.max(dp[0], nums[i]);
            }

            if(i > 1) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }

        return dp[nums.length - 1];

    }

}
