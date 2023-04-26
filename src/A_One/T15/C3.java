package A_One.T15;

import java.util.Stack;

/**
 * 给定一个非负数组arr，代表直方图，
 * 返回直方图的最大长方形面积
 */
public class C3 {

    public static int jobOne(int[] arr) {
        int size = arr.length;
        int res = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, (i - left - 1) * arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, (size - left - 1) * arr[pop]);
        }
        return res;
    }

    //用数组代替栈结构
    public static int jobTwo(int[] arr) {
        int size = arr.length;
        int res = Integer.MIN_VALUE;
        int[] stack = new int[size];
        int offSet = -1;
        for (int i = 0; i < size; i++) {
            while (offSet != -1 && arr[stack[offSet]] >= arr[i]) {
                int pop = stack[offSet--];
                int left = offSet == -1 ? -1 : stack[offSet];
                res = Math.max(res, (i - left - 1) * arr[pop]);
            }
            stack[++offSet] = i;
        }
        while (offSet != -1) {
            int pop = stack[offSet--];
            int left = offSet == -1 ? -1 : stack[offSet];
            res = Math.max(res, (size - left - 1) * arr[pop]);
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入：heights = [2,1,5,6,2,3]
         * 输出：10
         */
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println(jobOne(arr));
        System.out.println(jobTwo(arr));
    }

}
