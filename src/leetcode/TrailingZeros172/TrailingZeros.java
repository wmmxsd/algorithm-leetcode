package TrailingZeros172;

import java.math.BigInteger;

/**
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 *
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 * @author wangmingming160328
 * @Description
 * @date @2021/4/11 23:07
 */
public class TrailingZeros {
    public int trailingZeroes(int n) {
        if (n == 0) return 0;
        if (n == 1) return 0;
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        int counts = 0;
        String resultStr = String.valueOf(result);
        for (int index = resultStr.length() - 1; index >= 0; index--) {
            if (resultStr.charAt(index) != '0') {
                break;
            }
            counts++;
        }
       /* for (Character character : value.toCharArray()) {
            if (character == '0') {
                counts++;
            }
        }*/
         return counts;
    }

    public static void main(String[] args) {
        TrailingZeros trailingZeros = new TrailingZeros();
        trailingZeros.trailingZeroes(30);
    }
}
