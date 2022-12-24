package F09_binaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化与反序列化
 * ​1、先序方式序列化、反序列化
 * 2、后序方式序列化、反序列化
 * ​2、按层方式序列化、反序列化
 */
public class T04_serializationLink {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

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

    public static void printQueue(Queue<Node> data) {
        while (!data.isEmpty()) {
            Node poll = data.poll();
            if (poll == null) {
                System.out.print("null ");
            } else {
                System.out.print(poll.value + " ");
            }
        }
        System.out.println();
    }

    //前序方式序列化
    public static Queue<Node> preSerial(Node head) {
        Queue<Node> res = new LinkedList<>();
        preJob(head, res);
        return res;
    }

    public static void preJob(Node head, Queue<Node> res) {
        while (head == null) {
            res.offer(head);
            return;
        }
        res.offer(head);
        preJob(head.left, res);
        preJob(head.right, res);
    }

    //前序方式反序列化
    public static Node preDeserial(Queue<Node> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return preDesJob(data);
    }

    public static Node preDesJob(Queue<Node> data) {
        Node node = data.poll();
        if (node == null) {
            return node;
        }
        node.left = preDesJob(data);
        node.right = preDesJob(data);
        return node;
    }

    //后序方式序列化
    public static Queue<Node> endSerial(Node head) {
        Queue<Node> queue = new LinkedList<>();
        endJob(head, queue);
        return queue;
    }
    public static void endJob(Node head, Queue<Node> queue) {
        if (head == null) {
            queue.add(head);
            return;
        }
        endJob(head.left, queue);
        endJob(head.right, queue);
        queue.add(head);
    }

    //后序方式反序列化
    public static Node endDeserial(Queue<Node> data){
        if (data == null || data.size() == 0) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        while (!data.isEmpty()){
            stack.push(data.poll());
        }
        return endDesJob(stack);
    }
    public static Node endDesJob(Stack<Node> stack){
        Node node = stack.pop();
        if (node == null){
            return node;
        }
        node.right = endDesJob(stack);
        node.left = endDesJob(stack);
        return node;
    }

    //按层方式序列化
    public static Queue<Node> levelSerial(Node head){
        Queue<Node> queue = new LinkedList<>();
        levelJob(head, queue);
        return queue;
    }
    public static void levelJob(Node head,  Queue<Node> data){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        data.add(head);
        while (!queue.isEmpty()){
            Node pollNode = queue.poll();
            if (pollNode.left != null){
                queue.offer(pollNode.left);
                data.offer(pollNode.left);
            }else {
                data.offer(null);
            }
            if (pollNode.right != null){
                queue.offer(pollNode.right);
                data.offer(pollNode.right);
            }else {
                data.offer(null);
            }
        }
    }

    //按层方式反序列化
    public static Node levelDeserial(Queue<Node> data){
        if (data == null || data.size()==0){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Node head = data.poll();
        queue.offer(head);
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
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

        System.out.println("前序序列化和反序列化：");
        Queue<Node> preQueue = preSerial(head);
        //printQueue(preQueue);
        Node preNode = preDeserial(preQueue);
        printTree(preNode);
        System.out.println("后序序列化和反序列化：");
        Queue<Node> endQueue = endSerial(head);
        //printQueue(endQueue);
        Node endNode = endDeserial(endQueue);
        printTree(endNode);
        System.out.println("按层方式序列化和反序列化：");
        Queue<Node> levelQueue = levelSerial(head);
        //printQueue(levelQueue);
        Node levelNode = levelDeserial(levelQueue);
        printTree(levelNode);
    }

}
