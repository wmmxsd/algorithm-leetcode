package leetcode.findingduplicatesubtrees652;

import leetcode.minimumdepthofbinarytree111.MinimumDepthOfBinaryTree;

import java.util.*;
import java.util.stream.Collectors;

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
    private Map<String, Integer> nodeCountsMap = new HashMap<>();
    private List<TreeNode> result = new  ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        preOrder(root);
        return result;
    }

    /**
     * 使用字符串记录子树
     */
    private String preOrder(TreeNode root) {
        if (root == null) {
            //"#"记录空节点
            return "#";
        }
        StringBuilder subTreeBuilder = new StringBuilder(root.val + "");
        String left = preOrder(root.left);
        String right = preOrder(root.right);
        String subTreeStr = subTreeBuilder.append(",").append(left).append(",").append(right).toString();
        Integer count = nodeCountsMap.getOrDefault(subTreeStr, 0);
        //解决当出现三颗及以上数量的子树时重复添加的问题
        if (count == 1) {
            result.add(root);
        }
        nodeCountsMap.put(subTreeStr, count + 1);
        return subTreeStr;
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

        @Override
        public String toString() {
            return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode treeNode0 = new TreeNode(2);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(4);

        root.left = treeNode0;
        root.right = treeNode1;
        treeNode0.left = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode3.left = treeNode5;

        FindingDuplicateSubtrees demo = new FindingDuplicateSubtrees();
        System.out.println(demo.findDuplicateSubtrees(root));
    }
}
