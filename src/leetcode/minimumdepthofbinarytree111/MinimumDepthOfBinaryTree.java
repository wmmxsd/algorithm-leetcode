package leetcode.minimumdepthofbinarytree111;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * node.left == null && node.right == null ==> true
 * <p>
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * <p>
 * 提示：
 * <p>
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * @author wangmingming160328
 * @date @2021/4/16 11:40
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        return bfs(root);
    }

    private int bfs(TreeNode root) {
        int result = 0;
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        result++;
        while (!queue.isEmpty()) {
            //每次遍历一层
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return result;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result++;
        }

        return result;
    }

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode treeNode0 = new TreeNode(2);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(7);

        root.left = treeNode0;
        root.right = treeNode1;
        treeNode0.left = treeNode2;
        treeNode0.right = treeNode3;
        treeNode1.left = treeNode4;
        treeNode1.right = treeNode5;

        MinimumDepthOfBinaryTree tree = new MinimumDepthOfBinaryTree();
        System.out.println(tree.minDepth(root));
    }

}
