package com.xiaodai.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Author ：dai
 * Date   ：2021/1/2 12:53 下午
 * Description： 并查集结构
 */
public class UnionFindUtil {

    // 并查集结构中的节点类型。把V包装一下，方便引用
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    /**
     * 并查集结构
     * @param <V>
     */
    public static class UnionSet<V> {

        // 每个样本点，到自身的样本点的关系映射。
        public HashMap<V, Node<V>> nodes;

        // 每个样本点，到他最上层的该节点的代表点的关系映射
        public HashMap<Node<V>, Node<V>> parents;

        // 每个最上方的代表点和该点连通个数的映射
        public HashMap<Node<V>, Integer> sizeMap;

        /**
         * 构造函数，构造一批样本
         *
         * @param values
         */
        public UnionSet(List<V> values) {
            // 每个样本的V指向自身的代表节点
            // 每个样本当前都是独立的，parent是自身
            // 每个样本都是代表节点放入sizeMap，初始都是独立的，size为1
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * Union方法，合并两个集合为一个集合
         *
         * @param a
         * @param b
         */
        public void union(V a, V b) {
            // 先检查a和b有没有都登记过
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }

            // 找到a的最上面的代表点
            Node<V> aHead = findFather(nodes.get(a));
            // 找到b的最上面的代表点
            Node<V> bHead = findFather(nodes.get(b));

            // 只有两个最上代表点内存地址不相同，需要union
            if (aHead != bHead) {

                // 由于aHead和bHead都是代表点，那么在sizeMap里可以拿到大小
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);

                // 哪个小，哪个挂在下面
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                // 把小集合直接挂到大集合的最上面的代表节点下面
                parents.put(small, big);
                // 大集合的代表节点的size要吸收掉小集合的size
                sizeMap.put(big, aSetSize + bSetSize);
                // 把小的代表点记录删除
                sizeMap.remove(small);
            }
        }


        /**
         * 检查两个样本，是否在一个集合中。isSameSet方法
         *
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(V a, V b) {
            // 先检查a和b有没有登记
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            // 比较a的最上的代表点和b最上的代表点
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 给定节点，找节点最上方的代表节点
         * 从点cur开始，一直往上找，找到不能再往上的代表点，返回
         * 通过把路径上所有节点指向最上方的代表节点，目的是把findFather优化成O(1)的
         *
         * @param cur
         * @return
         */
        public Node<V> findFather(Node<V> cur) {
            // 在找father的过程中，沿途所有节点加入当前容器，便于后面扁平化处理
            Stack<Node<V>> path = new Stack<>();
            // 当前节点的父亲不是指向自己，进行循环
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 循环结束，cur是最上的代表节点
            // 把沿途所有节点拍平，都指向当前最上方的代表节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }


    }

}
