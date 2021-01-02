package com.xiaodai.algorithm;

import java.util.*;

/**
 * Author ：dai
 * Date   ：2021/1/2 3:40 下午
 * Description：图结构表示和基本算法
 */
public class GraphGeneratorUtil {

    /**
     * 图的点结构表示
     */
    public static class Node {
        // 点的编号，标识
        public int value;
        // 入度，表示有多少个点连向该点
        public int in;
        // 出度，表示从该点出发连向别的节点多少
        public int out;
        // 直接邻居：表示由自己出发，直接指向哪些节点。nexts.size==out
        public ArrayList<Node> nexts;
        // 直接下级边：表示由自己出发的边有多少
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    /**
     * 图的边结构表示，由于任何图都可以理解为有向图，我们定义有向的边结构
     */
    public static class Edge {
        // 边的权重信息
        public int weight;
        // 出发的节点
        public Node from;
        // 指向的节点
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * 图的结构表示
     */
    public static class Graph {
        // 点的集合，编号为1的点是什么，用map结构
        public HashMap<Integer, Node> nodes;
        // 边的集合
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    /**
     * 任意图结构，向我们熟悉的上述图结构进行转化。这里把矩阵表示的图结构，转化为我们上述熟悉的图结构
     * matrix是长度为3，高度不限的矩阵，三个值分别表示权重，from标识，to标识：[weight, from节点上面的值，to节点上面的值]
     *
     * @param matrix
     * @return
     */
    public static Graph createGraph(Integer[][] matrix) {
        // 定义我们的图结构
        Graph graph = new Graph();
        // 遍历给定的图结构进行转换
        for (int i = 0; i < matrix.length; i++) {
            // matrix[0][0], matrix[0][1]  matrix[0][2]
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            // 我们的图结构不包含当前from节点，新建该节点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            // 没有to节点，建立该节点
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            // 拿出我们图结构的from节点
            Node fromNode = graph.nodes.get(from);
            // 拿出我们图结构的to节点
            Node toNode = graph.nodes.get(to);
            // 建立我们的边结构。权重，from指向to
            Edge newEdge = new Edge(weight, fromNode, toNode);
            // 把to节点加入到from节点的直接邻居中
            fromNode.nexts.add(toNode);
            // from的出度加1
            fromNode.out++;
            // to的入度加1
            toNode.in++;
            // 该边需要放到from的直接边的集合中
            fromNode.edges.add(newEdge);
            // 把该边加入到我们图结构的边集中
            graph.edges.add(newEdge);
        }
        return graph;
    }

    /**
     * 图的宽度优先遍历，从node节点出发
     *
     * @param node
     */
    public static void bfs(Node node) {

        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        // 图需要用set结构，因为图相比于二叉树有可能存在环
        // 即有可能存在某个点多次进入队列的情况
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                // 直接邻居，没有进入过Set的进入Set和队列
                // 用set限制队列的元素，防止有环队列一直会加入元素
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }


    /**
     * 图的深度优先遍历，从node节点出发
     *
     * @param node
     */
    public static void dfs(Node node) {

        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        // Set的作用和宽度优先遍历类似，保证重复的点不要进栈
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        // 打印时机是在进栈的时候
        // 同理该步可以换成其他处理逻辑，表示深度遍历处理某件事情
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            // 枚举当前弹出节点的后代
            for (Node next : cur.nexts) {
                // 只要某个后代没进入过栈，进栈
                if (!set.contains(next)) {
                    // 把该节点的父亲节点重新压回栈中
                    stack.push(cur);
                    // 再把自己压入栈中
                    stack.push(next);
                    set.add(next);
                    // 打印当前节点的值
                    System.out.println(next.value);
                    // 直接break，此时栈顶是当前next节点，达到深度优先的目的
                    break;
                }
            }
        }
    }

