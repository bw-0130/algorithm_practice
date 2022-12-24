package F09_binaryTree;

/**
 * 判断一棵树是否是满二叉树
 */
public class T14_isFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean check(Node head){
        if (head == null){
            return true;
        }
        return process(head).isFull;
    }

    public static class Info{
        public boolean isFull;
        public int height;

        public Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        return new Info(isFull, height);
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
