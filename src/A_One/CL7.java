package A_One;

import java.util.Stack;

/**
 * 实现一个特殊栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 */
public class CL7 {

    public static class myStack{
        Stack<Integer> dataStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int value){
            dataStack.push(value);
            if (minStack.isEmpty()){
                minStack.push(value);
            }else {
                if (minStack.peek()>value){
                    minStack.push(value);
                }
            }
        }

        public Integer pop(){
            if (dataStack.isEmpty()){
                return null;
            }
            int res = dataStack.pop();
            if (res == minStack.peek()){
                minStack.pop();
            }
            return res;
        }

        public Integer peek(){
            return dataStack.peek();
        }

        public Integer getMin(){
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        myStack stack = new myStack();
        stack.push(67);
        stack.push(89);
        stack.push(5);
        stack.push(4);
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
    }

}
