package leetcode.topkhighfrequencyelements347;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @author wangmingming160328
 * @date @2021/5/13 15:17
 */
public class TopKHighFrequencyElements2 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));

        //桶排序
        //数组下标代表相同数字出现的次数，值代表数字
        List<Integer>[] array = new List[nums.length];
        for(int key : map.keySet()) {
            Integer index = map.get(key);
            if (array[index] == null) array[index] = new ArrayList<>();
            array[index].add(index);
        }

        //倒序遍历数组获取出现顺序从大到小的排列
        List<Integer> result = new ArrayList<>();
        for (int index = k - 1; index > -1; index--) {
            if (array[index] == null) continue;
            result.addAll(array[index]);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        TopKHighFrequencyElements2 app = new TopKHighFrequencyElements2();
        System.out.print(Arrays.toString(app.topKFrequent(new int[]{4, 4, 4, 4, 5, 1, 1, 1, 2, 2, 3}, 2)));
    }
}
