package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date   ：2020/12/28 9:09 下午
 * Description：自定义堆结构，用数组实现，以大根堆为例
 */
public class HeapUtil {


    // 我们的大根堆
    private int[] heap;
    private final int limit;
    // 表示目前这个堆收集了多少个数，也表示添加的下一个数应该放在哪个位置
    private int heapSize;

    // 构造初始化堆结构
    public HeapUtil(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    /**
     * 堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 堆是否满
     *
     * @return
     */
    public boolean isFull() {
        return heapSize == limit;
    }

    /**
     * 往堆中添加一个数，需要动态维持堆结构
     *
     * @param value
     */
    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = value;
        // value  heapSize
        heapInsert(heap, heapSize++);
    }

    /**
     * 弹出大根堆的堆顶元素，剩下的数，依然保持大根堆组织
     *
     * @return
     */
    public int pop() {
        int ans = heap[0];
        // 数组的最后有效位置，是heapSize-1
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    /**
     * 往堆上添加数后，要调整继续维持大根堆结构，需要找当前位置父节点比较
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 弹出堆顶后，把最后的元素提取到堆顶，进行调整，与孩子比较，孩子都不再比我大或者已经没孩子了，调整完毕
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        // 左孩子没越界，如果左孩子越界有孩子一定也越界
        while (left < heapSize) {
            // 取左右孩子值大的那个下标，需要考虑没有右孩子的边界情况
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 左右孩子中最大值，和当前值比较，谁大谁把下标给largest(当前，左，右的最大值下标)
            largest = arr[largest] > arr[index] ? largest : index;
            // index位置上的数比左右孩子的数都大，已经无需下沉
            if (largest == index) {
                break;
            }
            // 交换后，继续找左右孩子进行比较，周而复始
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
