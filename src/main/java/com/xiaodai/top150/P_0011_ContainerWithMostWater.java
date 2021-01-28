package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/19 4:05 下午
 * Description：盛最多水的容器
 */
public class P_0011_ContainerWithMostWater {

    public static int maxArea(int[] h) {
        int max = 0;
        int l = 0;
        int r = h.length - 1;
        while (l < r) {
            // 上个位置的max和当前双指针矮杆乘以长度的当前容量，取最大值进行结算。也就是确定要不要更新max
            max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
            // 小的高度的那边，缩小范围。
            if (h[l] > h[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }

}
