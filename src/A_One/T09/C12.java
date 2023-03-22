package A_One.T09;

import F09_binaryTree.T12_isBST;

/**
 * 判断二叉树是否是搜索二叉树
 * 搜索二叉树定义：在整个二叉树每个节点的左树都比自己小，右树都比自己大，在经典搜索二叉树中没有重复值。
 */
public class C12 {
    
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    
    public static class Info{
        public boolean isBST;
        public int minValue;
        public int maxValue;

        public Info(boolean isBST, int minValue, int maxValue) {
            this.isBST = isBST;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
    
    public static boolean job(Node head){
        if (head == null){
            return true;
        }
        Info process = process(head);
        return process.isBST;
    }
    
    public static Info process(Node head){
        if (head == null){
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int minValue = head.value;
        if (left!=null){
            minValue = Math.min(minValue, left.minValue);
        }
        if (right != null){
            minValue = Math.min(minValue, right.minValue);
        }
        int maxValue = head.value;
        if (left != null){
            maxValue = Math.max(maxValue, left.maxValue);
        }
        if (right != null){
            maxValue = Math.max(maxValue, right.maxValue);
        }
        boolean isBST = true;
        if (left!=null && !left.isBST){
            isBST = false;
        }
        if (right!=null && !right.isBST){
            isBST = false;
        }
        if (left!=null && left.maxValue>=head.value){
            isBST = false;
        }
        if (right!=null && right.minValue<=head.value){
            isBST = false;
        }
        return new Info(isBST, minValue, maxValue);
    }

    public static void main(String[] args) {
        Node head = new Node(12);
        head.left = new Node(5);
        head.left.left = new Node(2);
        head.left.right = new Node(9);
        head.right = new Node(18);
        head.right.left = new Node(15);
        head.right.right = new Node(19);
        System.out.println(job(head));
    }
    
}
