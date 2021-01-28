package com.xiaodai.top150;

import java.util.HashMap;

/**
 * Author ：dai
 * Date   ：2021/1/19 10:38 上午
 * Description：
 */
public class P_0001_TwoSum {

    // 数组中，找出目标和为target的两个位置的下标
    public static int[] twoSum(int[] nums, int target) {
        // key 某个之前的数   value 这个数出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { map.get(target - nums[i]), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }

}
