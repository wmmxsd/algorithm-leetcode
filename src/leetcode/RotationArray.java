import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @Description
 * @date @2021/4/9 21:56
 */
public class RotationArray {
    public  void rotate(int[] nums, int k) {
        int size = k % nums.length;
        StringBuilder result = new StringBuilder();
        for (int index = size; index > 0; index--) {
            result.append(nums[nums.length - index]).append(",");
        }
        for (int index = 0; index < nums.length - size; index++) {
            result.append(nums[index]).append(",");
        }
        nums = Arrays.stream(result.deleteCharAt(result.length() - 1).toString().split(",")).mapToInt((Integer::parseInt)).toArray();
    }

    public  void rotate1(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k % nums.length - 1);
        reverse(nums, k % nums.length, nums.length - 1);

    }

    private void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
            begin++;
            end--;
        }
    }

    public static void main(String[] args) {
    /*rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 1);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 2);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 5);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 6);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 7);
    rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 8);*/
        RotationArray rotationArray = new RotationArray();
        rotationArray.rotate(new int[]{1,2,3,4,5,6}, 2);
        rotationArray.rotate1(new int[]{1,2,3,4,5,6}, 2);
    }
}
