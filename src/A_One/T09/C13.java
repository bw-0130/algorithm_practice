package A_One.T09;

import F09_binaryTree.T13_maxDistance;

/**
 * （难）给定一颗二叉树的头结点head，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
 * 解释“距离”：两个节点最短路径沿途节点数量
 */
public class C13 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public int maxNodeNum;
        public int hight;

        public Info(int maxNodeNum, int hight) {
            this.maxNodeNum = maxNodeNum;
            this.hight = hight;
        }
    }

    public static int job(Node head) {
        if (head == null) {
            return 0;
        }
        Info process = process(head);
        return process.maxNodeNum;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int hight = Math.max(left.hight, right.hight) + 1;
        int p1 = Math.max(left.maxNodeNum, right.maxNodeNum);
        int p2 = left.hight+right.hight+1;
        return new Info(Math.max(p1,p2), hight);
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
