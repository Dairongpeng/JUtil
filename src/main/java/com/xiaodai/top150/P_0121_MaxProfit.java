package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 10:49 上午
 * Description：买卖股票的最佳时机
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class P_0121_MaxProfit {

    public static int maxProfit(int[] prices) {
        // base case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 0...i 最小值。
        int min = prices[0];
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            // 当前最低点买入后，利润是否会比上个最低点买入利润更大，决定是否要更新最大利润
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

}
