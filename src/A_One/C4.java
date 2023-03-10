package A_One;

/**
 * 一种特殊的单链表结构，除了next指针外还有一个random指针该指针可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】时间复杂度O(N)，额外空间复杂度O(1)
 * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class C4 {

    public static class Node {
        public int value;
        public Node next;
        public Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node job(Node head) {
        if (head == null) {
            return head;
        }
        //在原链表上增加节点
        Node cur = head;
        Node curNext = null;
        while (cur != null) {
            curNext = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = curNext;
            cur = curNext;
        }
        cur = head;
        Node newNode = null;
        while (cur != null) {
            newNode = cur.next;
            curNext = cur.next.next;
            newNode.random = cur.random.next;
            cur = curNext;
        }

        Node newNodeHead = head.next;
        cur = head;
        while (cur != null) {
            newNode = cur.next;
            curNext = cur.next.next;
            cur.next = curNext;
            newNode.next = curNext != null ? curNext.next : null;
            cur = curNext;
        }
        return newNodeHead;
    }

}
