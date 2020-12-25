package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date   ：2020/12/25 3:52 下午
 * Description：
 */
public class BinarySearch {

    /**
     * 二分查找，存在返回true
     *
     * @param arr
     * @param value
     * @return
     */
    public static boolean searchGoalIsContains(int[] arr, int value) {

        if(arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;

        while (L <= R) {
            mid = L + ((R - L) / 2);
            if(arr[mid] == value) {
                return true;
            } else if(arr[mid] > value) {
                R = mid - 1;
            } else if(arr[mid] < value) {
                L = mid + 1;
            }
        }

        return false;
    }

    /**
     * 二分查找，返回大于等于目标值value的最左侧位置
     *
     * @param arr
     * @param value
     * @return
     */
    public static int searchMoreThenGoalIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;

        while (L <= R) {
            int mid = L + ((R - L) / 2);
            if(arr[mid] < value) {
                L = mid + 1;
            } else {
                index = mid;
                R = mid - 1;
            }
        }

        return index;
    }

    /**
     * 二分查找，返回小于等于目标值的最右侧位置
     * @param arr
     * @param value
     * @return
     */
    public static int searchLessThenGoalIndex(int[] arr, int value) {

        int L = 0;
        int R = arr.length - 1;
        int index = -1; // 记录最右的位置
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return index;
    }

}
