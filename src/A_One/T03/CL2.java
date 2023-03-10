package A_One.T03;

/**
 * 双向链表反转
 */
public class CL2 {

    public static class Node{
        public int value;
        public Node next;
        public Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node[] job(Node head){
        Node end = head;
        Node next = null;
        Node pre = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return new Node[]{pre, end};
    }

    public static Node[] createLink(int len, int maxValue){
        Node head = new Node((int) (1+ Math.random()*maxValue));
        Node pre = head;
        for (int i = 0; i < len - 1; i++) {
            Node cur = new Node((int) (1+ Math.random()*maxValue));
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
        }
        return new Node[]{head, pre};
    }

    public static void prientLink(Node[] nodes){
        Node head = nodes[0];
        Node end = nodes[1];
        System.out.println("正向打印：");
        while (head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
        System.out.println("逆向打印：");
        while (end != null){
            System.out.print(end.value+" ");
            end = end.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node[] nodes = createLink(10, 100);
        prientLink(nodes);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Node[] newNodes = job(nodes[0]);
        prientLink(newNodes);
    }

}
