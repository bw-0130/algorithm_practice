package F09_binaryTree;

/**
 * 判断二叉树是否是搜索二叉树
 * 搜索二叉树定义：在整个二叉树每个节点的左树都比自己小，右树都比自己大，在经典搜索二叉树中没有重复值。
 */
public class T12_isBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean check(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static class Info {
        public boolean isBST;
        public int minVal;
        public int maxVal;

        public Info(boolean isBST, int minVal, int maxVal) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int minVal = node.value;
        if (leftInfo != null) {
            minVal = Math.min(minVal, leftInfo.minVal);
        }
        if (rightInfo != null) {
            minVal = Math.min(minVal, rightInfo.minVal);
        }
        int maxVal = node.value;
        if (leftInfo != null) {
            maxVal = Math.max(maxVal, leftInfo.maxVal);
        }
        if (rightInfo != null) {
            maxVal = Math.max(maxVal, rightInfo.maxVal);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.maxVal >= node.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.minVal <= node.value) {
            isBST = false;
        }
        return new Info(isBST, minVal, maxVal);
    }

    public static void main(String[] args) {
        Node head = new Node(12);
        head.left = new Node(5);
        head.left.left = new Node(2);
        head.left.right = new Node(9);
        head.right = new Node(18);
        head.right.left = new Node(15);
        head.right.right = new Node(19);
        System.out.println(check(head));
    }

}
