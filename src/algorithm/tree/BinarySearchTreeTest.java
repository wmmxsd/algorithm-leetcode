package algorithm.tree;

/**
 * @author wangmingming160328
 * @Description
 * @date @2019/12/11 14:31
 */
public class BinarySearchTreeTest {
    private int[] array = {8, 5, 10, 9, 12, 4, 6,};


    public static void main(String[] args) {
        BinarySearchTreeTest test = new BinarySearchTreeTest();
        test.array = new int[]{100, 50, 200, 20, 60, 150, 250, 1, 25, 55, 80, 170, 220, 270, 175, 210};
        test.array = new int[]{15, 10, 18, 5, 13, 16, 25, 1, 6, 12, 14};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        System.out.println("依次添加：");
        for (int value : test.array) {
            System.out.print(value + " ");
            tree.insert(value);
        }
        System.out.println("\nBFS：");
        tree.BFSWithQueue();
        System.out.println("\nDFS（递归）：");
        tree.DFSWithRecursion(tree.get(100));
        System.out.println("\nDFS（stack）：");
        tree.DFSWithStack(tree.get(100));
        System.out.println("\n前序遍历：");
        tree.preOrder();
        System.out.println("\n中序遍历：");
        tree.inOrder();
        System.out.println("\n后序遍历：");
        tree.postOrder();
        System.out.println("\n递归获取节点：");
        BinarySearchTree.BSTNode<Integer> node = tree.getRecursion(5);
        System.out.println(node);
        System.out.println("获取节点：");
        node = tree.get(5);
        System.out.println(node);
        System.out.println("获取最大节点：");
        node = tree.getMaxNode();
        System.out.println(node);
        System.out.println("获取最小节点：");
        node = tree.getMinNode();
        System.out.println(node);
        //key为210的节点的前驱节点
        System.out.println("key为210的节点的前驱节点为：" + tree.predecessor(tree.getRecursion(210)));
        //key为210的节点的后继节点
        System.out.println("key为210的节点的后继节点为：" + tree.successor(tree.getRecursion(210)));
        //key为80的节点的后继节点
        System.out.println("key为80的节点的后继节点为：" + tree.successor(tree.getRecursion(80)));
        BinarySearchTree.BSTNode<Integer> deletedNode0 = tree.delete(80);
        System.out.println(deletedNode0);
        BinarySearchTree.BSTNode<Integer> deletedNode1 = tree.delete(100);
        System.out.println(deletedNode1);
        BinarySearchTree.BSTNode<Integer> deletedNode2 = tree.delete(220);
        System.out.println(deletedNode2);

        tree.clear();
        System.out.println("销毁后先序遍历：");
        tree.preOrder();
    }
}
