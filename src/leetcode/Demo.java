/**
 * @author wangmingming160328
 * @Description
 * @date @2020/8/11 0:39
 */
public class Demo {
    public int getDecimalValue(ListNode head) {
        int result = 0;
         while(head != null) {
            result = (result << 1) +  head.val;
            head = head.next;
        }
        return result;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }



    public static void main(String[] args) {
        Demo demo = new Demo();
       /* ListNode listNode = demo.new ListNode(1);
        listNode.next = demo.new ListNode(0);
        listNode.next.next =demo.new ListNode(1);
        System.out.println(demo.getDecimalValue(listNode));*/
        System.out.println(demo.hammingWeight(00000000000000000000000000000001));
    }
}