    /**
     * 图的拓扑排序，针对有向无环图，返回拓扑排序的顺序List
     *
     * @param graph
     * @return
     */
    public static List<Node> sortedTopology(Graph graph) {
        // key：某一个node
        // value：该节点剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 0入度队列，剩余入度为0的点，才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();

        // 拿到该图中所有的点集
        for (Node node : graph.nodes.values()) {
            // 初始化每个点，每个点的入度是原始节点的入度信息
            // 加入inMap
            inMap.put(node, node.in);
            // 由于是有向无环图，则必定有入度为0的起始点。放入到zeroInQueue
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        // 拓扑排序的结果，依次加入result
        List<Node> result = new ArrayList<>();

        while (!zeroInQueue.isEmpty()) {
            // 该有向无环图初始入度为0的点，直接弹出放入结果集中
            Node cur = zeroInQueue.poll();
            result.add(cur);
            // 该节点的下一层邻居节点，入度减一且加入到入度的map中
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                // 如果下一层存在入度变为0的节点，加入到0入度的队列中。
                // 一层一层剥离，选择入度最先变为0的节点，依次加入zero队列，就是拓扑的顺序
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

    /**
     * 克鲁斯卡尔最小生成树算法。借助并查集结构
     *
     * @param graph
     * @return
     */
    public static Set<Edge> kruskalMST(Graph graph) {
        // 先拿到并查集结构
        UnionFind unionFind = new UnionFind();
        // 该图的所有点加入到并查集结构
        unionFind.makeSets(graph.nodes.values());
        // 边按照权值从小到大排序，加入到堆。自定义排序规则，变大根堆为小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        for (Edge edge : graph.edges) { // M 条边
            priorityQueue.add(edge);  // O(logM)
        }

        Set<Edge> result = new HashSet<>();
        // 堆不为空，弹出小根堆的堆顶
        while (!priorityQueue.isEmpty()) {
            // 假设M条边，O(logM)
            Edge edge = priorityQueue.poll();

            // 如果该边的左右两侧不在同一个集合中
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
                // 要这条边
                result.add(edge);
                // 联合from和to
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }

    /**
     * 并查集结构
     */
    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while(n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while(!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    /**
     * Prim算法，最小生成树
     * @param graph
     * @return
     */
    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        // 哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        // 已经考虑过的边，不要重复考虑
        Set<Edge> edgeSet = new HashSet<>();
        // 依次挑选的的边在result里
        Set<Edge> result = new HashSet<>();
        // 随便挑了一个点,进入循环处理完后直接break
        for (Node node : graph.nodes.values()) {
            // node 是开始点
            if (!nodeSet.contains(node)) {
                // 开始节点保留
                nodeSet.add(node);
                // 开始节点的所有邻居节点全部放到小根堆
                // 即由一个点，解锁所有相连的边
                for (Edge edge : node.edges) {
                    if (!edgeSet.contains(edge)) {
                        edgeSet.add(edge);
                        priorityQueue.add(edge);
                    }
                }

                while (!priorityQueue.isEmpty()) {
                    // 弹出解锁的边中，最小的边
                    Edge edge = priorityQueue.poll();
                    // 可能的一个新的点,from已经被考虑了，只需要看to
                    Node toNode = edge.to;
                    // 不含有的时候，就是新的点
                    if (!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            // 没加过的，放入小根堆
                            if (!edgeSet.contains(edge)) {
                                edgeSet.add(edge);
                                priorityQueue.add(edge);
                            }
                        }
                    }
                }
            }
            // 直接break意味着我们不用考虑森林的情况
            // 如果不加break我们可以兼容多个无向图的森林的生成树
            // break;
        }
        return result;
    }


    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    /**
     * 图的最短路径算法，dijkstra，求图中某个节点到各个节点的最小距离
     * 某个点不在map中记录，则from到该点位正无穷
     * 该方法是dijkstra的原生版本，可以用堆结构继续进行优化
     *
     * @param from
     * @return
     */
    public static HashMap<Node, Integer> dijkstra(Node from) {
        // 从from出发到所有点的最小距离表
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // from到from距离为0
        distanceMap.put(from, 0);
        // 已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        // from 0 得到没选择过的点的最小距离
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);

        // 得到minNode之后
        while (minNode != null) {
            // 把minNode对应的距离取出,此时minNode就是桥连点
            int distance = distanceMap.get(minNode);

            // 把minNode上所有的邻边拿出来
            // 这里就是要拿到例如A到C和A到桥连点B再到C哪个距离小的距离
            for (Edge edge : minNode.edges) {
                // 某条边对应的下一跳节点toNode
                Node toNode = edge.to;

                // 如果关于from的distencMap中没有去toNode的记录，表示正无穷，直接添加该条
                if (!distanceMap.containsKey(toNode)) {
                    // from到minNode的距离加上个minNode到当前to节点的边距离
                    distanceMap.put(toNode, distance + edge.weight);

                    // 如果有，看该距离是否更小，更小就更新
                } else {
                    distanceMap.put(edge.to,
                            Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // 锁上minNode，表示from通过minNode到其他节点的最小值已经找到
            // minNode将不再使用
            selectedNodes.add(minNode);
            // 再在没有选择的节点中挑选MinNode当成from的桥接点
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        // 最终distanceMap全部更新，返回
        return distanceMap;
    }

    // 得到没选择过的点的最小距离
    public static Node getMinDistanceAndUnselectedNode(
            HashMap<Node, Integer> distanceMap,
            HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            // 没有被选择过，且距离最小
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


}
