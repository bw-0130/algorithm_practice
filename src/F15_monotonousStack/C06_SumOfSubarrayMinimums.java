package F15_monotonousStack;

/**
 * 给定一个数组arr，
 * 返回所有子数组最小值的累加和
 * 思路：
 * 以每一个元素做最小值求出它能构成的子数组，将这个值累加到结果中
 * 公式：当前元素a，左边第一个比他大的元素l，右边第一个比他大的元素r
 * 能构成的子数组数量是 （a-l）*（r-a）
 * 那么以元素a为最小值的子数组的结果是 （a-l）*（r-a）*a
 * 注意：右边第一比自己小的元素计算时不能包括相等情况，也就是说计算元素r时遇到相等就停止。
 */
public class C06_SumOfSubarrayMinimums {

    public static int[] getLeftArray(int[] arr) {
        int N = arr.length;
        int[] res = new int[N];
        int[] stack = new int[N];
        int size = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (size != 0 && arr[stack[size - 1]] >= arr[i]) {
                res[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            res[stack[--size]] = -1;
        }
        return res;
    }

    public static int[] getRightArray(int[] arr) {
        int N = arr.length;
        int[] res = new int[N];
        int[] stack = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && arr[stack[size - 1]] > arr[i]) {
                res[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            res[stack[--size]] = N;
        }
        return res;
    }

    public static int job(int[] arr){
        int res = 0;
        int[] leftArray = getLeftArray(arr);
        int[] rightArray = getRightArray(arr);
        for (int i = 0; i < arr.length; i++) {
            int data = arr[i];
            res += (i-leftArray[i])*(rightArray[i]-i)*data;
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入：arr = [3,1,2,4]
         * 输出：17
         */
        int[] arr = {3,1,2,4};
        System.out.println(job(arr));
    }

}
