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


    /**
     * 随机快排，不随机的快排复杂度不固定
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processByQuickSort(arr, 0, arr.length - 1);
    }

    private static void processByQuickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机选择位置，与arr[R]上的数做交换
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        // 整体进行荷兰国旗划分，返回中间相等区域的左右边界，每次荷兰国旗问题，都可以搞定一批位置
        int[] equalArea = netherlandsFlag(arr, L, R);
        processByQuickSort(arr, L, equalArea[0] - 1);
        processByQuickSort(arr, equalArea[1] + 1, R);
    }

    //  小于arr[R]放左侧  等于arr[R]放中间  大于arr[R]放右边
    //  返回中间区域的左右边界
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        // 不存在荷兰国旗问题
        if (L > R) {
            return new int[] { -1, -1 };
        }
        // 已经都是等于区域，由于用R做划分返回R位置
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // 小于区域的右边界
        int more = R;     // 大于区域的左边界
        int index = L;
        while (index < more) {
            // 当前值等于右边界，不做处理，index++
            if (arr[index] == arr[R]) {
                index++;
                // 小于交换当前值和左边界的值
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
                // 大于右边界的值
            } else {
                swap(arr, index, --more);
            }
        }
        // 比较完之后，把R位置的数，调整到等于区域的右边，至此大于区域才是真正意义上的大于区域
        swap(arr, more, R);
        return new int[] { less + 1, more };
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int[] arr) {

        if(arr == null || arr.length < 2) {
            return;
        }


        /**
         * 把数组构建成大根堆,经过这层循环，堆顶元素是最大值元素
         */
        for (int i = arr.length -1; i >= 0; i--) {
            HeapUtil.heapify(arr, i, arr.length);
        }


        int heapSize = arr.length;
        /**
         * 堆顶元素和数组最右侧交换，最大的放最右
         */
        swap(arr, 0, --heapSize);

        /**
         * 最大值已经在最右后，从头开始对每个位置进行下沉操作
         */
        while (heapSize > 0) {
            // 此时的堆大小，已经在上一步的基础上减1了，在新的堆大小上进行堆调整
            HeapUtil.heapify(arr, 0, heapSize);
            // 每次调整后，堆大小减小1，在新的堆大小（堆大小>0）上继续调整，直至全部调整完毕，整个数组从小到大有序
            swap(arr, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 6, 3, 5, 2, 7, 2, 4, 8, 3};

        heapSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
