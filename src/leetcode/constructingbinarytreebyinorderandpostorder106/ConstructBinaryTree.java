package leetcode.constructingbinarytreebyinorderandpostorder106;

/**
 * 105. 从后序与中序遍历序列构造二叉树
 * 根据一棵树的后序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 后序遍历 postorder = [9,20,7,15,3]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 *  3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 通过次数197,504提交次数283,178
 *
 * @author wangmingming160328
 * @date @2021/5/27 17:00
 */
public class ConstructBinaryTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        System.out.println("inStart, inEnd = " + inStart + ", " + inEnd);
        System.out.println("postStart, postEnd = " + postStart + ", " + postEnd);
        System.out.println("***************************************************");
        if (inStart > inEnd) {
            return null;
        }

        //后序遍历数据的最后一个元素就是根节点
        int index = 0;
        for (int count = inStart; count <= inEnd; count++) {
            //根节点在中序遍历数组中的下标
            if (inorder[count] == postorder[postEnd]) {
                index = count;
                break;
            }
        }
        //中序遍历根节点左边的左子树的最右边的索引
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(postorder[postEnd]);
        root.right = buildTree(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        root.left = buildTree(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);

        return root;
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
        ConstructBinaryTree demo = new ConstructBinaryTree();

        TreeNode tree = demo.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }
}
