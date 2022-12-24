package F06_linkPractice;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class T03_partitionLink {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node job(Node head, int value) {
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
            } else if (curValue > value) {
                if (maxStart == null) {
                    maxStart = head;
                    maxEnd = head;
                } else {
                    maxEnd.next = head;
                    maxEnd = head;
                }
            } else {
                if (midStart == null) {
                    midStart = head;
                    midEnd = head;
                } else {
                    midEnd.next = head;
                    midEnd = head;
                }
            }
            head = next;
        }
        if (minEnd != null) {
            minEnd.next = midStart;//小于区尾巴连接等于区的头
            midEnd = midEnd != null ? midEnd : minEnd;
        }
        if (midEnd != null) {
            midEnd.next = maxStart;
        }
        return minStart != null ? minStart : (midStart != null ? midStart : maxStart);
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
        head.next = new Node(5);
        head.next.next = new Node(3);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(4);
        printLink(head);
        Node jobHead = job(head, 5);
        printLink(jobHead);
    }

}
