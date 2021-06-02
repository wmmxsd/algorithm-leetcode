package leetcode.binaryTreeExpandedIntoLinkedList114;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * <p>
 * 通过次数138,583提交次数191,764
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author wangmingming160328
 * @date @2021/5/25 17:41
 */
public class BinaryTreeExpandedIntoLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            //获取该节点的右子树
            TreeNode rightNode = root.right;
            //左子树变为右子树
            root.right = root.left;
            //左子树置为null
            root.left = null;
            //找出新的右子树的最右边的节点后把原右子树变为其右子树
            TreeNode tempNode = root.right;
            while (tempNode.right != null) {
                tempNode = tempNode.right;
            }
            tempNode.right = rightNode;
        }
        //root只有右子树，递归其下一个右节点，若为null或其左子树为null则跳出
        flatten(root.right);
    }

    /**
     * 后序遍历：3-4-2-6-5-1
     * 后序遍历变形：
     * <code>
     *     postOrder(node.right);
     *     postOrder(node.left);
     *     //toDo
     * </code>
     * 后序遍历变形：6-5-4-3-2-1
     * 可以采用变形的后序遍历，然后将当前节点的左子树置为null，右子树置为上一个节点
     * @param root
     */

    /**
     * 记录遍历过程中上一个节点
     */
    private TreeNode node;

    public void flatten1(TreeNode root) {
        if (root == null)
            return;

        flatten1(root.right);
        flatten1(root.left);

        root.left = null;
        root.right = node;
        node = root;
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

    public static void main(String[] args) {
        BinaryTreeExpandedIntoLinkedList demo = new BinaryTreeExpandedIntoLinkedList();
        //[1,2,5,3,4,null,6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        //demo.flatten(root);
        demo.flatten1(root);
    }

}
