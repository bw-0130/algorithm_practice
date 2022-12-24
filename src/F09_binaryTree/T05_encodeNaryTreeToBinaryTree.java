package F09_binaryTree;

import java.util.LinkedList;
import java.util.List;

/**
 * 多叉树序列化成二叉树表示
 */
public class T05_encodeNaryTreeToBinaryTree {
    //多叉树节点
    public static class Node{
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
        }
    }
    //二叉树节点
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //多叉树转二叉树
    public static TreeNode transition(Node node){
        if (node == null){
            return null;
        }
        TreeNode head = new TreeNode(node.val);
        head.left = tran(node.children);
        return head;
    }
    public static TreeNode tran(List<Node> children){
        TreeNode head = null;
        TreeNode cur = null;
        for (Node node : children) {
            TreeNode treeNode = new TreeNode(node.val);
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

    //二叉树还原多叉树
    public static Node untransition(TreeNode treeNode){
        if (treeNode == null){
            return null;
        }
        Node node = new Node(treeNode.val);
        node.children = untran(treeNode.left);
        return node;
    }
    public static List<Node> untran(TreeNode treeNode){
        List<Node> list = new LinkedList<>();
        while (treeNode != null){
            Node node = new Node(treeNode.val);
            node.children = untran(treeNode.left);
            list.add(node);
            treeNode = treeNode.right;
        }
        return list;
    }

}
