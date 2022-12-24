package F09_binaryTree;

/**
 * （难）给定一颗二叉树的头结点head，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
 * 解释“距离”：两个节点最短路径沿途节点数量
 */
public class T13_maxDistance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getMaxNodeNum(Node head){
        if (head == null){
            return 0;
        }
        return process(head).maxNodeNum;
    }

    public static class Info{
        public int maxNodeNum;
        public int height;

        public Info(int maxNodeNum, int height) {
            this.maxNodeNum = maxNodeNum;
            this.height = height;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        //和当前节点无关
        int p1 = Math.max(leftInfo.maxNodeNum, rightInfo.maxNodeNum);
        //和当前节点有关
        int p2 = leftInfo.height+rightInfo.height+1;
        return new Info(Math.max(p1, p2), height);
    }

    public static void main(String[] args) {
        Node head = new Node(12);
        head.left = new Node(5);
        head.left.left = new Node(2);
        head.left.right = new Node(9);
        head.right = new Node(18);
        head.right.left = new Node(15);
        head.right.right = new Node(19);
        System.out.println(getMaxNodeNum(head));
    }

}
