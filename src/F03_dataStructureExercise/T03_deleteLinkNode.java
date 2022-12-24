package F03_dataStructureExercise;

/**
 * 链表删除指定节点
 */
public class T03_deleteLinkNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node creadLink(int len, int maxValue) {
        Node head = new Node((int) (1 + Math.random() * maxValue));
        Node pre = head;
        for (int i = 0; i < len - 1; i++) {
            Node cur = new Node((int) (1 + Math.random() * maxValue));
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

    public static Node deleteNode(Node head, int deleteValue) {
        //找到第一个不用删除的节点
        while (head != null){
            if (head.value != deleteValue){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == deleteValue){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node link = creadLink(10, 10);
        printLink(link);
        Node node = deleteNode(link, 1);
        printLink(node);
    }

}
