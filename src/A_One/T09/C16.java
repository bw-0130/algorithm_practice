package A_One.T09;

import F09_binaryTree.T16_maxSubBSTHead;

/**
 * 给定一颗二叉树的头结点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class C16 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public Node maxBSTNode;
        public int maxBSTTreeNum;
        public int maxVal;
        public int minVal;

        public Info(Node maxBSTNode, int maxBSTTreeNum, int maxVal, int minVal) {
            this.maxBSTNode = maxBSTNode;
            this.maxBSTTreeNum = maxBSTTreeNum;
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
    }

    public static Node job(Node head) {
        if (head == null) {
            return null;
        }
        Info process = process(head);
        return process.maxBSTNode;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        Node maxBSTNode = head;
        int maxBSTTreeNum = 0;
        int maxVal = head.value;
        int minVal = head.value;
        if (left != null) {
            maxVal = Math.max(maxVal, left.maxVal);
            minVal = Math.min(minVal, left.minVal);
            maxBSTTreeNum = Math.max(maxBSTTreeNum, left.maxBSTTreeNum);
            maxBSTNode = left.maxBSTNode;
        }
        if (right != null) {
            maxVal = Math.max(maxVal, right.maxVal);
            minVal = Math.min(minVal, right.minVal);
            if (right.maxBSTTreeNum > maxBSTTreeNum) {
                maxBSTTreeNum = right.maxBSTTreeNum;
                maxBSTNode = right.maxBSTNode;
            }
        }
        if (left == null ? true : (left.maxBSTNode == head.left && left.maxVal < head.value) &&
                right == null ? true : (right.maxBSTNode == head.right && right.minVal > head.value)) {
            maxBSTTreeNum = (left == null ? 0 : left.maxBSTTreeNum) + (right == null ? 0 : right.maxBSTTreeNum) + 1;
            maxBSTNode = head;
        }
        return new Info(maxBSTNode, maxBSTTreeNum, maxVal, minVal);
    }

    public static void main(String[] args) {
        Node head = new Node(12);
        head.left = new Node(5);
        head.left.left = new Node(2);
        head.left.right = new Node(9);
        head.right = new Node(18);
        head.right.left = new Node(15);
        head.right.right = new Node(19);
        System.out.println(job(head).value);
    }

}
