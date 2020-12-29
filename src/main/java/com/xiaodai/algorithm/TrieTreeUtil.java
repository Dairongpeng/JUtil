package com.xiaodai.algorithm;

/**
 * Author ：dai
 * Date ：2020/12/25 2:56 下午
 * Description：标准前缀树结构,适用于英文26个字符的结构；需要扩展更多的字符路径，可以用Ascii码为键的HashMap进行扩展
 */
public class TrieTreeUtil {

    /**
     * 前缀树节点信息
     */
    public static class TrieNode {
        // pass表示字符从该节点的路径通过
        public int pass;
        // end表示该字符到此节点结束
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    /**
     * 前缀树结构
     */
    public static class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }


        /**
         * 往前缀树种插入一个字符串
         *
         * @param word
         */
        public void insert(String word) {
            if(word == null) {
                return;
            }

            char[] ch = word.toCharArray();

            // 每插入一个字符串，都从前缀树维护的头节点开始
            TrieNode node = root;
            // 头节点的pass首先++
            node.pass++;

            int path = 0;

            // 遍历插入字符串转化而来的字符数组
            for (int i = 0; i < ch.length; i++) {
                // 确认走26条路径的哪一个路径
                path = ch[i] - 'a';
                // 如果前往的路径没有节点，新增
                if(node.nexts[path] == null) {
                    node.nexts[path] = new TrieNode();
                }

                // 前往的这个节点，经过该节点的条目数增加1
                node = node.nexts[path];
                node.pass++;
            }

            // 经过循环后，node来到最后一个节点，最后一次循环更改了该节点的pass，现在单独对最后节点，结尾条目+1
            node.end++;

        }

        /**
         * 删除该前缀树的某个字符串
         *
         * @param word
         */
        public void delete(String word) {

            // 要保证删除的节点是我们前缀树中加入过的
            if(search(word) != 0) {
                // 该字符串所经过前缀树的沿途节点的pass都要减1
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;

                int path = 0;

                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';

                    /**
                     * 寻找的过程中发现，经过本次删除，该节点的pass即将变为0，那么该节点向下的节点可以直接删除。引用置为空，该节点的下面节点，让JVM回收
                     */
                    if(--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    // 前往下个路径对应的节点
                    node = node.nexts[path];
                }

                // 找到最终节点，最终节点的end要减1
                node.end--;
            }

        }

        /**
         * 检查某个字符串被加入该前缀树多少次
         * @param word
         * @return
         */
        public int search(String word) {

            if(word == null) {
                return 0;
            }

            char[] chs = word.toCharArray();
            TrieNode node = root;

            int index = 0;

            // 确定下一个字符前往的路径
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';

                // 如果该字符串的某个字符需要前往的路径，前缀树该节点为空，那么直接返回没有加入过该字符串
                if(node.nexts[index] == null) {
                    return 0;
                }

                // node跳转到前往的路径
                node = node.nexts[index];

            }

            // 跳出循环，意味着找到该字符串在前缀树中对应的路径，返回最后节点的end
            return node.end;

        }


        /**
         * 查询前缀树种有多少个是以目标字符串开头的字符串
         *
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if(pre == null) {
                return 0;
            }

            TrieNode node = root;
            char[] chs = pre.toCharArray();

            int index = 0;

            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';

                // 目标字符串的路径走不完，不存在
                if(node.nexts[index] == null) {
                    return 0;
                }

                node = node.nexts[index];
            }

            // 顺利走到最后，返回该字符串对应路径的最后节点上的pass数目
            return node.pass;

        }

    }


}
