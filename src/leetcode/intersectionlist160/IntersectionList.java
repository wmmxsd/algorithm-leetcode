package intersectionlist160;

import java.util.ArrayList;
import java.util.List;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangmingming160328
 * @Description
 * @date @2021/4/12 23:07
 */
public class IntersectionList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        List<ListNode> headAList = new ArrayList<>();
        while (headA != null) {
            headAList.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (headAList.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 官方题解
     * 链表到最后一个节点后就各自指向另一个链表的头结点，若有相交点则会出现相等情况。无相交点时，会出现null = null，返回null如[1,2,3]，[4,5,6]，第4次循环时,aHead = null,bHead = null,第五次循环判断关系时null = null
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode aHead = headA, bHead = headB;
        while (aHead != bHead) {
            aHead = aHead == null ? headB : aHead.next;
            bHead = bHead == null ? headA : bHead.next;
        }
        return aHead;
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
