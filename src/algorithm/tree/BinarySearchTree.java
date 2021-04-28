package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wangmingming160328
 * @Description 二叉查找树
 * @date @2019/12/11 14:22
 */
public class BinarySearchTree<T extends Comparable<T>> {
    /**
     * 根结点
     */
    private BSTNode<T> mRoot;

    public static class BSTNode<T> {
        //关键字
        T key;
        //父结点
        BSTNode<T> parent;
        //左孩子
        BSTNode<T> left;
        //右孩子
        BSTNode<T> right;

        public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            T parentKey = parent == null ? null : parent.key;
            T leftKey = left == null ? null : left.key;
            T rightKey = right == null ? null : right.key;
            return "BSTNode{" + "key=" + key + ", parent=" + parentKey + ", left=" + leftKey + ", right=" + rightKey + '}';
        }
    }

    /**
     * 将结点key插入到二叉树中
     *
     * @param key 待插入节点
     */
    public void insert(T key) {
        //如果新建结点失败，则返回
        insert(this, key);
    }

    private void insert(BinarySearchTree<T> bst, T key) {
        BSTNode<T> newNode = new BSTNode<>(key, null, null, null);
        int cmp;
        BSTNode<T> parent = null;
        //x指向该树的根结点
        BSTNode<T> root = bst.mRoot;

        /*
         * 查找z的插入位置
         * 比较根结点x与新节点z之间的关系
         * 		这时，y结点指向根结点x,若z小于根结点，则x指向x的左子树；否则指向右子树
         * 			直到左/右子树为空
         * 此时，z.parent指向y
         * if y=null
         *		新节点z就是父结点；
         * else
         * 		比较z和y的大小，
         * 		if z<y
         * 			z插入到y的左孩子的位置
         * 		else
         * 			z插入到y的右孩子的位置
         */
        //1.插入操作先通过循环查找到待插入的节点的父节点
        while (root != null) {
            parent = root;
            cmp = newNode.key.compareTo(root.key);
            if (cmp < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        //2.找到父节点后，对比父节点，小的就插入到父节点的左节点，大就插入到父节点的右节点上。
        if (parent == null) {
            bst.mRoot = newNode;
        } else {
            cmp = newNode.key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.parent = parent;
        }
    }

    /**
     * 前序遍历二叉树
     */
    public void preOrder() {
        if (mRoot == null) {
            System.out.println((Object) null);
        }
        preOrder(mRoot);
    }

    private void preOrder(BSTNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历二叉树
     */
    public void inOrder() {
        inOrder(mRoot);
    }

    private void inOrder(BSTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    /**
     * 后序遍历二叉树
     */
    public void postOrder() {
        postOrder(mRoot);
    }

    private void postOrder(BSTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    /**
     * 查找节点(递归)
     *
     * @param t 节点key值
     * @return key所在的节点
     */
    public BSTNode<T> getRecursion(T t) {
        return getRecursion(mRoot, t);
    }

    private BSTNode<T> getRecursion(BSTNode<T> bstNode, T t) {
        if (bstNode == null) {
            return null;
        }
        int cmp = t.compareTo(bstNode.key);
        if (cmp < 0) {
            return get(bstNode.left, t);
        } else if (cmp > 0) {
            return get(bstNode.right, t);
        } else {
            return bstNode;
        }
    }

    /**
     * 查找节点
     *
     * @param t 节点key值
     * @return key所在的节点
     */
    public BSTNode<T> get(T t) {
        return get(mRoot, t);
    }

    private BSTNode<T> get(BSTNode<T> bstNode, T t) {
        BSTNode<T> tempNode = bstNode;
        if (tempNode == null) {
            return null;
        }
        while (tempNode != null) {
            int cmp = t.compareTo(tempNode.key);
            if (cmp < 0) {
                tempNode = tempNode.left;
            } else if (cmp > 0) {
                tempNode = tempNode.right;
            } else {
                return tempNode;
            }
        }
        return null;
    }

    /**
     * 获得最大节点
     *
     * @return 最大节点
     */
    public BSTNode<T> getMaxNode() {
        return max(mRoot);
    }

    private BSTNode<T> max(BSTNode<T> node) {
        BSTNode<T> maxNode = node;
        if (maxNode == null) {
            return null;
        }
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode;
    }

    /**
     * 获得最小节点
     *
     * @return 最小节点
     */
    public BSTNode<T> getMinNode() {
        return min(mRoot);
    }

    private BSTNode<T> min(BSTNode<T> node) {
        BSTNode<T> minNode = node;
        if (minNode == null) {
            return null;
        }
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }

    /**
     * 找结点node的前驱结点：即，查找“二叉树中数据值小于该结点”的“最大结点”
     *
     * @param node 节点
     * @return 节点的前驱节点
     */
    public BSTNode<T> predecessor(BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        //如果node存在左孩子，则“node的前驱结点”为“以其左孩子为根的子树的最大结点”
        if (node.left != null) {
            return max(node.left);
        }
        /*
         * 如果node没有左孩子，则node有以下两种可能
         * 1、node是“一个右孩子”，则“node的前驱结点”为“它的父结点”
         * 2、node是“一个左孩子”，则查找“node的最低的父结点，并且该父结点要具有右孩子”，
         * 					找到的这个“最低的父结点”就是“node的前驱结点”
         */
        BSTNode<T> parent = node.parent;
        //node是左孩子的情况，否则就是右孩子
        //(parent != null) && (node == parent.left)代表有最低父节点，但是这个父节点的值是大于该节点的值的
        //parent = null 代表没有前驱节点（无具有右孩子的最低父节点）
        //parent != null && node != parent.left代表 父节点有右孩子。
        while ((parent != null) && (node == parent.left)) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /**
     * 找结点node的后继结点：即，查找“二叉树中数据值大于该结点”的“最小结点”
     *
     * @param node 节点
     * @return 节点的后继节点
     */
    public BSTNode<T> successor(BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        //1、有右子树，获得最小值
        BSTNode<T> right = node.right;
        if (right != null) {
            return min(right);
        }
        //2、无右子树
        BSTNode<T> parent = node.parent;
        ////node是右孩子的情况，否则就是左孩子
        while (parent != null && node == parent.right) {
            node = parent;
            parent = node.parent;
        }

        return parent;
    }

    /**
     * 删除节点并返回已删除的节点
     *
     * @param key 节点key值
     * @return 已删除的节点
     */
    public BSTNode<T> delete(T key) {
        return delete(getRecursion(key));
    }

    /**
     * 删除节点并返回已删除的节点
     *
     * @param node 节点
     * @return 已删除的节点
     */
    public BSTNode<T> delete(BSTNode<T> node) {
        /*
        1、节点有左右子树
        有左右子树时，不能直接删除该节点。得将该节点的后继节点（比该节点的大的节点当中的最小的节点）找到并将key值替换到该节点，然后删除该后继节点，便能在删除后维持二叉树
        后继节点一定没有左节点：后继节点是右节点中的最小值，所以他不可能有左节点。可能有右节点，这样就满足2,3情况
         2、节点无左右子树
        若节点无左右子树，只需将节点的父节点的左右子节点中等于该节点的节点置为null
        3、节点有左子树或右子树，需要将子节点的父节点指向到该节点的父节点，同时将该节点的父节点的子节点指向子节点
        */
        //要删除的节点、子节点
        BSTNode<T> deletedNode, childNode, resultNode;

        if (node == null) {
            return null;
        }
        //1获取要删除的节点
        if (node.left == null || node.right == null) {
            deletedNode = node;
        } else {
            deletedNode = successor(node);
        }

        //2获取子结点，不管左右
        if (deletedNode.left != null) {
            childNode = deletedNode.left;
        } else {
            childNode = deletedNode.right;
        }

        //3如果存在子结点，就关联被删除结点的父结点。
        if (childNode != null) {
            childNode.parent = deletedNode.parent;
        }

        //4父节点处理
        //如果父结点是空，说明要删的是根结点
        if (deletedNode.parent == null) {
            //设置根为child(此时根只有一个结点或者没有结点)
            mRoot = childNode;
        } else if (deletedNode == deletedNode.parent.left) {
            deletedNode.parent.left = childNode;
        } else {
            deletedNode.parent.right = childNode;
        }

        //5后继节点的情况
        if (deletedNode != node) {
            node.key = deletedNode.key;
        }

        resultNode = deletedNode;
        deletedNode = null;
        return resultNode;
    }

    /**
     * 销毁二叉树
     */
    public void clear() {
        clear(mRoot);
        mRoot = null;
    }

    private void clear(BSTNode<T> tree) {
        if (tree == null) {
            return;
        }
        if (tree.left != null) {
            clear(tree.left);
        }
        if (tree.right != null) {
            clear(tree.right);
        }
    }

    /**
     * 打印二叉查找树
     * key:  结点的值
     * i  :  0,表示该结点是根结点
     * -1,表示该结点是它的父结点的左孩子
     * 1,表示该结点是它的父结点的右孩子
     */
    public void print() {
        if (mRoot != null) {
            print(mRoot, mRoot.key, 0);
        }
    }

    private void print(BSTNode<T> tree, T key, int i) {
        if (tree != null) {
            //根结点
            if (i == 0) {
                System.out.printf("%3d is root\n", tree.key);
            } else {
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, i == 1 ? "right" : "left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void BFSWithQueue() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        if (mRoot != null) {
            queue.add(mRoot);
        }

        while (!queue.isEmpty()){
            BSTNode<T> node = queue.poll();
            System.out.print(node.key +  " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void DFSWithRecursion(BSTNode<T> node){
        if (node == null) {
            return;
        }
        //在这里处理遍历到的BSTNode节点
        System.out.print(node.key + " ");
        if (node.left != null) {
            DFSWithRecursion(node.left);
        }
        if (node.right != null) {
            DFSWithRecursion(node.right);
        }
    }

    public void DFSWithStack(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        Stack<BSTNode<T>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            BSTNode<T> treeNode = stack.pop();

            //在这里处理遍历到的TreeNode
            System.out.print(treeNode.key + " ");
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }
}
