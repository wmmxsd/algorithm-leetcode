package leetcode.topkhighfrequencyelements347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * @author wangmingming160328
 * @date @2021/5/13 15:17
 */
public class TopKHighFrequencyElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> map.put(num, map.getOrDefault(num, 1)));
        Integer[] result = map.entrySet().stream().
                sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue()).limit(k).map(Map.Entry::getKey).toArray(Integer[]::new);
        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        TopKHighFrequencyElements app = new TopKHighFrequencyElements();
        System.out.print(Arrays.toString(app.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }
}