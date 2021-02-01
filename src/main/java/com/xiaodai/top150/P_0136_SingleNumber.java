package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 2:50 下午
 * Description：只出现一次的数字
 */
public class P_0136_SingleNumber {

    /**
     * 全部异或
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        return eor;
    }

}
