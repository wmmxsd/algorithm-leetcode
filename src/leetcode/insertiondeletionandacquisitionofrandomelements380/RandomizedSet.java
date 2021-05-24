package insertiondeletionandacquisitionofrandomelements380;

import java.util.BitSet;
import java.util.Random;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 * <p>
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 * <p>
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 * <p>
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 * <p>
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 * <p>
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 * <p>
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 * <p>
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 * <p>
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wangmingming160328
 * @Description
 * @date @2021/5/11 21:55
 */
class RandomizedSet {
    //0和正整数
    private BitSet bitSet;
    //负整数
    private BitSet bitSet1;
    private StringBuilder stringBuilder;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        bitSet = new BitSet(100000);
        bitSet1 = new BitSet(100000);
        stringBuilder = new StringBuilder();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (val < 0) {
            if (bitSet1.get(Math.abs(val)))
                return false;
            bitSet1.set(Math.abs(val));
            stringBuilder.append(val).append(",");
            return true;
        }

        if (bitSet.get(val))
            return false;
        bitSet.set(val);
        stringBuilder.append(val).append(",");
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (val < 0) {
            if (bitSet1.get(Math.abs(val))) {
                bitSet1.set(Math.abs(val), false);
                int index = stringBuilder.indexOf(val + ",");
                //System.out.println(stringBuilder);
                stringBuilder = stringBuilder.delete(index, index + String.valueOf(val).length() + 1);
                //System.out.println(stringBuilder);
                return true;
            }
            return false;
        }

        if (bitSet.get(val)) {
            bitSet.set(val, false);
            int index = stringBuilder.indexOf(val + ",");
            //System.out.println(stringBuilder);
            stringBuilder = stringBuilder.delete(index, index + String.valueOf(val).length() + 1);
            //System.out.println(stringBuilder);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        String[] array = stringBuilder.toString().split(",");
        if (array.length == 0) throw new NullPointerException();
        if (array.length == 1) return Integer.parseInt(array[0]);
        Random random = new Random();
        return Integer.parseInt(array[random.ints(1, 0, array.length).toArray()[0]]);
    }

    public static void main(String[] args) {
        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();

        // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomSet.insert(3));
        System.out.println(randomSet.insert(-2));

        // 返回 false ，表示集合中不存在 2 。
        System.out.println(randomSet.remove(2));

        // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.insert(-3));
        System.out.println(randomSet.insert(-2));

        System.out.println(randomSet.remove(-2));
        System.out.println(randomSet.remove(3));

        System.out.println(randomSet.insert(-1));

        System.out.println(randomSet.remove(-3));

        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.insert(-2));
        System.out.println(randomSet.insert(-2));
        System.out.println(randomSet.insert(-2));
        System.out.println(randomSet.insert(1));

        // getRandom 应随机返回 1 或 2 。
        System.out.println(randomSet.getRandom());

        System.out.println(randomSet.insert(-2));


        // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        System.out.println(randomSet.remove(0));

        // 2 已在集合中，所以返回 false 。
        System.out.println(randomSet.insert(-3));
        System.out.println(randomSet.insert(1));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
