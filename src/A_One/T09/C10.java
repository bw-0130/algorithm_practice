package A_One.T09;

import F09_binaryTree.T10_IsCBT2;

/**
 * 判断一棵树是否是完全二叉树
 * 思路：递归套路
 * 递归参数：是否满二叉树，是否完全二叉树，树高度
 */
public class C10 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public boolean isCBT;
        public boolean fullTree;
        public int hight;

        public Info(boolean isCBT, boolean fullTree, int hight) {
            this.isCBT = isCBT;
            this.fullTree = fullTree;
            this.hight = hight;
        }
    }

    public static boolean job(Node head) {
        if (head == null) {
            return true;
        }
        Info process = process(head);
        return process.isCBT;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info processLeft = process(head.left);
        Info processRight = process(head.right);
        int hight = Math.max(processLeft.hight, processRight.hight) + 1;
        boolean isFull = processLeft.fullTree && processRight.fullTree && processLeft.hight == processRight.hight;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            if (processLeft.isCBT && processRight.fullTree && processLeft.hight == processRight.hight + 1) {
                isCBT = true;
            }
            if (processLeft.fullTree && processRight.isCBT && processLeft.hight == processRight.hight) {
                isCBT = true;
            }
            if (processLeft.isCBT && processRight.isCBT && processLeft.hight == processRight.hight + 1) {
                isCBT = true;
            }
        }
        return new Info(isCBT, isFull, hight);
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        System.out.println(job(head));
    }

}
