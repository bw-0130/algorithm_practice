package F06_linkPractice;

/**
 * 给定两个可能有换也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
 * 如果不相交，返回null。
 * 【要求】如果两个链表长度之和为N，时间复杂度请达到O（N），额外空间复杂度请达到O（1）
 */
public class T05_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node find(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loopNode1 = findloopNode(head1);
        Node loopNode2 = findloopNode(head2);
        //无环情况下
        if (loopNode1 == null && loopNode2 == null) {
            return noLoop(head1, head2);
        } else {//有环情况下
            return haveLoop(head1, loopNode1, head2, loopNode2);
        }
    }

    //无环情况
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
        //如果两个链表最后一个节点不相同说明两个链表不相交
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n <= 0 ? head2 : head1;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);//取绝对值
        for (int i = 0; i < n; i++) {
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //有环情况
    public static Node haveLoop(Node head1, Node loop1, Node head2, Node loop2) {
        //相交点在环外
        if (loop1 == loop2) {
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
            n = Math.abs(n);//取绝对值
            for (int i = 0; i < n; i++) {
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//相交点在环内
            Node next = loop1.next;
            while (next != loop1) {
                if (next == loop2) {
                    return loop2;
                }
                next = next.next;
            }
            return null;
        }
    }

    public static Node findloopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
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
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
