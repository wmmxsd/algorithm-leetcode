import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @Description 两数之和
 * @date @2020/5/18 23:25
 */
public class SumOfTwoNumbers {
    /**
     * 两个for循环
     * 时间复杂度为O(n^2)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for(int index = 0; index < nums.length - 1; index++){
            for(int innerIndex = index + 1; innerIndex < nums.length; innerIndex++){
                if(nums[index] + nums[innerIndex] == target) {
                    return new int[]{index, innerIndex};
                }
            }
        }
        throw new IllegalArgumentException("该数组中无法找到和为" + target + "的那两个整数");
    }

    public static int[] towSumByMap(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>(length);
        for (int index = 0; index < length; index++) {
            int key = target - nums[index];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), index};
            }
            map.put(nums[index], index);
        }
        throw new IllegalArgumentException("该数组中无法找到和为" + target + "的那两个整数");
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 5};
        System.out.println(Arrays.toString(twoSum(nums, 8)));
        System.out.println(Arrays.toString(towSumByMap(nums, 8)));
    }
}
