package algorithm.tree;


/**
 * @author wangmingming160328
 * @Description 红黑树
 * @date @2019/12/16 9:56
 */
public class RBTree<T extends Comparable<T>> {
    public RBNode<T> mRoot = null;
    public final static boolean RED = true;
    public final static boolean BLACK = false;

    static class RBNode<T extends Comparable<T>> {
        //颜色
        private boolean color;
        //关键字（键值）
        private T key;
        //左子节点
        private RBNode<T> left;
        //右子节点
        private RBNode<T> right;
        //父节点
        private RBNode<T> parent;

        public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        private void setColor(boolean color) {
            this.color = color;
        }

        private RBNode<T> getParent() {
            return this.parent;
        }

        @Override
        public String toString() {
            T leftKey = left == null ? null : left.key;
            T rightKey = right == null ? null : right.key;
            T parentKey = parent == null ? null : parent.key;
            return "RBNode{" + "color=" + (color ? "红色" : "黑色") + ", key=" + key + ", left=" + leftKey + ", right=" + rightKey + ", parent=" + parentKey + '}';
        }
    }

    public void insert(T key) {
        if (key != null) {
            insert(new RBNode<T>(key, RED, null, null, null));
        }
    }

    public void insert(RBNode<T> node) {
        if (node == null) {
            return;
        }

        /*
        1.找到能够插入该节点的父节点
        从根节点开始对比，若值小于root的key值，则root更新为root的左子节点。若大于，则更新为root的右子节点。直到找到的父节点无对应的
         */
        RBNode<T> parent = null;
        RBNode<T> root = mRoot;
        while (root != null) {
            //当最后root为null时，parent为变为null之前的节点，此节点就是能够插入该节点的父节点
            parent = root;
            int cmp = node.key.compareTo(root.key);
            if (cmp < 0) {
                root = root.left;
            } else if (cmp > 0) {
                root = root.right;
            } else {
                throw new IllegalArgumentException(node.key + " is existed");
            }
        }
        //2.找到父节点后，对比父节点，小的就插入到父节点的左节点，大就插入到父节点的右节点上。
        if (parent == null) {
            mRoot = node;
        } else {
            int cmp = node.key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            node.parent = parent;
        }
        //3. 将它重新修整为一颗红黑树
        insertFixUp(node);
    }

    /**
     * 平衡红黑树
     *
     * @param node 要平衡的子树
     */
    private void insertFixUp(RBNode<T> node) {
        //定义父节点和祖父节点
        RBNode<T> parent, gParent;

        //需要修整的条件：父节点存在，且父节点的颜色是红色
        while (((parent = node.parent) != null) && parent.color == RED) {
            //获得祖父节点
            gParent = parent.parent;

            //若父节点是祖父节点的左子节点，下面else与其相反
            if (parent == gParent.left) {
                //获得叔叔节点
                RBNode<T> uncle = gParent.right;

                //case1: 叔叔节点也是红色
                if (uncle != null && uncle.color == RED) {
                    //把父节点和叔叔节点涂黑
                    parent.setColor(BLACK);
                    uncle.setColor(BLACK);
                    //把祖父节点涂红
                    gParent.setColor(RED);
                    //将位置放到祖父节点处
                    node = gParent;
                    //继续while，重新判断
                    continue;
                }

                //case2: 叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.right) {
                    //从父节点处左旋
                    leftRotate(parent);
                    //然后将父节点和自己调换一下，为下面右旋做准备
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3: 叔叔节点是黑色，且当前节点是左子节点
                //把父节点和叔叔节点涂黑
                parent.setColor(BLACK);
                //把祖父节点涂红
                gParent.setColor(RED);
                rightRotate(gParent);
            } else {
                //若父节点是祖父节点的右子节点,与上面的完全相反，本质一样的
                RBNode<T> uncle = gParent.left;

                //case1: 叔叔节点也是红色
                if (uncle != null && uncle.color == RED) {
                    //把父节点和叔叔节点涂黑
                    parent.setColor(BLACK);
                    uncle.setColor(BLACK);
                    //把祖父节点涂红
                    gParent.setColor(RED);
                    node = gParent;
                    continue;
                }

                //case2: 叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3: 叔叔节点是黑色的，且当前节点是右子节点
                //把父节点和叔叔节点涂黑
                parent.setColor(BLACK);
                //把祖父节点涂红
                gParent.setColor(RED);
                leftRotate(gParent);
            }
        }

        //将根节点设置为黑色
        this.mRoot.setColor(BLACK);
    }

