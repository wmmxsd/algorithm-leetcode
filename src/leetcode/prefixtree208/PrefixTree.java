package prefixtree208;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 *
 * 示例：
 *
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 *
 * 提示：
 *
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 * @author wangmingming160328
 * @Description
 * @date @2021/4/26 22:08
 */
public class PrefixTree {
    int nums = 10000;
    int[][] trie = new int[nums][26];
    int[] count = new int[nums];
    int index = 0;

    /** Initialize your data structure here. */
    public PrefixTree() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        int row = 0;
        for(char cr : word.toCharArray()) {
            int column = cr - 'a';
            //存储下一个字符的在二位数组里面的行数
            if (trie[row][column] == 0) trie[row][column] = ++index;
            row = trie[row][column];
        }
        count[row]++;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int row = 0;
        for (char cr : word.toCharArray()) {
            int column = cr - 'a';
            //为0代表没有插入该字符
            if (trie[row][column] == 0) return false;
            row = trie[row][column];
        }
        //最后一个字符是否是结尾字符
        return count[row] != 0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int row = 0;
        for (char cr : prefix.toCharArray()) {
            int column = cr - 'a';
            //为0代表没有插入该字符
            if (trie[row][column] == 0) return false;
            row = trie[row][column];
        }

        return true;
    }

    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();
        prefixTree.insert("apple");
        System.out.println(prefixTree.search("apple"));   // 返回 True
        System.out.println(prefixTree.search("app"));     // 返回 False
        System.out.println(prefixTree.startsWith("app")); // 返回 True
        prefixTree.insert("app");
        System.out.println(prefixTree.search("app"));     // 返回 True
    }
}
