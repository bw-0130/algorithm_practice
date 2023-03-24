package A_One.T09;

import F09_binaryTree.T01_recursiveTraversal;

/**
 * 二叉树递归方式遍历
 */
public class C1 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    //前序遍历
    public static void jobOne(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value+" ");
        jobOne(head.left);
        jobOne(head.right);
    }
    //中序遍历
    public static void jobTwo(Node head){
        if (head == null){
            return;
        }
        jobTwo(head.left);
        System.out.print(head.value+" ");
        jobTwo(head.right);
    }
    
    //后续遍历
    public static void jobThree(Node head){
        if (head == null){
            return;
        }
        jobThree(head.left);
        jobThree(head.right);
        System.out.print(head.value+" ");
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);
        jobOne(head);
        System.out.println("~~~~~~~~~~~~~~~~~");
        jobTwo(head);
        System.out.println("~~~~~~~~~~~~~~~~~");
        jobThree(head);
    }

}
