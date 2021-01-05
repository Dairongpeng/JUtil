package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date   ：2021/1/4 11:54 上午
 * Description：动态规划的几个例子
 */
public class DPExampleUtil {

    /**
     * 1、🎒背包问题：给定两个长度都为N的数组weights和values，weight[i]和values[i]分别代表i号物品的重量和价值。
     * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少？
     *
     * @param w   重量数组
     * @param v   价值数组
     * @param bag 背包的最大容量
     * @return 返回该背包所能装下的最大价值
     */
    public static int getMaxValue(int[] w, int[] v, int bag) {
        // 初始传入w,v。index位置开始，alreadyW表示在index位置的时候，重量已经到达了多少
        return process(w, v, 0, 0, bag);
    }

    // 暴力递归的第一种尝试
    // 0..index-1上做了货物的选择，使得你已经达到的重量是多少 alreadyW
    // 如果返回-1，认为没有方案
    // 如果不返回-1，认为返回的值是真实价值
    public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        // base case
        if (alreadyW > bag) {
            return -1;
        }
        // 重量没超
        if (index == w.length) {
            return 0;
        }
        // 当前不选择index的货物情况下，后续的价值
        // 无需传递当前index的重量，且p1就是总价值
        int p1 = process(w, v, index + 1, alreadyW, bag);
        // 当前选择了index的货物，把重量加上，继续向下递归
        int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
        // p2表示要了当前货物之后总价值应该是后续价值加上当前价值
        int p2 = -1;
        if (p2next != -1) {
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);

    }


    /**
     * 背包问题的第二种暴力尝试。
     *
     * @param w   重量数组
     * @param v   价值数组
     * @param bag 背包容量
     * @return 返回给定背包容量所能装下的最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        // 相比上一个暴力递归尝试，去掉了alreadyW。用背包剩余空间代替；rest表示背包剩余空间，初始剩余空间就是背包容量
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        // base case 1 无效方案。背包剩余容量装不下当前重量的情况
        if (rest < 0) {
            return -1;
        }
        // rest >=0。index来到终止位置，没货物了，当前返回0价值
        // base case 2
        if (index == w.length) {
            return 0;
        }
        // 有货也有空间。当前index不选择，得到p1总价值
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        // 选择了index位置，剩余空间减去当前重量
        int p2Next = process(w, v, index + 1, rest - w[index]);
        // 选择index的总价值，是index...的价值加上个当前index的价值
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }


    /**
     * 0-1背包问题：动态规划解决方案。在暴力递归的思路上改进
     * <p>
     * 以背包问题举例，我们每一个重量有要和不要两个选择，且都要递归展开。那么我们的递归时间复杂度尾O(2^N)。
     * 而记忆化搜索，根据可变参数得到的长为N价值为W的二维表，那么我们的时间复杂度为O(N*bag)。
     * 如果递归过程中状态转移有化简继续优化的可能，例如枚举。那么经典动态规划可以继续优化，
     * 否则记忆化搜索和动态规划的时间复杂度是一样的
     *
     * @param w   重量数组
     * @param v   价值数组
     * @param bag 背包容量
     * @return 返回价值
     */
    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        // 准备一张dp表，行号为我们的重量范围bag+1。列为我们的价值数目个数的范围N+1。dp数组装下所有的可能性。
        int[][] dp = new int[N + 1][bag + 1];
        // 由于暴力递归中index==w.length的时候，总是返回0。所以：
        // dp[N][...] = 0。整形数组初始化为0，无需处理
        // 由于N行已经初始化为0，我们从N-1开始。填我们的dp表
        for (int index = N - 1; index >= 0; index--) {
            // 剩余空间从0开始，一直填写到bag
            for (int rest = 0; rest <= bag; rest++) { // rest < 0
                // 通过正常位置的递归处理。我们转而填写我们的dp表
                // 所以我们p1等于dp表的下一层向上一层返回
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                // rest - w[index] 不越界
                if (rest - w[index] >= 0) {
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                // p1和p2取最大值
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        // 最终返回dp表的0，bag位置，就是我们暴力递归的主函数调用
        return dp[0][bag];
    }


    /**
     * 2、机器人运动问题：一排有N个位置，1~N，N > 2 ，开始时机器人在其中的M位置上。机器人在1位置上只能往右走，在N位置上只能往左走
     * 规定机器人必须走K步，最终能来到P位置，P在1~N上，的方法有多少种？
     * <p>
     * 注：纯粹暴力递归解法。不加入缓存
     *
     * @param N 横向位置数
     * @param M 从M点出发
     * @param K 必须经过K步
     * @param P 最终到达P位置
     * @return 方法数
     */
    public static int ways1(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // 总共N个位置，从M点出发，还剩K步可以走，返回最终能达到P的方法数
        return walk(N, M, K, P);
    }

    // N : 位置为1 ~ N，固定参数
    // cur : 当前在cur位置，可变参数
    // rest : 还剩rest步没有走，可变参数
    // P : 最终目标位置是P，固定参数
    // 该函数的含义：只能在1~N这些位置上移动，当前在cur位置，走完rest步之后，停在P位置的方法数作为返回值返回
    public static int walk(int N, int cur, int rest, int P) {
        // 如果没有剩余步数了，当前的cur位置就是最后的位置
        // 如果最后的位置停在P上，那么之前做的移动是有效的
        // 如果最后的位置没在P上，那么之前做的移动是无效的
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        // 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
        // 后续的过程就是，来到2位置上，还剩rest-1步要走
        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }
        // 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
        // 后续的过程就是，来到N-1位置上，还剩rest-1步要走
        if (cur == N) {
            return walk(N, N - 1, rest - 1, P);
        }
        // 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
        // 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
        // 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
        // 走向左、走向右是截然不同的方法，所以总方法数要都算上
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
    }


    /**
     * 2、机器人运动问题。解法2；根据暴力递归，加入缓存，实现记忆化搜索。
     *
     * @param N 横向位置数
     * @param M 从M点出发
     * @param K 必须经过K步
     * @param P 最终到达P位置
     * @return 方法数
     */
    public static int waysCache(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }

        // 我们准备一张缓存的dp表
        // 由于我们的cur范围是1~N，我们准备N+1。
        // rest范围在1~K。我们准备K+1。
        // 目的是把我们的可能结果都能装得下
        int[][] dp = new int[N + 1][K + 1];
        // 设置这张表的初始值都为-1，代表都还没用过
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col++) {
                dp[row][col] = -1;
            }
        }
        return walkCache(N, M, K, P, dp);
    }

    // HashMap<String, Integer>   (19,100)。也可以不用Hash表，直接字符串拼接例如 "19_100"
    // 我想把所有cur和rest的组合，返回的结果，加入到缓存里
    public static int walkCache(int N, int cur, int rest, int P, int[][] dp) {
        // 当前场景已经计算过，不要再暴力展开，直接从缓存中拿
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0) {
            // 先加缓存
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        // cur为1，当前只能往右走
        if (cur == 1) {
            // 先加缓存
            dp[cur][rest] = walkCache(N, 2, rest - 1, P, dp);
            return dp[cur][rest];
        }
        // cur为N,当前只能往左走
        if (cur == N) {
            // 先加缓存
            dp[cur][rest] = walkCache(N, N - 1, rest - 1, P, dp);
            return dp[cur][rest];
        }
        // 先加缓存
        dp[cur][rest] = walkCache(N, cur + 1, rest - 1, P, dp)
                + walkCache(N, cur - 1, rest - 1, P, dp);
        return dp[cur][rest];
    }


    /**
     * 2、机器人运动，根据计划化搜索。改造成动态规划。实质就是填DP表，返回我们需要求解的dp表中的位置的值
     *
     * @param N 横向位置数
     * @param M 从M点出发
     * @param K 必须经过K步
     * @param P 最终到达P位置
     * @return 方法数
     */
    public static int ways2(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        // 经过0步，当前位置再P位置上，到达P位置的方法有一种
        dp[0][P] = 1;
        // 填充DP表
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                // 还剩i步，当前在j位置。j如果是1位置，只能往右走，还剩i-1步。我们需要拿到2位置还剩i-1步的的dp信息
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == N) { // 还剩i步，当前在j位置。j如果是N位置，只能往左走，还剩i-1步。我们需要拿到N-1位置还剩i-1步的的dp信息
                    dp[i][j] = dp[i - 1][N - 1];
                } else { // 在中间的任意位置，DP当前的DP信息，依赖于左右DP信息的和
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }


    /**
     * 3、凑货币问题：有一个表示货币面值的数组arr，每种面值的货币可以使用任意多张。arr数组元素为正数，且无重复值。例如[7,3,100,50]这是四种面值的货币。
     * 给定一个特定金额Sum，我们用货币面值数组有多少种方法，可以凑出该面值。例如P=1000,用这是四种面值有多少种可能凑出1000
     *
     * @param arr 货币面值数组
     * @param aim 凑出的目标金额
     * @return 返回有多少种方案
     */
    // arr中都是正数且无重复值，返回组成aim的方法数，暴力递归
    public static int waysCoin(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return processCoin(arr, 0, aim);
    }

    public static int processCoin(int[] arr, int index, int rest) {
        // base case
        // 当在面值数组的arr.length，此时越界，没有货币可以选择。
        // 如果当前目标金额aim也就是初始的rest就是0，那么存在一种方法，如果目标金额aim不为0，返回0中方法
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        // 普遍位置
        int ways = 0;
        // 从0号位置开始枚举，选择0张，1张，2张等
        // 条件是张数count乘以选择的面值，不超过目标面值rest。当前可以继续选择
        for (int count = 0; count * arr[index] <= rest; count++) {
            // 方法数加上除去当前选择后所剩面额到下一位置index + 1的选择数，递归。rest - (count * arr[index]为当前选择后还剩的目标面值
            ways += processCoin(arr, index + 1, rest - (count * arr[index]));
        }
        return ways;
    }

    /**
     * 凑货币问题：暴力递归改记忆化搜索解决方案。waysCoin暴力递归，改为记忆化搜索。waysCoinBySearch为记忆化搜索版本
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int waysCoinBySearch(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 缓存结构，且只和index和rest有关，跟arr无关。即选择不选择某种面值的货币的位置在变，选择不选择后目标面值在变。缓存结构确保装下所有可能性
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 一开始所有的过程，都没有计算呢，dp二维表初始化为-1
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        // 缓存结构向传递进递归过程
        return processCoinBySearch(arr, 0, aim, dp);
    }

    // 如果index和rest的参数组合，是没算过的，dp[index][rest] == -1
    // 如果index和rest的参数组合，是算过的，dp[index][rest]  > - 1
    public static int processCoinBySearch(int[] arr, int index, int rest, int[][] dp) {
        // 当前需要递归拆分的结果，已经在缓存结构中存在了，递归无需展开。直接拿缓存
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        // 如果递归来到了arr.length。后续没有选择，如果aim就是0,那就是一种结过就是不选择arr.length。否则aim不等于0，那就是0中可能
        // base case
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }

        int ways = 0;
        // 中间的任意位置，当前位置的面值选择后，仍然没达到当前目标面值，选择当前，进入下一个位置递归
        for (int count = 0; count * arr[index] <= rest; count++) {
            // 返回之前加入缓存
            ways += processCoinBySearch(arr, index + 1, rest - (count * arr[index]), dp);
        }
        // 返回之前加入缓存
        dp[index][rest] = ways;
        return ways;
    }


    /**
     * 凑货币问题：记忆化搜索改造为动态规划版本。如果没有枚举行为，该动态该规划为自顶向下的动态规划和记忆化搜索等效，但这题存在枚举行为。
     *
     * @param arr 面值数组
     * @param aim 目标面值
     * @return 返回方法数目
     */
    public static int waysCoinByDP(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        // dp表。位置在变，目标面值随着选择过程在变
        int[][] dp = new int[N + 1][aim + 1];
        // 根据递归方法，N为arr的越界位置，但是我们的dp表定义的是N+1。
        // N位置，如果aim为0，则dp[N][0] = 1;
        dp[N][0] = 1;// dp[N][1...aim] = 0;

        // 每个位置依赖自己下面的位置，那么我们从下往上循环
        for (int index = N - 1; index >= 0; index--) {
            // rest从左往右
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // 普遍位置填dp表
                for (int count = 0; count * arr[index] <= rest; count++) {
                    ways += dp[index + 1][rest - (count * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        // 最终我们需要[0,aim]位置的解
        return dp[0][aim];
    }

    /**
     * 凑货币问题：由于存在枚举行为，该题可以在动态规划的基础上据需优化。根据枚举，用具体化例子来找出规律，省掉枚举
     * @param arr 面值数组
     * @param aim 目标面值
     * @return 返回方法数目
     */
    public static int waysPlush(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;// dp[N][1...aim] = 0;
        for(int index = N - 1; index >= 0; index--) {
            for(int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if(rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        // 背包问题
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));

        System.out.println("=============================");

        // 机器人运动问题
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(waysCache(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));

        System.out.println("=============================");

        int[] arr = { 5, 10,50,100 };
        int sum = 1000;
        System.out.println(waysCoin(arr, sum));
        System.out.println(waysCoinBySearch(arr, sum));
        System.out.println(waysCoinByDP(arr, sum));
        System.out.println(waysPlush(arr, sum));
    }

}
