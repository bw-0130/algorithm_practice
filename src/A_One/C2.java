package A_One;

import java.util.Stack;

/**
 * 二叉树非递归方式遍历
 */
public class C2 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //先序遍历
    public static void jobOne(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.value + " ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    //中序遍历
    public static void jobTwo(Node head) {
        Stack<Node> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                Node pop = stack.pop();
                System.out.print(pop.value + " ");
                head = pop.right;
            }
        }
    }

    //后序遍历
    public static void jobThree(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> res = new Stack<>();
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            res.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
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
        jobOne(head);
        System.out.println("~~~~~~~~~~~~~~~~~");
        jobTwo(head);
        System.out.println("~~~~~~~~~~~~~~~~~");
        jobThree(head);
    }

}
