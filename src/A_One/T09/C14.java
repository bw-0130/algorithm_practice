package A_One.T09;

import F09_binaryTree.T14_isFull;
import com.sun.org.apache.regexp.internal.RE;

/**
 * 判断一棵树是否是满二叉树
 */
public class C14 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean isFull;
        public int hight;

        public Info(boolean isFull, int hight) {
            this.isFull = isFull;
            this.hight = hight;
        }
    }

    public static boolean job(Node head) {
        if (head == null) {
            return true;
        }
        Info process = process(head);
        return process.isFull;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int hight = Math.max(left.hight, right.hight) + 1;
        boolean isFull = left.isFull && right.isFull && left.hight == right.hight;
        return new Info(isFull, hight);
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
