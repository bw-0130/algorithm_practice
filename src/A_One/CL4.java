package A_One;

/**
 * 使用双向链表实现队列
 */
public class CL4 {

    public static class Node{
        public int value;
        public Node next;
        public Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    //自定义队列  push poll getSize
    public static class queue{
        public Node head;
        public Node end;
        public int size;

        public void push(int value){
            Node cur = new Node(value);
            if (size == 0){
                head = cur;
                end = cur;
            }else {
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
            size++;
        }

        public Node poll(){
            if (end == null){
                return null;
            }
            Node pre = end.pre;
            Node res = end;
            pre.next = null;
            end = pre;
            res.pre = null;
            size--;
            return res;
        }

        public int getSize(){
            return size;
        }

        public void printQueue(){
            Node cur = head;
            while (cur != null){
                System.out.print(cur.value+" ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        queue q = new queue();
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.printQueue();
        System.out.println(q.poll().value);
        System.out.println(q.poll().value);
        q.printQueue();
    }

}
