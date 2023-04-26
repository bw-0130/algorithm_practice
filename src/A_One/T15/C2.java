package A_One.T15;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，一定都可以算出（sub累加和）*（sub中的最小值）是什么，那么所有子数组中，这个值最大是多少？
 * 思路：将问题转换成依次将数组中每一个元素当做最小值求当前元素最大范围问题，也就是单调栈问题
 * 求当前元素左边第一个比它小和右边第一个比它小括起来的范围元素累加和 * 当前元素。
 */
public class C2 {

    public static int job(int[] arr){
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i-1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                Integer pop = stack.pop();
                int subSum = stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()]);
                res = Math.max(res, subSum * arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            int subSum = stack.isEmpty()?sum[N-1]:(sum[N-1]-sum[stack.peek()]);
            res = Math.max(res, subSum * arr[pop]);
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * Input: nums = [1,2,3,2]
         * Output: 14
         */
        int[] arr = {1,2,3,2};
        System.out.println(job(arr));
    }

}
