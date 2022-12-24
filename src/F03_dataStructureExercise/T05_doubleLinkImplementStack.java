package F03_dataStructureExercise;

/**
 * 使用双向链表实现栈
 */
public class T05_doubleLinkImplementStack {

    public static class Node{
        public int value;
        public Node next;
        public Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class stack{
        public int size;
        public Node head;
        public Node end;

        public void push(int value){
            Node cur = new Node(value);
            if (size == 0){
                head = cur;
                end = cur;
            }else {
                cur.pre = end;
                end.next = cur;
                end = cur;
            }
            size++;
        }

        public Node poll(){
            if (end == null){
                return null;
            }
            Node res = end;
            end = res.pre;
            end.next = null;
            res.pre = null;
            size--;
            return res;
        }

        public int getSize(){
            return size;
        }

        public void printStack(){
            Node cur = head;
            while (cur != null){
                System.out.print(cur.value+" ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        stack stack = new stack();
        System.out.println("size:"+stack.getSize());
        stack.push(10);
        stack.push(4);
        stack.push(8);
        stack.push(45);
        stack.printStack();
        System.out.println("size:"+stack.getSize());
        System.out.println(stack.poll().value);
        System.out.println(stack.poll().value);
        stack.printStack();
        System.out.println("size:"+stack.getSize());
    }
}
