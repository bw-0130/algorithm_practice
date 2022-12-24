package F03_dataStructureExercise;

import java.util.Stack;

/**
 * 实现一个特殊栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 */
public class T07_minStack {

    public static class myStack {
        private Stack<Integer> dataStack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public void push(int data) {
            dataStack.push(data);
            if (minStack.isEmpty()) {
                minStack.push(data);
            } else {
                Integer minData = minStack.peek();
                if (data < minData) {
                    minStack.push(data);
                }
            }
        }

        public Integer pop() {
            if (dataStack.isEmpty()) {
                return null;
            }
            Integer res = dataStack.pop();
            if (!minStack.isEmpty()) {
                Integer minData = minStack.peek();
                if (res == minData) {
                    minStack.pop();
                }
            }
            return res;
        }

        public Integer getMin() {
            return minStack.peek();
        }

        public Integer peek() {
            return dataStack.peek();
        }
    }

    public static void main(String[] args) {
        myStack myStack = new myStack();
        myStack.push(20);
        myStack.push(10);
        myStack.push(40);
        myStack.push(4);
        System.out.println(myStack.getMin());
        System.out.println(myStack.pop());
        System.out.println(myStack.getMin());
        System.out.println(myStack.peek());
    }

}
