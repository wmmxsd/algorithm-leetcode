package numberofbit1191;

/**
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *
 *  
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *  
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *  
 *
 * 提示：
 *
 * 输入必须是长度为 32 的 二进制串 。
 *  
 *
 * 进阶：
 *
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @Description
 * @date @2021/4/12 23:51
 */

public class NumberOfBiit1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        //每次循环n右移0,1,2,...,31,然后每次与1进行与运算，找出1的个数
        // for(int index = 0; index < 32; index++) {
        //     result += n >> index & 1;
        // }
        //n每次逻辑右移1位后赋给n，高位用0填充，然后每次与1进行与运算，找出1的个数，但是如果n为0则结束循环
        while(n != 0) {
            result += n & 1;
            n>>>=1;
        }
        return result;
    }

    public static void main(String[] args) {
        NumberOfBiit1 biit1 = new NumberOfBiit1();
        biit1.hammingWeight(11);
        biit1.hammingWeight(128);
        biit1.hammingWeight(-3);
    }
}
