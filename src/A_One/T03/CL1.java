package A_One.T03;

import F03_dataStructureExercise.T01_singleLinkReversal;

/**
 * 单链表反转
 */
public class CL1 {
    //链表节点
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //链表反转
    public static Node job(Node head){
        if (head == null){
            return null;
        }
        Node next = null;
        Node pre = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
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

    public static void prientLink(Node head){
        while (head!=null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = createLink(10, 100);
        prientLink(head);
        Node newHead = job(head);
        prientLink(newHead);
    }

}
