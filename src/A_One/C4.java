package A_One;

import F09_binaryTree.T04_serializationLink;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化与反序列化
 * ​1、先序方式序列化、反序列化
 * 2、后序方式序列化、反序列化
 * ​2、按层方式序列化、反序列化
 */
public class C4 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //二叉树打印
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    //先序方式序列化、反序列化
    public static Queue<Node> preSerial(Node head) {
        Queue<Node> res = new LinkedList<>();
        preJob(head, res);
        return res;
    }

    public static void preJob(Node head, Queue<Node> queue) {
        if (head == null) {
            queue.offer(head);
            return;
        }
        queue.offer(head);
        preJob(head.left, queue);
        preJob(head.right, queue);
    }

    public static Node preDeserial(Queue<Node> queue) {
        if (queue == null || queue.size() < 0) {
            return null;
        }
        return preJobD(queue);
    }

    public static Node preJobD(Queue<Node> queue) {
        Node node = queue.poll();
        if (node == null) {
            return node;
        }
        node.left = preJobD(queue);
        node.right = preJobD(queue);
        return node;
    }

    //后序方式序列化、反序列化
    public static Queue<Node> endSerial(Node head) {
        Queue<Node> res = new LinkedList<>();
        endJob(head, res);
        return res;
    }

    public static void endJob(Node head, Queue<Node> queue) {
        if (head == null) {
            queue.add(head);
            return;
        }
        endJob(head.left, queue);
        endJob(head.right, queue);
        queue.offer(head);
    }

    public static Node endDeserial(Queue<Node> queue) {
        if (queue == null || queue.size() < 0) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return endjobD(stack);
    }

    public static Node endjobD(Stack<Node> stack) {
        Node node = stack.pop();
        if (node == null) {
            return node;
        }
        node.right = endjobD(stack);
        node.left = endjobD(stack);
        return node;
    }

    //按层方式序列化、反序列化
    public static Queue<Node> levelSerial(Node head) {
        Queue<Node> res = new LinkedList<>();
        levelJob(head, res);
        return res;
    }

    public static void levelJob(Node head, Queue<Node> queue) {
        if (head == null) {
            return;
        }
        Queue<Node> temp = new LinkedList<>();
        temp.offer(head);
        queue.offer(head);
        while (!temp.isEmpty()) {
            Node poll = temp.poll();
            if (poll.left != null) {
                temp.offer(poll.left);
                queue.offer(poll.left);
            } else {
                queue.offer(null);
            }
            if (poll.right != null) {
                temp.offer(poll.right);
                queue.offer(poll.right);
            }else {
                queue.offer(null);
            }
        }
    }
    
    public static Node levelDeserial(Queue<Node> data){
        if (data == null || data.size()<0){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Node head = data.poll();
        queue.offer(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            node.left = data.poll();
            node.right = data.poll();
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.right = new Node(5);
        head.right.left = new Node(4);
        head.right.right = new Node(6);

        printTree(head);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        Queue<Node> preQueue = preSerial(head);
        Node preHead = preDeserial(preQueue);
        printTree(preHead);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        Queue<Node> endQueue = endSerial(head);
        Node endHead = endDeserial(endQueue);
        printTree(endHead);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        Queue<Node> levelQueue = levelSerial(head);
        Node levelHead = levelDeserial(levelQueue);
        printTree(levelHead);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
