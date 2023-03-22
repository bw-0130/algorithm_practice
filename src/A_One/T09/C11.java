package A_One.T09;

import F09_binaryTree.T11_IsBalanced;

/**
 * 给定一颗二叉树的头结点head，返回这颗二叉树是不是平衡二叉树
 * 平衡二叉树定义：整个二叉树中左树的最大高度和右树的最大高度的绝对值不超过1。
 * 列可能性：我可以向左树要什么？向右树要什么
 * 左树是否平衡二叉树
 * 右树是否平衡二叉树
 * 左树和右树高度差小于2
 * info属性 是否平衡二叉树  树高度
 */
public class C11 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean isBalanced;
        public int heigh;

        public Info(boolean isBalanced, int heigh) {
            this.isBalanced = isBalanced;
            this.heigh = heigh;
        }
    }

    public static boolean job(Node head){
        if (head == null){
            return true;
        }
        Info process = process(head);
        return process.isBalanced;
    }
    
    public static Info process(Node head){
        if (head == null){
            return new Info(true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        boolean isBalanced = false;
        if (left.isBalanced && right.isBalanced && Math.abs(left.heigh-right.heigh)<2){
            isBalanced = true;
        }
        int hight = Math.max(left.heigh, right.heigh)+1;
        return new Info(isBalanced, hight);
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        head.right.right.left = new Node(6);
        head.right.right.right = new Node(6);
        head.right.right.right.left = new Node(6);
        head.right.right.right.right = new Node(6);
        System.out.println(job(head));
    }

}
