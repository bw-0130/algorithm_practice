package F09_binaryTree;

/**
 * 给定一颗二叉树的头结点head，返回这颗二叉树是不是平衡二叉树
 * 平衡二叉树定义：整个二叉树中左树的最大高度和右树的最大高度的绝对值不超过1。
 * 列可能性：我可以向左树要什么？向右树要什么
 * 左树是否平衡二叉树
 * 右树是否平衡二叉树
 * 左树和右树高度差小于2
 * info属性 是否平衡二叉树  树高度
 */
public class T11_IsBalanced {

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
        Info process = process(head);
        return process.isBalanced;
    }

    public static class Info{
        public boolean isBalanced;//是否是平衡二叉树
        public int height;//树高度

        public Info(boolean isBST, int height) {
            this.isBalanced = isBST;
            this.height = height;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        boolean isBST = false;
        if (leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height-rightInfo.height) < 2){
            isBST = true;
        }
        return new Info(isBST, height);
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
