package algorithm.tree;

/**
 * @author wangmingming160328
 * @Description
 * @date @2019/12/17 17:34
 */
public class RBTreeTest {
    private static final int[] ARRAY = {10, 40, 30, 60, 90, 70, 20, 50, 80};
    /**
     * "插入"动作的检测开关(false，关闭；true，打开)
     */
    private static final boolean M_DEBUG_INSERT = true;
    /**
     * "删除"动作的检测开关(false，关闭；true，打开)
     */
    private static final boolean M_DEBUG_DELETE = true;

    public static void main(String[] args) {
        int i, ilen = ARRAY.length;
        RBTree<Integer> tree = new RBTree<>();

        System.out.print("== 原始数据: ");
        for (i = 0; i < ilen; i++) {
            System.out.printf("%d ", ARRAY[i]);
        }
        System.out.print("\n");

        for (i = 0; i < ilen; i++) {
            tree.insert(ARRAY[i]);
            // 设置mDebugInsert=true,测试"添加函数"
            if (M_DEBUG_INSERT) {
                System.out.printf("== 添加节点: %d\n", ARRAY[i]);
                System.out.print("== 树的详细信息: \n");
                tree.print();
                System.out.print("\n");
            }
        }
        System.out.print("== 前序遍历: ");
        tree.preOrder();
        System.out.print("\n== 中序遍历: ");
        tree.inOrder();
        System.out.print("\n== 后序遍历: ");
        tree.postOrder();
        System.out.print("\n");
        RBTree.RBNode<Integer> predecessorA = tree.predecessor(tree.get(80));
        System.out.println("80的前驱节点为：" + predecessorA);
        RBTree.RBNode<Integer> predecessorB = tree.predecessor(tree.get(50));
        System.out.println("50的前驱节点为：" + predecessorB);
        RBTree.RBNode<Integer> predecessorC = tree.predecessor(tree.get(70));
        System.out.println("70的前驱节点为：" + predecessorC);
        RBTree.RBNode<Integer> successorA = tree.successor(tree.get(60));
        System.out.println("60的后继节点为：" + successorA);
        RBTree.RBNode<Integer> successorB = tree.successor(tree.get(70));
        System.out.println("70的后继节点为：" + successorB);
        RBTree.RBNode<Integer> successorC = tree.successor(tree.get(90));
        System.out.println("90的后继节点为：" + successorC);
        RBTree.RBNode<Integer> successorD = tree.successor(tree.get(50));
        System.out.println("50的后继节点为：" + successorD);
        System.out.println("最小值:" + tree.getMinNode());
        System.out.println("最大值: " + tree.getMaxNode());
        tree.clear();
        System.out.println("树的详细信息:");
        tree.print();
        System.out.print("\n");
        // 设置mDebugDelete=true,测试"删除函数"
        if (M_DEBUG_DELETE) {
            for (i = 0; i < ilen; i++) {
//                tree.remove(ARRAY[i]);

//                System.out.print("== 删除节点: %d\n", ARRAY[i]);
                System.out.print("== 树的详细信息: \n");
//                tree.print();
                System.out.print("\n");
            }
        }
    }
}
