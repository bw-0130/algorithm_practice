package A_One.T06;

/**
 * 给定两个可能有换也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
 * 如果不相交，返回null。
 * 【要求】如果两个链表长度之和为N，时间复杂度请达到O（N），额外空间复杂度请达到O（1）
 */
public class C5 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node job(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = findLoop(head1);
        Node loop2 = findLoop(head2);
        if (loop1 == null && loop2 == null) {//无环
            return noLoop(head1, head2);
        } else {//有环
            return haveLoop(head1, loop1, head2, loop2);
        }
    }

    public static Node haveLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {//相交点在环外
            int n = 0;
            Node cur1 = head1;
            Node cur2 = head2;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n <= 0 ? head2 : head1;
            cur2 = cur1 == head1 ? head2 : head1;
            for (int i = 0; i < n; i++) {
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//相交点在环内
            Node next = loop1.next;
            while (next != loop1){
                if (next == loop2){
                    return loop2;
                }
            }
            return null;
        }
    }

    public static Node noLoop(Node head1, Node head2) {
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n <= 0 ? head2 : head1;
        cur2 = cur1 == head1 ? head2 : head1;
        for (int i = 0; i < n; i++) {
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;

    }

    public static Node findLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
