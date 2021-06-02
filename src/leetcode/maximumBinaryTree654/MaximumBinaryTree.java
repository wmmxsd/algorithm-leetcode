package leetcode.maximumBinaryTree654;

/**
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 *
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 *         - 空数组，无子节点。
 *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 *             - 空数组，无子节点。
 *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 *         - 只有一个元素，所以子节点是一个值为 0 的节点。
 *         - 空数组，无子节点。
 * 示例 2：
 *
 *
 * 输入：nums = [3,2,1]
 * 输出：[3,null,2,null,1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * nums 中的所有整数 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @date @2021/5/27 11:36
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new NullPointerException();
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int lo, int hi) {
        //lo必须要小于hi，因为我们需要下标从lo到hi的数组元素进行计算
        // ||
        // ▽
        //lo必须可以等于hi，因为计算某个叶子节点时，此时其在数组的lo和hi相等，所以必须放行
        // ||
        // ▽
        if (lo > hi) {
            return null;
        }

        //max只能小于0，因为nums数组中值有可能为0，max如果默认为0，if (nums[count] > max) {...}部分就进不去导致逻辑错误
        int max = -1, index = -1;
        //[lo, hi]为闭区间
        for(int count = lo; count <= hi; count++) {
            if (nums[count] > max) {
                max = nums[count];
                index = count;
            }
        }

        TreeNode node = new TreeNode(max);
        //最大值左边部分来构造左子树
        node.left = constructMaximumBinaryTree(nums, lo, index - 1);
        //最大值右边部分来构造右子树
        node.right = constructMaximumBinaryTree(nums, index + 1, hi);

        //将子节点赋给父节点的左/右子节点
        return node;
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
        int[] nums = {3,2,1,6,0,5};
        MaximumBinaryTree demo = new MaximumBinaryTree();
        demo.constructMaximumBinaryTree(nums);
    }
}
