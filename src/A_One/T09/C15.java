package A_One.T09;

import F09_binaryTree.T15_findBSTMaxNodeNum;

/**
 * 给定一颗二叉树头节点head，返回子树是搜索二叉树的最大子树节点数量
 */
public class C15 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public int maxCNN;//搜索二叉树最大子树节点数量
        public int maxVal;
        public int minVal;
        public int nodeNum;//树总节点数量

        public Info(int maxCNN, int maxVal, int minVal, int nodeNum) {
            this.maxCNN = maxCNN;
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.nodeNum = nodeNum;
        }
    }

    public static int job(Node head) {
        if (head == null) {
            return 0;
        }
        Info process = process(head);
        return process.maxCNN;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int maxVal = head.value;
        int minVal = head.value;
        int nodeNum = 1;
        if (left != null) {
            maxVal = Math.max(maxVal, left.maxVal);
            minVal = Math.min(minVal, left.minVal);
            nodeNum += left.nodeNum;
        }
        if (right != null) {
            maxVal = Math.max(maxVal, right.maxVal);
            minVal = Math.min(minVal, right.minVal);
            nodeNum += right.nodeNum;
        }
        int maxCNN = 0;
        //和当前节点无关
        if (left != null) {
            maxCNN = Math.max(maxCNN, left.maxCNN);
        }
        if (right != null) {
            maxCNN = Math.max(maxCNN, right.maxCNN);
        }
        //和当前节点有关
        int p = -1;
        boolean leftIsBST = left == null ? true : (left.maxCNN == left.nodeNum);
        boolean rightIsBST = right == null ? true : (right.maxCNN == right.nodeNum);
        if (leftIsBST && rightIsBST) {
            boolean leftT = left == null ? true : (left.maxVal < head.value);
            boolean rightT = right == null ? true : (right.minVal > head.value);
            if (leftT && rightT) {
                int leftSize = left == null ? 0 : left.nodeNum;
                int rightSize = right == null ? 0 : right.nodeNum;
                p = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(maxCNN, p), maxVal, minVal, nodeNum);
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
