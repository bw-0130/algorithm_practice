package F20_morris;

/**
 * 求二叉树的最小深度
 */
public class C02_MinDepth {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //二叉树递归套路方法
    public static int jobOne(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    public static int process(Node cur) {
        if (cur.left == null && cur.right == null) {
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if (cur.left != null) {
            leftH = process(cur.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (cur.right != null) {
            rightH = process(cur.right);
        }
        return 1 + Math.min(leftH, rightH);
    }

    //morris遍历方法
    public static int jobTwo(Node head) {
        if (head == null) {
            return 0;
        }
        Node cur = head;
        Node mostRight = null;
        int curLeve = 0;//当前层数
        int res = Integer.MAX_VALUE;
        while (cur != null) {
            int rightLeve = 1;
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    rightLeve++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){//第一次到达该节点
                    mostRight.right = cur;
                    cur = cur.left;
                    curLeve++;
                    continue;
                }else {//第二次到达该节点
                    if (mostRight.left==null){
                        res = Math.min(res, curLeve);
                    }
                    curLeve -= rightLeve;
                    mostRight.right = null;
                }
            } else {
                curLeve++;
            }
            cur = cur.right;
        }
        return res;
    }


    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);

        System.out.println(jobOne(head));

        System.out.println(jobTwo(head));
    }

}
