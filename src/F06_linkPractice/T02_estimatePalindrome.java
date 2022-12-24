package F06_linkPractice;

import java.util.Stack;

/**
 * 给定一个单链表的头结点head，请判断该链表是否为回文结构
 */
public class T02_estimatePalindrome {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //使用栈实现
    public static boolean jobOne(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            Node stackNode = stack.pop();
            if (stackNode.value != head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean jobTwo(Node head){
        //找中点
        if (head == null || head.next== null){
            return false;
        }
        if (head.next.next == null){
            if (head.value == head.next.value){
                return true;
            }
            return false;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node rHead = slow.next;
        slow.next = null;
        //右半边链表反转
        Node pre = null;
        Node next = null;
        while (rHead != null){
            next = rHead.next;
            rHead.next = pre;
            pre = rHead;
            rHead = next;
        }
        Node newRHead = pre;
        Node lHead = head;
        while (newRHead != null){
            if (newRHead.value != lHead.value){
                return false;
            }
            newRHead = newRHead.next;
            lHead = lHead.next;
        }
        //还原链表
        rHead = pre;
        pre = null;
        next = null;
        while (rHead != null){
            next = rHead.next;
            rHead.next = pre;
            pre = rHead;
            rHead = next;
        }
        slow.next = pre;
        return true;
    }

    public static void printLink(Node head){
        while (head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(4);
        head.next.next = new Node(7);
       // head.next.next.next = new Node(7);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(10);
        printLink(head);
        System.out.println(jobOne(head));
        System.out.println(jobTwo(head));
        printLink(head);
    }

}
