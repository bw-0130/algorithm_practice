package A_One.T06;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class C3 {

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
        Node minS = null;
        Node minE = null;
        Node midS = null;
        Node midE = null;
        Node maxS = null;
        Node maxE = null;
        while (head != null) {
            Node next = head.next;
            head.next = null;
            if (head.value < value) {
                if (minS == null) {
                    minS = head;
                    minE = head;
                } else {
                    minE.next = head;
                    minE = head;
                }
            } else if (head.value == value) {
                if (midS == null) {
                    midS = head;
                    midE = head;
                } else {
                    midE.next = head;
                    midE = head;
                }
            } else {
                if (maxS == null) {
                    maxS = head;
                    maxE = head;
                } else {
                    maxE.next = head;
                    maxE = head;
                }
            }
            head = next;
        }
        //连接链表
        if (minE != null) {
            minE.next = midS;
            minE = midE != null ? midE : minE;
        }
        if (minE != null){
            minE.next = maxS;
        }
        return minS != null ? minS : (midS != null ? midS : maxS);
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
