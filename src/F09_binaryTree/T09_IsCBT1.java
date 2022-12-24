package F09_binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ### 判断一棵树是否是完全二叉树
 * 思路：按层遍历
 * 原则：
 * 1、当遍历到一个节点有右孩子无左孩子直接返回false （不是完全二叉树）
 * 2、当遇到第一个左右孩子不双全的时候，剩下节点一定是叶子节点
 */
public class T09_IsCBT1 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    
    public static boolean check(Node head){
        if (head == null){
            return true;
        }
        return process(head);
    }
    
    public static boolean process(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        boolean temp = false;
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            if (pollNode.left != null){
                if (temp){
                    return false;
                }
                queue.add(pollNode.left);
            }else {
                temp = true;
            }
            if (pollNode.right != null){
                if (temp){
                    return false;
                }
                if (pollNode.left == null){
                    return false;
                }
                queue.add(pollNode.right);
            }else {
                temp = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        System.out.println(check(head));
    }

}
