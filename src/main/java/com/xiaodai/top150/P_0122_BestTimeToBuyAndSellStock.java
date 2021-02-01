package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 11:36 上午
 * Description：买卖股票的最佳时机2
 */
public class P_0122_BestTimeToBuyAndSellStock {


    public static int maxProfit(int[] prices) {
        // base case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 累计
        int ans = 0;
        // 贪心思想，只要后面一次比当前利润大，我们就可以加上该利润，等同于利润大于当前，卖出再买入
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
        }
        return ans;
    }


}
