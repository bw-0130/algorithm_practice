package A_One.T15;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组，返回数组中每个元素左边比它小的第一个数和右边比它小的第一个数。返回一个二维数组
 */
public class C1 {

    public static int[][] jobOne(int[] arr) {
        int N = arr.length;
        int[][] res = new int[N][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer pop = stack.pop();
                res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                res[pop][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            res[pop][1] = -1;
        }
        return res;
    }

    public static int[][] jobTwo(int[] arr) {
        int N = arr.length;
        int[][] res = new int[N][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();
                for (Integer data : pop) {
                    res[data][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[data][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)]==arr[i]){
                stack.peek().add(i);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            for(Integer data : pop){
                res[data][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[data][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 3};
        int[][] ints = jobOne(arr);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(ints[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~");
        int[][] rest = jobTwo(arr);
        for (int i = 0; i < rest.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(rest[i][j]+" ");
            }
            System.out.println();
        }
    }

}
