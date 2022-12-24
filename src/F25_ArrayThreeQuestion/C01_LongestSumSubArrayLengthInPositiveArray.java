package F25_ArrayThreeQuestion;

/**
 * 给定一个正整数数组组成的无序数组arr，给定一个正整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且长度是最大的，返回其长度？
 * 思路：使用滑动窗口
 */
public class C01_LongestSumSubArrayLengthInPositiveArray {

    //满足单调性，使用滑动窗口方法
    public static int jobOne(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int leftWin = 0;
        int rightWin = 0;
        int sum = 0;
        int len = 0;
        while (rightWin < arr.length){
            if (sum == k){
                len = Math.max(len, rightWin-leftWin+1);
            }else if (sum<k){
                rightWin++;
                if (rightWin == arr.length){
                    break;
                }
                sum += arr[rightWin];
            }else {
                sum -= arr[leftWin++];
            }
        }
        return len;
    }

    //暴力解法
    public static int rightJob(int[] arr, int k){
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (check(arr, i, j, k)){
                    res = Math.max(res, (j-i+1));
                }
            }
        }
        return res;
    }

    public static boolean check(int[] arr, int l, int r, int k){
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum == k;
    }

    //测试用
    public static int[] createArray(int len, int value){
        int size = (int)(Math.random()*len+1);
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int)(Math.random()*value+1);
        }
        return res;
    }

    public static void main(String[] args) {
        int k = 10;
        for (int i = 0; i < 50; i++) {
            int[] array = createArray(10, 30);
            int one = jobOne(array, k);
            int two = rightJob(array, k);
            if (one != two){
                System.out.println("oops!");
            }
        }
        System.out.println("执行完成！");
    }

}
