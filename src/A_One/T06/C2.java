package A_One.T06;

import java.util.Stack;

/**
 * 给定一个单链表的头结点head，请判断该链表是否为回文结构
 */
public class C2 {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //使用栈
    public static boolean jobOne(Node head){
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            Node data = stack.pop();
            if (head.value != data.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //不使用栈
    public static boolean jobTwo(Node head){
        boolean res = true;
         if (head == null || head.next == null){
            return false;
        }
        if (head.next.next == null){
            if (head.value == head.next.value){
                return true;
            }
            return false;
        }
        //找链表中点
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;
        System.out.println("~~"+mid.value);
        Node rHead = slow.next;//右半部分链表头
        mid.next = null;//截断链表
        //反转右半部分链表
        Node pre = null;
        Node next = null;
        while (rHead!=null){
            next = rHead.next;
            rHead.next = pre;
            pre = rHead;
            rHead = next;
        }
        Node newRHead = pre;//反转后右半部分链表头
        Node lHead = head;//左半部分链表头
        while (newRHead!= null){
            if (newRHead.value != lHead.value){
                res = false;
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
        mid.next = pre;
        return res;
    }

    public static void printLink(Node head){
        while (head!= null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(4);
        head.next.next = new Node(7);
         head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(10);
        printLink(head);
        System.out.println(jobOne(head));
        System.out.println(jobTwo(head));
        printLink(head);
    }

}
