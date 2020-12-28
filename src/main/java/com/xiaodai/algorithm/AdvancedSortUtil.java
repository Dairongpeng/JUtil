package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date ：2020/12/25 2:56 下午
 * Description：高级排序：归并排序和快速排序
 */
public class AdvancedSortUtil {

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);

        process(arr, L, mid);
        process(arr, mid + 1, R);

        merge(arr, L, mid, R);
    }

    /**
     * 两个有序数组合并
     *
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    private static void merge(int[] arr, int L, int M, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];

        // help填充过程中的下标，从0开始
        int i = 0;
        // 左有序数组指针
        int p1 = L;
        // 右有序数组指针
        int p2 = M + 1;

        /**
         * p1和p2都没越界
         */
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1] : arr[p2];
        }

        /**
         * 上述第一层while循环后，要么左数组越界，要么右数组越界，接下来把剩下的，没跑完的半边数组，直接填充
         */
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }


        /**
         * 把整体merge后的数组，拷贝到原始数组中去
         */
        for (int j = 0; j < help.length; j++) {
            arr[L + i] = help[i];
        }

    }




}
