package turnontheturntablelock752;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 * <p>
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 * <p>
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 * <p>
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 * https://leetcode-cn.com/problems/open-the-lock/
 *
 * @author wangmingming160328
 * @date @2021/4/16 18:25
 */
public class TurnOnTheTurntableLock {
    /**
     * 每次只能拨动一个拨轮且只能拨动一下，0000拨动一下可能是0001,0009,0010,0090,0100,0900,1000,9000，每一次拨动都会有八种可能，构成一个图，求拨到解锁密码的拨动次数，即BFS模型
     */
    public int openLock(String[] deadends, String target) {
        if (target == null || target.length() != 4)
            throw new IllegalArgumentException();

        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000"))
            return -1;

        if ("0000".equals(target))
            return 0;

        int result = 0;
        Queue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("0000");
        visited.add("0000");

        while (!queue.isEmpty()) {
            int size = queue.size();

            //每次只拨动一下的可能情况
            for (int index = 0; index < size; index++) {
                String pw = queue.poll();
                if (target.equals(pw)) {
                    return result;
                }
                //四个拨轮
                for (int count = 0; count < 4; count++) {
                    //上拨
                    String dialedUp = dialUp(pw, count);
                    if (!visited.contains(dialedUp)) {
                        queue.offer(dialedUp);
                        visited.add(dialedUp);
                    }

                    //下拨
                    String dialedDown = dialDown(pw, count);
                    if (!visited.contains(dialedDown)) {
                        queue.offer(dialedDown);
                        visited.add(dialedDown);
                    }
                }
            }
            result++;
        }

        //无法打开
        return -1;
    }

    /**
     * 双向BFS查找
     */
    public int openLock1(String[] deadends, String target) {
        if (target == null || target.length() != 4)
            throw new IllegalArgumentException();

        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000"))
            return -1;

        if ("0000".equals(target))
            return 0;

        int result = 0;
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();
            for (String cur : q1) {
                //q1和q2处于同一层时说明
                if (q2.contains(cur)) {
                    return result;
                }

                visited.add(cur);

                //四个拨轮
                for (int count = 0; count < 4; count++) {
                    //上拨
                    String dialedUp = dialUp(cur, count);
                    if (!visited.contains(dialedUp)) {
                        temp.add(dialedUp);
                    }

                    //下拨
                    String dialedDown = dialDown(cur, count);
                    if (!visited.contains(dialedDown)) {
                        temp.add(dialedDown);
                    }
                }
            }

            //本次是从上向下找，下一次就从下向上找。本次是从下向上找，下一次就从上向下找
            q1 = q2;
            q2 = temp;

            result++;
        }

        //无法打开
        return -1;
    }


    private String dialUp(String pw, int count) {
        char[] array = pw.toCharArray();
        array[count] = array[count] == '9' ? '0' : (char) ((int) array[count] + 1);
        return new String(array);
    }

    private String dialDown(String pw, int count) {
        char[] array = pw.toCharArray();
        array[count] = array[count] == '0' ? '9' : (char) ((int) array[count] - 1);
        return new String(array);
    }

    public static void main(String[] args) {
        TurnOnTheTurntableLock turnOnTheTurntableLock = new TurnOnTheTurntableLock();
        System.out.println(turnOnTheTurntableLock.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        System.out.println(turnOnTheTurntableLock.openLock(new String[]{"8888"}, "0009"));
        System.out.println(turnOnTheTurntableLock.openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"));
        System.out.println(turnOnTheTurntableLock.openLock(new String[]{"0000"}, "8888"));

        System.out.println(turnOnTheTurntableLock.openLock1(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        System.out.println(turnOnTheTurntableLock.openLock1(new String[]{"8888"}, "0009"));
        System.out.println(turnOnTheTurntableLock.openLock1(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"));
        System.out.println(turnOnTheTurntableLock.openLock1(new String[]{"0000"}, "8888"));
    }
}
