package searchingTwoDimensionalMatrixII240;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * 通过次数131,606提交次数286,711
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @Description
 * @date @2021/5/24 21:37
 */
public class SearchingTwoDimensionalMatrixII {
    /*public boolean searchMatrix(int[][] matrix, int target) {
        for (int index = 0; index < matrix.length; index++) {
            int[] array = matrix[index];
            for (int count = 0; count < array.length; count++) {
                if (array[count] ==  target) {
                    return true;
                }
            }
        }
        return false;
    }*/

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, column = 0;

        while (row >= 0 && column < matrix[0].length) {
            if (matrix[row][column] > target) {
                row--;
                continue;
            }
            if (matrix[row][column] < target) {
                column++;
                continue;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
       /* int[][] matrix = new int[5][6];
        matrix[0] = new int[]{1,4,7,11,15};
        matrix[1] = new int[]{2,5,8,12,19};
        matrix[2] = new int[]{3,6,9,16,22};
        matrix[3] = new int[]{10,13,14,17,24};
        matrix[4] = new int[]{8,21,23,26,30};*/
        int[][] matrix = new int[1][3];
        matrix[0] = new int[]{1,3,5};
        SearchingTwoDimensionalMatrixII demo = new SearchingTwoDimensionalMatrixII();
        System.out.println(demo.searchMatrix(matrix, 5));
    }
}
