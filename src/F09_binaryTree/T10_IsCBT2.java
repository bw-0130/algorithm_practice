package F09_binaryTree;

/**
 * 判断一棵树是否是完全二叉树
 * 思路：递归套路
 * 递归参数：是否满二叉树，是否完全二叉树，树高度
 */
public class T10_IsCBT2 {

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
        Info process = process(head);
        return process.isCBT;
    }

    public static class Info {
        public boolean isFull;//是否满二叉树
        public boolean isCBT;//是否完全二叉树
        public int height;//树高度

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            }
            if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                isCBT = true;
            }
            if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                isCBT = true;
            }
        }
        return new Info(isFull, isCBT, height);
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        System.out.println(check(head));
    }

}
