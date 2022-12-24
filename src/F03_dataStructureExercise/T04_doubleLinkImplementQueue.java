package F03_dataStructureExercise;

/**
 * 使用双向链表实现队列
 */
public class T04_doubleLinkImplementQueue {

    public static class Node{
        public int value;
        public Node next;
        public Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class queue{
        public int size;
        public Node head;
        public Node end;

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
            end = pre;
            end.next = null;
            res.pre = null;
            size--;
            return res;
        }

        public int getSize(){
            return size;
        }

        public void printQueue(){
            Node cur = head;
            while (cur!=null){
                System.out.print(cur.value+" ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        queue queue = new queue();
        System.out.println("size:"+queue.getSize());
        queue.push(10);
        queue.push(4);
        queue.push(8);
        queue.push(45);
        queue.printQueue();
        System.out.println("size:"+queue.getSize());
        System.out.println(queue.poll().value);
        System.out.println(queue.poll().value);
        queue.printQueue();
        System.out.println("size:"+queue.getSize());


    }

}
