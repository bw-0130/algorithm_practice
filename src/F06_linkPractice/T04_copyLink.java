package F06_linkPractice;

import java.util.HashMap;

/**
 * 一种特殊的单链表结构，除了next指针外还有一个random指针该指针可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】时间复杂度O(N)，额外空间复杂度O(1)
 * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class T04_copyLink {

    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int value) {
            this.val = value;
        }
    }

    //用hashmap实现，不满足要求
    public static Node copyJobOne(Node head) {
        if (head == null) {
            return head;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);
            newNode.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    //满足要求的复制方法
    public static Node copyJobTwo(Node head){
        if (head == null){
            return head;
        }
        Node cur = head;
        Node curNext = null;
        //1->1`->2->2`->3->3'
        while (cur != null){
            curNext = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = curNext;
            cur = curNext;
        }
        cur = head;
        Node newNode = null;
        while (cur != null){
            newNode = cur.next;
            curNext = cur.next.next;
            newNode.random = cur.random != null? cur.random.next : null;
            cur = curNext;
        }
        Node resNode = head.next;
        cur = head;
        while (cur!=null){
            newNode = cur.next;
            curNext = cur.next.next;
            cur.next = curNext;
            newNode.next = curNext!= null? curNext.next:null;
            cur = curNext;
        }
        return resNode;
    }

}
