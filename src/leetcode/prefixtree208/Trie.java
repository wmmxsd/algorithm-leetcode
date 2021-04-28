package prefixtree208;

/**
 * @author wangmingming160328
 * @Description
 * @date @2021/4/26 23:11
 */
public class Trie {
    private class TrieNode {
        boolean isEnd = false;
        TrieNode[] trieNode = new TrieNode[26];
    }

    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char cr : word.toCharArray()) {
            int index = cr - 'a';
            if (node.trieNode[index] == null)
                node.trieNode[index] = new TrieNode();
            node = node.trieNode[index];
        }

        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (char cr : word.toCharArray()) {
            int index = cr - 'a';
            if (node.trieNode[index] == null) return false;
            node = node.trieNode[index];
        }

        return node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char cr : prefix.toCharArray()) {
            int index = cr - 'a';
            if (node.trieNode[index] == null) return false;
            node = node.trieNode[index];
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
    }
}
