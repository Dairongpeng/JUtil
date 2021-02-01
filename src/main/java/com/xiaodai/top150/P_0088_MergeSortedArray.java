package com.xiaodai.top150;

/**
 * Author ：dai
 * Date   ：2021/2/1 2:31 下午
 * Description：合并两个有序数组
 */
public class P_0088_MergeSortedArray {


    /**
     * 时间和空间复杂度O(M+N)解法
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] ints = new int[ m + n ];
        int p = 0,q = 0; // 开辟两个新指针
        int i = 0; //新数组的指针
        while (p < m && q < n){
            ints[i++] = nums1[p] < nums2[q] ? nums1[p++] : nums2[q++];
        }
        // 某一个指针到达末尾，拷贝剩余的全部
        if (p != m){
            System.arraycopy(nums1, p, ints, i, m - p);//拷贝剩余的全部
            System.arraycopy(ints, 0, nums1, 0, m+n);//最后将新数组拷贝到nums1数组中
        }
        if (q != n){
            System.arraycopy(nums2, q, ints, i , n - q);//拷贝剩余的全部
            System.arraycopy(ints, 0, nums1, 0, m+n);//最后将新数组拷贝到nums1数组中
        }

    }



    /**
     * 逆向双指针解法，时间复杂度O(M+N),空间复杂度为O(1)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[--index] = nums1[--m];
            } else {
                nums1[--index] = nums2[--n];
            }
        }
        while (m > 0) {
            nums1[--index] = nums1[--m];
        }
        while (n > 0) {
            nums1[--index] = nums2[--n];
        }
    }

}
