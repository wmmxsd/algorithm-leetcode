package islandsnumber200;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * @author wangmingming160328
 * @Description
 * @date @2021/4/11 14:17
 */
public class IslandsNumber {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rowCounts = grid.length;
        int columnCounts = grid[0].length;
        int isLandsNums = 0;
        for (int row = 0; row < rowCounts; ++row) {
            for (int column = 0; column < columnCounts; ++column) {
                if (grid[row][column] == '1') {
                    ++isLandsNums;
                    dfs(grid, row, column);
                }
            }
        }

        return isLandsNums;
    }

    private void dfs(char[][] grid, int row, int column) {
        int rowCounts = grid.length;
        int columnCounts = grid[0].length;

        if (row < 0 || column < 0 || row >= rowCounts || column >= columnCounts || grid[row][column] == '0') return;

        grid[row][column] = '0';
        dfs(grid, row - 1, column);
        dfs(grid, row + 1, column);
        dfs(grid, row, column - 1);
        dfs(grid, row, column + 1);
    }

    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rowCounts = grid.length;
        int columnCounts = grid[0].length;
        int isLandsNums = 0;
        for (int row = 0; row < rowCounts; ++row) {
            for (int column = 0; column < columnCounts; ++column) {
                if (grid[row][column] == '1') {
                    grid[row][column] = '0';
                    ++isLandsNums;
                    //使用DFS扫描[r][c]的上下左右
                    Queue<Integer> queue = new LinkedBlockingQueue<>();
                    queue.offer(row * columnCounts + column);
                    while (!queue.isEmpty()) {
                        int id = queue.poll();
                        //值为1的元素的上下左右中为1的元素的下标加入队列，并且将1变为0，表示已访问
                        int r = id / columnCounts, c = id % columnCounts;
                        if (r - 1 > 0 && grid[r - 1][c] == '1') {
                            grid[r - 1][c] = '0';
                            queue.offer((r - 1) * columnCounts + c);
                        }

                        if (r + 1 < rowCounts && grid[r + 1][c] == '1') {
                            grid[r + 1][c] = '0';
                            queue.offer((r + 1) * columnCounts + c);
                        }

                        if (c - 1 > 0 && grid[r][c - 1] == '1') {
                            grid[r][c - 1] = '0';
                            queue.offer(r * columnCounts + c - 1);
                        }

                        if (c + 1 < columnCounts && grid[r][c + 1] == '1') {
                            grid[r][c + 1] = '0';
                            queue.offer(r * columnCounts + c + 1);
                        }
                    }
                }
            }
        }

        return isLandsNums;
    }

    public static void main(String[] args) {
        IslandsNumber islandsNumber = new IslandsNumber();
        char[][] islands = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(islandsNumber.numIslands(islands));
        System.out.println(islandsNumber.numIslands1(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));
    }
}
