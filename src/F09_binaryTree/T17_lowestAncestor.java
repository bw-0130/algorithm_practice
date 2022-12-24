package F09_binaryTree;

/**
 * 给定一颗二叉树的头结点head，和另外两个节点a和b，返回a和b的最低公共祖先
 * 最低公共祖先定义：两个节点同时往上走以一个相交的节点即使它们的最低公共祖先。
 * 可能性：
 * 和当前节点无关
 * 左树有最低公共祖先
 * 右树有最低公共祖先
 * 和当前节点有关
 * 当前节点就是最低公共祖先，左树有a右树有b或者左树有b右树有a
 * 当前节点就是a也是最低公共祖先，右树或左树有b
 * 当前节点就是b也是最低公共祖先，右树或左树有a
 * 递归参数：最低公共祖先节点、有没有节点a、有没有节点b
 */
public class T17_lowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node job(Node head, Node a, Node b) {
        if (head == null) {
            return head;
        }
        return process(head, a, b).lowestAncestorNode;
    }

    public static class Info {
        public Node lowestAncestorNode;
        public boolean isA;
        public boolean isB;

        public Info(Node lowestAncestorNode, boolean isA, boolean isB) {
            this.lowestAncestorNode = lowestAncestorNode;
            this.isA = isA;
            this.isB = isB;
        }
    }

    public static Info process(Node node, Node a, Node b) {
        if (node == null) {
            return new Info(null, false, false);
        }
        Info leftInfo = process(node.left, a, b);
        Info rightInfo = process(node.right, a, b);
        boolean isA = (node == a) || leftInfo.isA || rightInfo.isA;
        boolean isB = (node == b) || leftInfo.isB || rightInfo.isB;;
        Node lowestAncestorNode = null;
        if (leftInfo.lowestAncestorNode != null){
            lowestAncestorNode = leftInfo.lowestAncestorNode;
        }else if (rightInfo.lowestAncestorNode != null){
            lowestAncestorNode = rightInfo.lowestAncestorNode;
        }else {
            if (isA && isB){
                lowestAncestorNode = node;
            }
        }
        return new Info(lowestAncestorNode,isA, isB);
    }
    public static void main(String[] args) {
        Node head = new Node(12);
        head.left = new Node(5);
        head.left.left = new Node(2);
        head.left.right = new Node(9);
        head.right = new Node(18);
        head.right.left = new Node(15);
        head.right.right = new Node(19);
        System.out.println(job(head, head.right.right,  head.right.left).value);
    }
}
