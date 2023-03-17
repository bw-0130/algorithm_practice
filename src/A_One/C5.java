package A_One;

import java.util.List;

/**
 * 多叉树序列化成二叉树表示
 */
public class C5 {
    //多叉树节点
    public static class Node{
        public int value;
        public List<Node> children;

        public Node(int value) {
            this.value = value;
        }
    }
    //二叉树节点
    public static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }



}
