package F03_dataStructureExercise;

import java.util.HashMap;

/**
 * 双向链表反转
 */
public class T02_doubleLinkReversal {

    public static class Node {
        public int value;
        public Node next;
        public Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    public static HashMap<String, Node> createLink(int len, int maxValue) {
        Node head = new Node((int) (1 + Math.random() * maxValue));
        Node pre = head;
        for (int i = 0; i < len - 1; i++) {
            Node cur = new Node((int) (1 + Math.random() * maxValue));
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
        }
        HashMap<String, Node> hashMap = new HashMap<>();
        hashMap.put("head", head);
        hashMap.put("end", pre);
        return hashMap;
    }

    public static void printLink(HashMap<String, Node> hashMap){
        Node head = hashMap.get("head");
        Node end = hashMap.get("end");
        System.out.println("正序打印：");
        while (head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
        System.out.println("逆序打印：");
        while (end != null){
            System.out.print(end.value+" ");
            end = end.pre;
        }
        System.out.println();
    }

    public static HashMap<String, Node> reversalLink(Node head){
        Node end = head;
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        HashMap<String, Node> hashMap = new HashMap<>();
        hashMap.put("head", pre);
        hashMap.put("end", end);
        return hashMap;
    }

    public static void main(String[] args) {
        HashMap<String, Node> linkData = createLink(10, 100);
        printLink(linkData);
        HashMap<String, Node> reversalLinkResult = reversalLink(linkData.get("head"));
        printLink(reversalLinkResult);
    }

}
