package F12_violenceRecursion;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈
 * 要求：不能申请额外的数据结构，只能使用递归函数实现
 */
public class C05_ReverseStackUsingRecursive {

    //递归返回栈底元素，其他元素位置不变
    public static int getStackEnd(Stack<Integer> stack) {
        Integer popData = stack.pop();
        if (stack.isEmpty()){
            return popData;
        }
        int endData = getStackEnd(stack);
        stack.push(popData);
        return endData;
    }

    //反转栈
    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int data = getStackEnd(stack);
        reverse(stack);
        stack.push(data);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

}
