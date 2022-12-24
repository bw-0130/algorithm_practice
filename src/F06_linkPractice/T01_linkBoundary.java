package F06_linkPractice;

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
public class T01_linkBoundary {
    
    //奇数长度返回中点，偶数长度返回上中点
    public static Node findUpMid(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    //输入链表头节点，奇数长度返回中点，偶数长度返回下中点。
    public static Node findDownMid(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个。
    public static Node findPreUpMid(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node findPreDownMid(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node evenNum = creatLink(10, 100);
        Node oddNum = creatLink(9, 100);
        System.out.println("奇数长度返回中点，偶数长度返回上中点:");
        printLink(evenNum);
        System.out.println(findUpMid(evenNum).value);
        printLink(oddNum);
        System.out.println(findUpMid(oddNum).value);
        System.out.println("输入链表头节点，奇数长度返回中点，偶数长度返回下中点:");
        printLink(evenNum);
        System.out.println(findDownMid(evenNum).value);
        printLink(oddNum);
        System.out.println(findDownMid(oddNum).value);
        System.out.println("输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个:");
        printLink(evenNum);
        System.out.println(findPreUpMid(evenNum).value);
        printLink(oddNum);
        System.out.println(findPreUpMid(oddNum).value);
        System.out.println("输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个:");
        printLink(evenNum);
        System.out.println(findPreDownMid(evenNum).value);
        printLink(oddNum);
        System.out.println(findPreDownMid(oddNum).value);
    }
    

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    
    public static Node creatLink(int len, int maxValue){
        Node head = new Node((int) (1+ Math.random()*maxValue));
        Node pre = head;
        for (int i = 0; i < len - 1; i++) {
            Node cur = new Node((int) (1+ Math.random()*maxValue));
            pre.next = cur;
            pre = cur;
        }
        return head;
    }
    
    public static void printLink(Node head){
        while (head!=null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

}
