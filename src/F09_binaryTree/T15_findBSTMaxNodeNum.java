package F09_binaryTree;

/**
 * 给定一颗二叉树头节点head，返回子树是搜索二叉树的最大子树节点数量
 *
 */
public class T15_findBSTMaxNodeNum {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int job(Node head){
        if (head == null){
            return 0;
        }
        return process(head).maxBSTNodeNum;
    }

    public static class Info{
        public int maxBSTNodeNum;//最大搜索二叉树节点数
        public int maxNodeVal;//最大节点值
        public int minNodeVal;//最小节点值
        public int nodeNum;//整棵树节点数

        public Info(int maxBSTNodeNum, int maxNodeVal, int minNodeVal, int nodeNum) {
            this.maxBSTNodeNum = maxBSTNodeNum;
            this.maxNodeVal = maxNodeVal;
            this.minNodeVal = minNodeVal;
            this.nodeNum = nodeNum;
        }
    }

    public static Info process(Node node){
        if (node == null){
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int maxNodeVal = node.value;//最大节点值
        int minNodeVal = node.value;//最小节点值
        int nodeNum = 1;//整棵树节点数
        if (leftInfo != null){
            maxNodeVal = Math.max(maxNodeVal, leftInfo.maxBSTNodeNum);
            minNodeVal = Math.min(minNodeVal, leftInfo.minNodeVal);
            nodeNum += leftInfo.nodeNum;
        }
        if (rightInfo != null){
            maxNodeVal = Math.max(maxNodeVal, rightInfo.maxBSTNodeNum);
            minNodeVal = Math.min(minNodeVal, rightInfo.minNodeVal);
            nodeNum += rightInfo.nodeNum;
        }
        int maxBSTNodeNum = 0;
        //与当前节点有关
        if (leftInfo != null){
            maxBSTNodeNum = Math.max(maxBSTNodeNum, leftInfo.maxBSTNodeNum);
        }
        if (rightInfo != null){
            maxBSTNodeNum = Math.max(maxBSTNodeNum, rightInfo.maxBSTNodeNum);
        }
        //与当前节点有关
        int p = -1;
        boolean leftIsBST = leftInfo == null? true:(leftInfo.maxBSTNodeNum == leftInfo.nodeNum);//左树是否为搜索树
        boolean rightIsBST = rightInfo == null? true:(rightInfo.maxBSTNodeNum == rightInfo.nodeNum);//右树是否为搜索树
        if (leftIsBST && rightIsBST){
            boolean leftbo = leftInfo == null? true:(leftInfo.maxNodeVal<node.value);
            boolean rightbo = rightInfo == null? true:(rightInfo.minNodeVal>node.value);
            if (leftbo && rightbo){
                int leftSize = leftInfo == null?0:leftInfo.nodeNum;
                int rightSize = rightInfo==null?0:rightInfo.nodeNum;
                p = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(maxBSTNodeNum, p),maxNodeVal,minNodeVal,nodeNum);
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
