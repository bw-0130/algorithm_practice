package A_One.T03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列结构实现栈结构
 */
public class CL9 {

    public static class myStack {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> help = new LinkedList<>();

        public void push(int value) {
            queue.offer(value);
        }

        public int poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            int res = queue.poll();
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return res;
        }

        public Integer peek(){
            while (queue.size() > 1) {
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
        stack.push(34);
        stack.push(21);
        stack.push(67);
        stack.push(88);
        System.out.println(stack.peek());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
    }


}
