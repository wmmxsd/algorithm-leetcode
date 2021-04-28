package countprime204;

import java.util.BitSet;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= n <= 5 * 106
 * @author wangmingming160328
 * @Description
 * @date @2021/4/25 22:32
 */
public class CountPrime {
    //超时
    public int countPrimes(int n) {
        if (n < 0) throw new IllegalArgumentException();

        if (n == 0 || n == 1) return 0;

        int result = 0;
        for (int index = 2; index < n; index++) {
            for (int count = 2; count < index; count++) {
                //非质数
                if (index % count == 0) {
                    result++;
                    break;
                }
            }
        }
        //总数-非质数-
        return n - result - 2;
    }

    //暴力算法优化版本,也会超时
    public int countPrimes1(int n) {
        if (n < 3)  return 0;

        //大与3的数肯定有2这个质数
        int result = 1;
        for (int index = 3; index < n; index++) {
            if ((index & 1) == 0) continue;
            boolean isPrime = true;
            for (int count = 3; count * count <= index; count += 2) {
                if(index % count == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) result++;
        }
        //总数-非质数-
        return result;
    }

    //埃氏筛
    public int countPrimes2(int n) {
        int count = 0;

        BitSet bitSet = new BitSet(n);
        for (int index = 2; index < n; index++) {
            if (!bitSet.get(index)) {
                count++;
                for (int nums =index + index; nums < n; nums += index) {
                    bitSet.set(nums);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountPrime countPrime = new CountPrime();
        System.out.println(countPrime.countPrimes2(10));
        System.out.println(countPrime.countPrimes2(11));
        System.out.println(countPrime.countPrimes2(12));
        System.out.println(countPrime.countPrimes2(0));
        System.out.println(countPrime.countPrimes2(1));
        System.out.println(countPrime.countPrimes2(2));
        System.out.println(countPrime.countPrimes2(22));
    }
}
