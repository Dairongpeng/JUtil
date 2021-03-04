package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/3/4 10:14 上午
 * Description：
 */
public class P_0283_MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int to = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, to++, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
