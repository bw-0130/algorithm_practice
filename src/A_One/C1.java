package A_One;
/**
 * #### 链表边界练习
 *
 * 1、输入链表头节点，奇数长度返回中点，偶数长度返回上中点。
 *
 * 2、输入链表头节点，奇数长度返回中点，偶数长度返回下中点。
 *
 * 3、输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个。
 *
 * 4、输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个。
 */
public class C1 {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node creatLink(int len, int maxValue){
        //int size = (int) (Math.random()*len+1);
        Node head = new Node((int) (Math.random()*maxValue+1));
        Node pre = head;
        for (int i = 0; i < len-1; i++) {
            Node cur = new Node((int) (Math.random()*maxValue+1));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public static void printLink(Node head){
        while (head!= null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node job(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        Node head = creatLink(5, 100);
        printLink(head);
        System.out.println(job(head).value);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        Node head2 = creatLink(6, 100);
        printLink(head2);
        System.out.println(job(head2).value);
    }

}
