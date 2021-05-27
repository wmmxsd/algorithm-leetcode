package perfectsquare279;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 104
 * 通过次数138,630提交次数228,948
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * @author wangmingming160328
 * @Description
 * @date @2021/5/27 21:18
 */
public class PerfectSquare {
    public int numSquares(int n) {
        int result = Integer.MAX_VALUE;
        int num = (int) Math.sqrt(getMinPerfectSquare(n, true, -1,1));
        for (int count = 1; count <= num; count++) {
            int circle = 0;
            int temp = n;
            while (temp != 0) {
                temp -= getMinPerfectSquare(temp, temp == n, num, count);
                circle++;
            }
            result = Math.min(result, circle);

        }
        return result;
    }

    private int getMinPerfectSquare(int n, boolean useCount, int num, int count) {
        int index = 1;
        while (index * index <= n && index <= count - num + 1) {
            index++;
        }
        return (index - (useCount ? count : 1)) * (index - (useCount ? count : 1));
    }

    public static void main(String[] args) {
        PerfectSquare demo = new PerfectSquare();
        System.out.println(demo.numSquares(1));
        System.out.println(demo.numSquares(12));
        System.out.println(demo.numSquares(13));
        System.out.println(demo.numSquares(4));
    }
}
