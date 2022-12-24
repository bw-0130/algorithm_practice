package F15_monotonousStack;

import java.util.Stack;

/**
 * 给定一个非负数组arr，代表直方图，
 * 返回直方图的最大长方形面积
 */
public class C03_LargestRectangleInHistogram {

    public static int job(int[] arr) {
        int size = arr.length;
        int res = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer popIndex = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, (i - left - 1) * arr[popIndex]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer popIndex = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, (size - left - 1) * arr[popIndex]);
        }
        return res;
    }

    //自定数组替换stack
    public static int jobTwo(int[] arr) {
        int size = arr.length;
        int res = Integer.MIN_VALUE;
        int[] stack = new int[size];
        int sts = -1;
        for (int i = 0; i < size; i++) {
            while (sts != -1 && arr[stack[sts]] >= arr[i]) {
                int popInex = stack[sts--];
                int left = sts == -1?-1:stack[sts];
                res = Math.max(res, (i-left-1)*arr[popInex]);
            }
            stack[++sts] = i;
        }
        while (sts != -1){
            int popInex = stack[sts--];
            int left = sts == -1?-1:stack[sts];
            res = Math.max(res, (size-left-1)*arr[popInex]);
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入：heights = [2,1,5,6,2,3]
         * 输出：10
         */
        int[] arr = {2,1,5,6,2,3};
        System.out.println(job(arr));
        System.out.println(jobTwo(arr));
    }

}
