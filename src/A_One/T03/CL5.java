package A_One.T03;

/**
 * 使用双向链表实现栈
 */
public class CL5 {

    public static class Node{
        public int value;
        public Node next;
        public Node pre;

        public Node(int value) {
            this.value = value;
        }
    }

    //push poll getSize printStack
    public static class Stack{
        public Node head;
        public Node end;
        public int size;

        public void push(int value){
            Node cur = new Node(value);
            if (size==0){
                head = cur;
                end = cur;
            }else {
                end.next = cur;
                cur.pre = end;
                end = cur;
            }
            size++;
        }

        public Node poll(){
            if (end == null){
                return null;
            }
            Node res = end;
            Node pre = end.pre;
            pre.next = null;
            end = pre;
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
        Stack stack = new Stack();
        stack.push(3);
        stack.push(6);
        stack.push(7);
        stack.push(9);
        stack.printStack();
        System.out.println(stack.poll().value);
        System.out.println(stack.poll().value);
        stack.printStack();
    }

}
