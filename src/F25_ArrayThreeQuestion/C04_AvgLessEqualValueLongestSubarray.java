package F25_ArrayThreeQuestion;

/**
 * 给定一个数组arr，给定一个值v
 * 求子数组平均值小于等于v的最长子数组长度
 */
public class C04_AvgLessEqualValueLongestSubarray {

    //暴力方法（O（N^3））
    public static int jobOne(int[] arr, int v) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                int len = j - i + 1;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                double avg = (double) sum / (double) len;
                if (avg <= v) {
                    res = Math.max(res, len);
                }
            }
        }
        return res;
    }

    //最优解法（O（N））
    public static int jobTwo(int[] arr, int v) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //进行转换
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= v;
        }
        return maxLengthAwesome(arr, 0);
    }

    public static int maxLengthAwesome(int[] arr, int k) {
        int size = arr.length;
        int[] minSums = new int[size];
        int[] minSumEnds = new int[size];
        //初始化辅助数组
        minSums[size - 1] = arr[size - 1];
        minSumEnds[size - 1] = size - 1;
        for (int i = size - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        //正式逻辑开始
        int end = 0;
        int res = 0;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            while (end < size && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return res;
    }

    //测试用
    public static int[] createArray(int len, int value) {
        int size = (int) (Math.random() * len + 1);
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int) (Math.random() * value + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            int v = (int) (Math.random() * 30 + 1);
            int[] array = createArray(50, 100);
            int one = jobOne(array, v);
            int two = jobTwo(array, v);
            if (one != two){
                System.out.println("oops! "+ one + "--"+two);
            }
        }
        System.out.println("执行完毕！");
    }

}
