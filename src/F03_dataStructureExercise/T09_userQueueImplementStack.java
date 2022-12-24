package F03_dataStructureExercise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列结构实现栈结构
 */
public class T09_userQueueImplementStack {

    public static class myStack{
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> help = new LinkedList<>();

        public void push(int data){
            queue.offer(data);
        }

        public Integer pop(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            Integer res = queue.poll();
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }

        public Integer peek(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            Integer res = queue.poll();
            help.offer(res);
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }
    }

    public static void main(String[] args) {
        myStack stack = new myStack();
        stack.push(12);
        stack.push(4);
        stack.push(66);
        stack.push(21);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }

}
