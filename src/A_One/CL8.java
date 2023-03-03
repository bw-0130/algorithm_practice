package A_One;

import java.util.Stack;

/**
 * 何用栈结构实现队列结构
 */
public class CL8 {

    public static class myQueue{
        private Stack<Integer> setStack = new Stack<>();
        private Stack<Integer> getStack = new Stack<>();
        public int size = 0;

        public void push(int value){
            setStack.push(value);
            size++;
        }

        public void move(){
            while (!setStack.isEmpty()){
                getStack.push(setStack.pop());
            }
        }

        public int poll(){
            move();
            int res = getStack.pop();
            size--;
            return res;
        }

        public int getSize(){
            return size;
        }
    }

    public static void main(String[] args) {
        myQueue queue = new myQueue();
        queue.push(43);
        queue.push(22);
        queue.push(1);
        queue.push(6);
        System.out.println(queue.getSize());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
