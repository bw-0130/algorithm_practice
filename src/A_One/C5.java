package A_One;

import java.util.List;

/**
 * 多叉树序列化成二叉树表示
 */
public class C5 {
    //多叉树节点
    public static class Node {
        public int value;
        public List<Node> children;

        public Node(int value) {
            this.value = value;
        }
    }

    //二叉树节点
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    //多叉树转二叉树
    public static TreeNode transition(Node head) {
        if (head == null) {
            return null;
        }
        TreeNode node = new TreeNode(head.value);
        node.left = tran(head.children);
        return node;
    }

    public static TreeNode tran(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node node : children){
            TreeNode treeNode = new TreeNode(node.value);
            if (head == null){
                head = treeNode;
            }else {
                cur.right = treeNode;
            }
            cur = treeNode;
            cur.left = tran(node.children);
        }
        return head;
    }

}
