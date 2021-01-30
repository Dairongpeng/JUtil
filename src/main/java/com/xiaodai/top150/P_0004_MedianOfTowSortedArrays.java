package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/1/30 5:11 下午
 * Description：求两个有序数组的中位数
 */
public class P_0004_MedianOfTowSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;
        int[] sum = new int[n + m];

        int index = 0;
        int i = 0;
        int j = 0;
        // merge两个有序数组
        while(i < n && j < m) {
            sum[index++] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }

        while(i < n) {
            sum[index++] = nums1[i++];
        }

        while(j < m) {
            sum[index++] = nums2[j++];
        }


        if((m + n) % 2 == 0) { // 偶数时候，上中点和下中点的平均数
            return (sum[(m + n) / 2 - 1] + sum[(m + n) / 2]) / 2.0;
        } else { // 奇数，中点的值
            return sum[(m + n) / 2];
        }

    }

}
