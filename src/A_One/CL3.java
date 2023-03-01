package A_One;

/**
 * 链表删除指定节点
 */
public class CL3 {
    //链表节点
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node job(Node head, int deleteValue){
        while (head != null){
            if (head.value != deleteValue){
                break;
            }
            head = head.next;
        }
        Node cur = head;
        Node pre = head;
        while (cur!= null){
            if (cur.value == deleteValue){
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
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
        Node head = createLink(10, 10);
        prientLink(head);
        System.out.println();
        Node newHead = job(head, 5);
        prientLink(newHead);
    }

}
