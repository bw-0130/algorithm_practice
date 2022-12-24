package F09_binaryTree;

import java.util.Stack;

/**
 * 二叉树非递归方式遍历
 */
public class T02_noRecursiveTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //前序遍历
    public static void preTraversal(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node popNode = stack.pop();
            System.out.print(popNode.value + " ");
            if (popNode.right != null) {
                stack.push(popNode.right);
            }
            if (popNode.left != null) {
                stack.push(popNode.left);
            }
        }
    }

    //中序遍历
    public static void midTraversal(Node head) {
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            //左树全部压栈
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                Node popNode = stack.pop();
                System.out.print(popNode.value + " ");
                head = popNode.right;
            }
        }
    }

    public static void endTraversal(Node head){
        if (head == null){
            return;
        }
        Stack<Node> res = new Stack<>();
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
             head = stack.pop();
            res.push(head);
            if (head.left != null){
                stack.push(head.left);
            }
            if (head.right != null){
                stack.push(head.right);
            }
        }
        while (!res.isEmpty()){
            System.out.print(res.pop().value+" ");
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

        preTraversal(head);
        System.out.println();
        midTraversal(head);
        System.out.println();
        endTraversal(head);
    }

}
