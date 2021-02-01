package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/30 5:42 下午
 * Description：删除排序数组中的重复项
 */
public class P_0026_RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        // 数组在不停滑动，只有下一个位置和当前i位置不相等的时候，保存到i位置，相等的时候i不向下扩展
        for (int j = 1; j < nums.length; j++) {
            if(nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


}
