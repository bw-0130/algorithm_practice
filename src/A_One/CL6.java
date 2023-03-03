package A_One;

/**
 * 用数组实现队列和栈
 */
public class CL6 {
    //getNextIndex push pop
    public static class Queue {
        public int size;
        public int[] data;
        public int head;
        public int end;
        public int maxSize;

        public Queue(int len) {
            size = 0;
            data = new int[len];
            head = 0;
            end = 0;
            maxSize = len;
        }

        public int getNextIndex(int index) {
            return index < maxSize - 1 ? index + 1 : 0;
        }

        public void push(int value) {
            if (size == maxSize) {
                System.out.println("队列已满");
                return;
            }
            data[end] = value;
            end = getNextIndex(end);
            size++;
        }

        public int pop() {
            if (size == 0) {
                System.out.println("队列已空");
                return -1;
            }
            int res = data[head];
            head = getNextIndex(head);
            size--;
            return res;
        }
    }

    //getNextIndex getPreIndex push pop
    public static class Stack {
        public int size;
        public int[] data;
        public int head;
        public int end;
        public int maxSize;

        public Stack(int len) {
            size = 0;
            data = new int[len];
            head = 0;
            end = 0;
            maxSize = len;
        }

        public int getNextIndex(int index) {
            return index < maxSize - 1 ? index + 1 : 0;
        }

        public int getPreIndex(int index) {
            return index > 0 ? index - 1 : maxSize - 1;
        }

        public void push(int value){
            if (size == maxSize){
                System.out.println("栈满了");
                return;
            }
            data[end] = value;
            end = getNextIndex(end);
            size++;
        }

        public int pop(){
            if (size == 0){
                System.out.println("栈空了");
                return -1;
            }
            end = getPreIndex(end);
            int res = data[end];
            size--;
            return res;
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue(5);
        System.out.println(queue.pop());
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.push(7);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Stack stack = new Stack(5);
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
