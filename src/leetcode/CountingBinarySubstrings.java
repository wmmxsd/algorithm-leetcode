import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * <p>
 * 重复出现的子串要计算它们出现的次数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * <p>
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * <p>
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * <p>
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 * <p>
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wangmingming160328
 * @Description 计算二进制子串
 * @date @2020/8/10 21:31
 */
public class CountingBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int result = 0;
        List<Integer> sameNumberList = new ArrayList<>();
        int index = 0;
        while (index < s.length()) {
            int sameNumCount = 0;
            int num = s.charAt(index);
            while (index < s.length() && s.charAt(index) == num) {
                index++;
                sameNumCount++;

            }
            sameNumberList.add(sameNumCount);
        }

        for (index = 0; index < sameNumberList.size() - 1; index++) {
            result += Math.min(sameNumberList.get(index), sameNumberList.get(index + 1));
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String str0 = "00110011";
        String str1 = "10101";
        String str2 = "101010101";
        CountingBinarySubstrings solution = new CountingBinarySubstrings();
        solution.countBinarySubstrings(str0);
        solution.countBinarySubstrings(str1);
        solution.countBinarySubstrings(str2);
    }
}
