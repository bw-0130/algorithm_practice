package F03_dataStructureExercise;

import java.util.Stack;

/**
 * 何用栈结构实现队列结构
 */
public class T08_userStackImplementQueue {

    public static class myQueue{
        private Stack<Integer> stackSet = new Stack<>();
        private Stack<Integer> stackGet = new Stack<>();
        public int size = 0;

        private void move(){
            while (!stackSet.isEmpty()){
                stackGet.push(stackSet.pop());
            }
        }

        public void push(int data){
            stackSet.push(data);
            size++;
        }

        public int pop(){
            move();
            size--;
            return stackGet.pop();
        }
    }

    public static void main(String[] args) {
        myQueue queue = new myQueue();
        queue.push(10);
        queue.push(44);
        queue.push(33);
        queue.push(2);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

}
