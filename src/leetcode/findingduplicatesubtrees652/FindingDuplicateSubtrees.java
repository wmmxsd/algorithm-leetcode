package findingduplicatesubtrees652;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 652. 寻找重复的子树
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 * 示例 1：
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 下面是两个重复的子树：
 *
 *       2
 *      /
 *     4
 * 和
 *
 *     4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 *
 * 通过次数25,088提交次数44,194
 * https://leetcode-cn.com/problems/find-duplicate-subtrees/
 * @author wangmingming160328
 * @Description
 * @date @2021/6/1 22:16
 */
public class FindingDuplicateSubtrees {
    private Map<TreeNode, Integer> nodeCountsMap = new HashMap<>();
    private List<TreeNode> result = new Vector<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        return preOrder(root);
    }

    private List<TreeNode> preOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        //
        preOrder(root.left);
        preOrder(root.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
