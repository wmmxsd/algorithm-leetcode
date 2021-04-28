package circularlinkedlist141;

import java.util.ArrayList;
import java.util.List;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 *
 * @author wangmingming160328
 * @Description
 * @date @2021/4/12 22:38
 */
public class CircularLinkedList {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        List<ListNode> nodeList = new ArrayList<>();
        while (head.next != null) {
            if (nodeList.contains(head.next)) {
                return true;
            }
            nodeList.add(head.next);
            head = head.next;
        }
        return false;
    }

    /**
     * 官方题解 龟兔赛跑算法
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        //兔子在乌龟的前面一个节点出发，避免无法满足while循环的条件
        while (slow != fast) {
            //遍历完后无环形链表，必须加上fast.next == null，因为循环体最后的fast = fast.next.next;如果fast.next为null，则会报空指针
            if (fast == null || fast.next == null) {
                return false;
            }
            //乌龟每次走一步
            slow = slow.next;
            //兔子每次走两步
            fast = fast.next.next;
        }
        //slow = fast（兔子和乌龟在环形链表中相遇）
        return true;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}