    /**
     * <P>对红黑树节点x进行左旋操作</P>
     *
     * <textarea cols="70" rows = "15">
     * 左旋示意图：对节点x进行左旋
     * p                       p
     * /                       /
     * x                       y
     * / \                     / \
     * lx  y    ----->     x  ry
     * / \                 / \
     * ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     * </textarea>
     *
     * @param x 要旋转的子树
     */
    private void leftRotate(RBNode<T> x) {
        RBNode<T> y = x.right;
        //1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
        if (y != null) {
            x.right = y.left;
            if (y.left != null) {
                y.left.parent = x;
            }
            //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
            y.parent = x.parent;
        }
        if (x.parent == null) {
            //x为根节点时
            this.mRoot = y;
        } else {
            //x不为根节点时
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        //3. 将y的左子节点设为x，将x的父节点设为y
        x.parent = y;
        if (y != null) {
            y.left = x;
        }
    }

    /**
     * <p>对红黑树节点y进行右旋操作</p>
     * <textarea cols="70" rows = "15">
     * * 左旋示意图：对节点y进行右旋
     * *        p                   p
     * *       /                   /
     * *      y                   x
     * *     / \                 / \
     * *    x  ry   ----->      lx  y
     * *   / \                     / \
     * * lx  rx                   rx ry
     * * 右旋做了三件事：
     * * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * * 3. 将x的右子节点设为y，将y的父节点设为x
     * </textarea>
     *
     * @param x 要旋转的子树
     */
    private void rightRotate(RBNode<T> x) {
        RBNode<T> y = x.left;
        if (y != null) {
            //1. 将y的右子节点赋给x的左子节点,并将x赋给y右子节点的父节点(y右子节点非空时)
            x.left = y.right;
            if (y.right != null) {
                y.right.parent = x;
            }
            //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
            y.parent = x.parent;
        }
        if (x.parent == null) {
            this.mRoot = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        //3.将x赋给y的右节点，y赋给x的父节点
        if (y != null) {
            y.right = x;
        }
        x.parent = y;
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    private void preOrder(RBNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    private void inOrder(RBNode<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.key + " ");
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    private void postOrder(RBNode<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.key + " ");
    }

    /**
     * 获得最小节点
     *
     * @return 最小节点
     */
    public RBNode<T> getMinNode() {
        return getMinNode(mRoot);
    }

    /**
     * 获得最小节点
     *
     * @param node 子节点
     * @return 最小节点
     */
    private RBNode<T> getMinNode(RBNode<T> node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 获得最大节点
     *
     * @return 最大节点
     */
    public RBNode<T> getMaxNode() {
        return getMaxNode(mRoot);
    }

    /**
     * 获得最大节点
     *
     * @param node 子节点
     * @return 最大节点
     */
    private RBNode<T> getMaxNode(RBNode<T> node) {
        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 找结点node的前驱结点：即，查找“二叉树中数据值小于该结点”的“最大结点”
     *
     * @param node 节点
     * @return 节点的前驱节点
     */
    public RBNode<T> predecessor(RBNode<T> node) {
        if (node == null) {
            return null;
        }
        //1.节点有左子树
        if (node.left != null) {
            return getMaxNode(node.left);
        }

        //2.节点本身是右节点，直接返回父节点。若是左节点，找到父节点的右节点是当前节点的节点。
        RBNode<T> parent = node.parent;
        while (parent != null && parent.left == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    public RBNode<T> successor(RBNode<T> node) {
        if (node == null) {
            return null;
        }

        //1.当前节点有右子树
        if (node.right != null) {
            return getMinNode(node.right);
        }

        //2.当前节点本身是左节点，直接返回父节点。若是右节点，找到节点的左节点是当前节点的最低父节点
        RBNode<T> parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    public RBNode<T> get(T key) {
        if (key == null) {
            return null;
        }

        return get(new RBNode<T>(key, RED, null, null, null));
    }

    private RBNode<T> get(RBNode<T> node) {
        if (node == null) {
            return null;
        }

        RBNode<T> rbNode = mRoot;
        while (rbNode != null) {
            int cmp = node.key.compareTo(rbNode.key);
            if (cmp < 0) {
                rbNode = rbNode.left;
            } else if (cmp > 0) {
                rbNode = rbNode.right;
            } else {
                return rbNode;
            }
        }
        return null;
    }

    public RBNode<T> getRecursion(T key) {
        if (key == null) {
            return null;
        }

        return getRecursion(new RBNode<T>(key, RED, null, null, null));
    }

    private RBNode<T> getRecursion(RBNode<T> node) {
        if (node == null) {
            return null;
        }

        RBNode<T> rbNode = mRoot;
        int cmp = node.key.compareTo(rbNode.key);
        if (cmp < 0) {
            rbNode = rbNode.left;
            return getRecursion(rbNode);
        } else if (cmp > 0) {
            rbNode = rbNode.right;
            return getRecursion(rbNode);
        } else {
            return node;
        }
    }

    public void remove(T key) {
        RBNode<T> node;
        if ((node = get(key)) != null) {
            remove(node);
        }
    }


    private void remove(RBNode<T> node) {
        /*
        case1、节点有左右子树
        有左右子树时，不能直接删除该节点。得将该节点的后继节点（比该节点的大的节点当中的最小的节点）找到并将key值替换到该节点，然后删除该后继节点，便能在删除后维持二叉树
        后继节点一定没有左节点：后继节点是右节点中的最小值，所以他不可能有左子节点。可能有右子节点，这样就满足2,3情况
         case2、节点无左右子树
        若节点无左右子树，只需将节点的父节点的左右子节点中等于该节点的节点置为null
        case3、节点有左子树或右子树，需要将子节点的父节点指向到该节点的父节点，同时将该节点的父节点的子节点指向子节点
        */
        //要删除的节点、子节点
        RBNode<T> deletedNode, childNode;
        boolean color;
        //1.找到要删除的节点
        if (node.left == null || node.right == null) {
            deletedNode = node;
        }else {
            deletedNode = successor(node);
        }

        //保存要删除节点的颜色，以此判断是否需要进行修复红黑树
        color = deletedNode.color;
        //2.找到子节点
        if (deletedNode.left != null) {
            childNode = deletedNode.left;
        }else {
            //若左节点为空，则子节点要么为空，要么就是右子节点。为空时和右子节点一样处理
            childNode = deletedNode.right;
        }

        //3.子节点关联被删除结点的父结点
        if (childNode != null) {
            childNode.parent = deletedNode.parent;
        }

        //被删除结点的父结点关联子节点
        if (deletedNode.parent == null) {
        //删除根节点
        mRoot = deletedNode;
        }else if (deletedNode.parent.left == deletedNode) {
            deletedNode.parent.left = childNode;
        }else {
            deletedNode.parent.right = childNode;
        }

        //5后继节点的情况
        if (deletedNode != node) {
            node.key = deletedNode.key;
        }

        if (color == BLACK) {
            removeFixUp(childNode, deletedNode.parent);
        }

        deletedNode = null;
    }

    /**
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     * 如果当前待删除节点是红色的，它被删除之后对当前树的特性不会造成任何破坏影响。
     * 而如果被删除的节点是黑色的，这就需要进行进一步的调整来保证后续的树结构满足要求。
     * 这里我们只修正删除的节点是黑色的情况：
     *
     * 调整思想：
     * 为了保证删除节点的父节点左右两边黑色节点数一致，需要重点关注父节点没删除的那一边节点是不是黑色。
     * 如果删除后父亲节点另一边比删除的一边黑色多，就要想办法平衡。
     * 1、把父亲节点另一边（即删除节点的兄弟树）其中一个节点弄成红色，也少了一个黑色。
     * 2、或者把另一边多的节点（染成黑色）转过来一个
     *状     态：
     * 1）、当前节点是黑色的，且兄弟节点是红色的（那么父节点和兄弟节点的子节点肯定是黑色的）；
     * 2）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的两个子节点均为黑色的；
     * 3）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的左子节点是红色，右子节点时黑色的；
     * 4）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的右子节点是红色，左子节点任意颜色。
     *
     * 以上四种情况中，2，3，4都是（当前节点是黑色的，且兄弟节点是黑色的）的子集。
     *
     * 参数说明：
     * @param node 删除之后代替的节点（后序节点）
     * @param parent 后序节点的父节点
     */
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        //兄弟节点
        RBNode<T> other;
        RBNode<T> root = mRoot;
        while ((node == null || node.color == BLACK) && node != root) {
            if (parent.left == node) {
                other = parent.right;
                if (other.color == RED) {
                    //case 1：x的兄弟w是红色的【对应状态1，将其转化为2，3，4】
                    other.color = BLACK;
                    parent.color = RED;
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || other.left.color == BLACK) && (other.right == null || other.right.color == BLACK)) {
                    //case 2：x的兄弟w是黑色，且w的两个孩子都是黑色的【对应状态2，利用调整思想1网树的根部做递归】
                    //当所以的兄弟节点的子节点都是黑色节点时，可以直接将兄弟节点变红，这样局部的红黑树颜色是符合定义的。但是整颗树不一定是符合红黑树定义的，需要往上追溯继续调整。
                    other.color = RED;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (other.right == null || other.right.color == BLACK) {
                        //case 3:x的兄弟w是黑色的，并且w的左孩子是红色的，右孩子是黑色的【状态3调整到状态4，保证有2个黑色节点可以借调】
                        other.left.color = BLACK;
                        other.color = RED;
                        rightRotate(other);
                        other = parent.right;
                    }
                    //case 4:x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色【对应状态4，利用调整思想2做调整】
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.right.color = BLACK;
                    leftRotate(parent);
                    node = root;
                    break;
                }
            } else {
                other = parent.left;
                if (other.color == RED) {
                    //case 1:x的兄弟w是红色的。让兄弟节点变黑，方便借调
                    other.color = BLACK;
                    parent.color = RED;
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || other.left.color == BLACK) && (other.right == null || other.right.color == BLACK)) {
                    //case 2:x兄弟w是黑色，且w的两个孩子也都是黑色的。达到局部平衡，然后向上追溯
                    other.color = RED;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (other.left == null || other.left.color == BLACK) {
                        //case 3:x的兄弟w是黑色左子树的，并且w的左孩子是黑色，右孩子为红色。
                        other.right.color = BLACK;
                        other.color = RED;
                        leftRotate(other);
                        other = parent.left;
                    }
                    //case 4:x的兄弟w是黑色的；并且w的左孩子是红色的，右孩子任意颜色。
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.left.color = BLACK;
                    rightRotate(parent);
                    node = root;
                    break;
                }
            }
        }
        if (node != null) {
            node.color = BLACK;
        }
    }


    /**
     * 清除树
     */
    public void clear() {
        clear(mRoot);
        mRoot = null;
    }

    /**
     * 清除
     *
     * @param node 树节点
     */
    private void clear(RBNode<T> node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            clear(node.left);
        }

        if (node.right != null) {
            clear(node.right);
        }
    }

    public void print() {
        if (mRoot != null) {
            print(mRoot, mRoot.key, 0);
        }
    }

    /**
     * key---节点的键值
     * direction--- 0:表示该节点是根节点
     * 1:表示该节点是它的父节点的左子节点
     * 2:表示该节点是它的父节点的右子节点
     */
    private void print(RBNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (0 == direction) {
                System.out.printf("%2d(B) is root\n", tree.key);
            } else {
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, tree.color == RED ? "R" : "b", key, direction == 1 ? "right" : "left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }
}
