package F03_dataStructureExercise;

/**
 * 单链表反转
 */
public class T01_singleLinkReversal {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node createLink(int len, int maxValue){
        Node head = new Node((int) (1+ Math.random()*maxValue));
        Node pre = head;
        for (int i = 0; i < len-1; i++) {
            Node cur = new Node((int) (1+ Math.random()*maxValue));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static void printLink(Node head){
        while (head != null){
            System.out.print(head.value+ " ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node reverssalLink(Node head){
        if (head == null){
            return null;
        }
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = createLink(10, 100);
        printLink(head);
        Node node = reverssalLink(head);
        printLink(node);
    }

}
