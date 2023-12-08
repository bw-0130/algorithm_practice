package B_Two;

import F06_linkPractice.T02_estimatePalindrome;
import F06_linkPractice.T03_partitionLink;
import F06_linkPractice.T04_copyLink;

import javax.sound.midi.Soundbank;
import java.util.Set;
import java.util.Stack;

/**
 * 链表深度练习
 */
public class T06_linkPractice {

    //单链表节点
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //双向链表节点
    public static class doubleNode {
        public int value;
        public doubleNode next;
        public doubleNode pre;

        public doubleNode(int value) {
            this.value = value;
        }
    }

    public static Node createLink(int len, int maxValue) {
        Node head = new Node((int) (Math.random() * maxValue + 1));
        Node pre = head;
        for (int i = 0; i < len - 1; i++) {
            Node cur = new Node((int) (Math.random() * maxValue + 1));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static void printLink(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static doubleNode[] createDoubleLink(int len, int maxValue) {
        doubleNode head = new doubleNode((int) (Math.random() * maxValue + 1));
        doubleNode pre = head;
        for (int i = 0; i < len - 1; i++) {
            doubleNode cur = new doubleNode((int) (Math.random() * maxValue + 1));
            cur.pre = pre;
            pre.next = cur;
            pre = cur;
        }
        return new doubleNode[]{head, pre};
    }

    public static void printDoubleLink(doubleNode[] data) {
        doubleNode head = data[0];
        doubleNode end = data[1];
        System.out.print("正向打印： ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        System.out.print("反向打印： ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node linkData1 = createLink(10, 100);
        System.out.println("偶数：");
        printLink(linkData1);
        Node linkData2 = createLink(9, 100);
        System.out.println("奇数：");
        printLink(linkData2);
        linkBoun linkBoun = new linkBoun();

        /*System.out.println(linkBoun.praOne(linkData1).value);
        System.out.println(linkBoun.praOne(linkData2).value);*/
        /*------------------------------------------------------------------------*/
        /*System.out.println(linkBoun.praTwo(linkData1).value);
        System.out.println(linkBoun.praTwo(linkData2).value);*/
        /*------------------------------------------------------------------------*/
        /*System.out.println(linkBoun.praThree(linkData1).value);
        System.out.println(linkBoun.praThree(linkData2).value);*/
        /*------------------------------------------------------------------------*/
        /*System.out.println(linkBoun.praFour(linkData1).value);
        System.out.println(linkBoun.praFour(linkData2).value);*/
        /*------------------------------------------------------------------------*/
        /*tspa tspa = new tspa();
        Node head = new Node(10);
        head.next = new Node(4);
        head.next.next = new Node(7);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(10);
        System.out.println(tspa.job1(head));
        System.out.println(tspa.job2(head));
        printLink(head);*/
        /*------------------------------------------------------------------------*/
        Node head = new Node(10);
        head.next = new Node(5);
        head.next.next = new Node(3);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(4);
        printLink(head);
        partitionLink partitionLink = new partitionLink();
        Node jobHead = partitionLink.job(head, 5);
        printLink(jobHead);
    }

    /**
     * #### 链表边界练习
     * 1、输入链表头节点，奇数长度返回中点，偶数长度返回上中点。
     * 2、输入链表头节点，奇数长度返回中点，偶数长度返回下中点。
     * 3、输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个。
     * 4、输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个。
     */
    public static class linkBoun {
        public Node praOne(Node head) {
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slowNode = head.next;
            Node fastNode = head.next.next;
            while (fastNode.next != null && fastNode.next.next != null) {
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            return slowNode;
        }

        public Node praTwo(Node head) {
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slowNode = head.next;
            Node fastNode = head.next;
            while (fastNode.next != null && fastNode.next.next != null) {
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            return slowNode;
        }

        public Node praThree(Node head) {
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slowNode = head;
            Node fastNode = head.next.next;
            while (fastNode.next != null && fastNode.next.next != null) {
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            return slowNode;
        }

        public Node praFour(Node head) {
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slowNode = head;
            Node fastNode = head.next;
            while (fastNode.next != null && fastNode.next.next != null) {
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            return slowNode;
        }
    }

    /**
     * 给定一个单链表的头结点head，请判断该链表是否为回文结构
     */
    public static class tspa {
        public boolean job1(Node head) {
            Stack<Node> stack = new Stack<>();
            Node cur = head;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                if (pop.value != head.value) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        public boolean job2(Node head) {
            if (head == null || head.next == null) {
                return false;
            }
            if (head.next.next == null) {
                return head.value == head.next.value ? true : false;
            }
            Node slowNode = head.next;
            Node fastNode = head.next.next;
            while (fastNode.next != null && fastNode.next.next != null) {
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            Node rHead = slowNode.next;
            slowNode.next = null;//断掉
            Node next = null;
            Node pre = null;
            while (rHead != null) {
                next = rHead.next;
                rHead.next = pre;
                pre = rHead;
                rHead = next;
            }
            boolean res = true;
            Node newRHead = pre;
            while (newRHead != null) {
                if (newRHead.value != head.value) {
                    res = false;
                    break;
                }
                newRHead = newRHead.next;
                head = head.next;
            }
            newRHead = pre;
            pre = null;
            next = null;
            while (newRHead != null) {
                next = newRHead.next;
                newRHead.next = pre;
                pre = newRHead;
                newRHead = next;
            }
            slowNode.next = pre;
            return res;
        }
    }

    /**
     * 将单向链表按某值划分成左边小、中间相等、右边大的形式
     */
    public static class partitionLink {
        public Node job(Node head, int value) {
            if (head == null) {
                return head;
            }
            Node minStart = null;
            Node minEnd = null;
            Node midStart = null;
            Node midEnd = null;
            Node maxStart = null;
            Node maxEnd = null;
            while (head != null) {
                int curValue = head.value;
                Node next = head.next;
                head.next = null;
                if (curValue < value) {
                    if (minStart == null) {
                        minStart = head;
                        minEnd = head;
                    } else {
                        minEnd.next = head;
                        minEnd = head;
                    }
                } else if (curValue == value) {
                    if (midStart == null) {
                        midStart = head;
                        midEnd = head;
                    } else {
                        midEnd.next = head;
                        midEnd = head;
                    }
                } else if (curValue > value) {
                    if (maxStart == null) {
                        maxStart = head;
                        maxEnd = head;
                    } else {
                        maxEnd.next = head;
                        maxEnd = head;
                    }
                }
                head = next;
            }
            if (minEnd != null) {
                minEnd.next = midStart;
                midEnd = midEnd != null ? midEnd : minEnd;
            }
            if (midEnd != null) {
                midEnd.next = maxStart;
            }
            return minStart != null ? minStart : (midStart != null ? midStart : maxStart);
        }
    }

    /**
     * 一种特殊的单链表结构，除了next指针外还有一个random指针该指针可能指向链表中的任意一个节点，也可能指向null。
     * 给定一个由Node节点类型组成的无环单链表头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
     * 【要求】时间复杂度O(N)，额外空间复杂度O(1)
     * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
     */
    public static class copyLink {
        public static class Node1 {
            public int val;
            public Node1 next;
            public Node1 random;

            public Node1(int value) {
                this.val = value;
            }
        }

        public static Node1 job(Node1 head) {
            if (head == null) {
                return head;
            }
            Node1 cur = head;
            Node1 curNext = null;
            while (cur != null) {
                curNext = cur.next;
                cur.next = new Node1(cur.val);
                cur.next.next = curNext;
                cur = curNext;
            }
            cur = head;
            Node1 newNode = null;
            while (cur != null) {
                newNode = cur.next;
                curNext = cur.next.next;
                newNode.random = cur.random != null ? cur.random.next : null;
                cur = curNext;
            }
            Node1 res = head.next;
            cur = head;
            while (cur != null) {
                newNode = cur.next;
                curNext = cur.next.next;
                cur.next = curNext;
                newNode.next = curNext != null ? curNext.next : null;
                cur = curNext;
            }
            return res;
        }

    }

    /**
     * 给定两个可能有换也可能无环的单链表，头节点head1和head2。
     * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
     * 如果不相交，返回null。
     * 【要求】如果两个链表长度之和为N，时间复杂度请达到O（N），额外空间复杂度请达到O（1）
     */
    public static class ffin {
        public static Node findloopNode(Node head) {
            if (head == null || head.next == null || head.next.next == null) {
                return head;
            }
            Node slowNode = head;
            Node fastNode = head.next;
            while (slowNode != fastNode) {
                if (fastNode.next == null || fastNode.next.next == null) {
                    return null;
                }
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            fastNode = head;
            while (slowNode != fastNode) {
                slowNode = slowNode.next;
                fastNode = fastNode.next;
            }
            return slowNode;
        }

        public static Node job(Node head1, Node head2) {
            if (head1 == null || head2 == null) {
                return null;
            }
            Node loop1 = findloopNode(head1);
            Node loop2 = findloopNode(head2);
            if (loop1 == null && loop2 == null) {//无环情况
                return noLoop(head1, head2);
            } else {//有环情况
                return Loop(head1, loop1, head2, loop2);
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
            cur1 = n < 0 ? head2 : head1;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            for (int i = 0; i < n; i++) {
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }

        public static Node Loop(Node head1, Node loop1, Node head2, Node loop2) {
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
                cur1 = n < 0 ? head2 : head1;
                cur2 = cur1 == head1 ? head2 : head1;
                n = Math.abs(n);
                for (int i = 0; i < n; i++) {
                    cur1 = cur1.next;
                }
                while (cur1!=cur2){
                    cur1 = cur1.next;
                    cur2 = cur2.next;
                }
                return cur1;
            } else {
                Node next = loop1.next;
                while (next != loop1){
                    if (next == loop2){
                        return loop2;
                    }
                    next = next.next;
                }
                return null;
            }
        }
    }

}
