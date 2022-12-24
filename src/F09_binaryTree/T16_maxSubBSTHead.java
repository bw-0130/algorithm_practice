package F09_binaryTree;

/**
 * 给定一颗二叉树的头结点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class T16_maxSubBSTHead {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node job(Node head){
        if (head == null){
            return head;
        }
        return process(head).maxSubBSTHead;
    }

    public static class Info{
        public Node maxSubBSTHead;//最大的二叉搜索子树的头节点
        public int maxSubBSTNum;//最大二叉搜索子树节点数量
        public int maxNodeVal;//最大节点值
        public int minNodeVal;//最小节点值

        public Info(Node maxSubBSTHead, int maxSubBSTNum, int maxNodeVal, int minNodeVal) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTNum = maxSubBSTNum;
            this.maxNodeVal = maxNodeVal;
            this.minNodeVal = minNodeVal;
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
        Node maxSubBSTHead = node;//最大的二叉搜索子树的头节点
        int maxSubBSTNum = 0;//最大二叉搜索子树节点数量
        if (leftInfo!= null){
            maxNodeVal = Math.max(maxNodeVal, leftInfo.maxNodeVal);
            minNodeVal = Math.min(minNodeVal, leftInfo.minNodeVal);

            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTNum = leftInfo.maxSubBSTNum;
        }
        if (rightInfo!= null){
            maxNodeVal = Math.max(maxNodeVal, rightInfo.maxNodeVal);
            minNodeVal = Math.min(minNodeVal, rightInfo.minNodeVal);
            if (rightInfo.maxSubBSTNum>maxSubBSTNum){
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTNum = rightInfo.maxSubBSTNum;
            }
        }
        //与当前节点有关
        if (leftInfo == null?true:(leftInfo.maxSubBSTHead == node.left && leftInfo.maxNodeVal<node.value)
                &&rightInfo == null?true:(rightInfo.maxSubBSTHead == node.right && rightInfo.minNodeVal>node.value)){
            maxSubBSTHead = node;
            maxSubBSTNum = (leftInfo==null?0:leftInfo.maxSubBSTNum)+(rightInfo==null?0:rightInfo.maxSubBSTNum)+1;
        }
        return new Info(maxSubBSTHead, maxSubBSTNum,maxNodeVal,minNodeVal);
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
