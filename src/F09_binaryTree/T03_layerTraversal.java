package F09_binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树按层遍历
 */
public class T03_layerTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void traversal(Node head){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            System.out.print(pollNode.value+" ");
            if (pollNode.left != null){
                queue.offer(pollNode.left);
            }
            if (pollNode.right != null){
                queue.offer(pollNode.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        traversal(head);
    }

}
