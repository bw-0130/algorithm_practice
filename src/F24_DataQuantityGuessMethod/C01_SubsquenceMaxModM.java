package F24_DataQuantityGuessMethod;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * 给定一个非负数组arr，和一个正数m。返回arr所有子序列中累加和%m之后的最大值
 */
public class C01_SubsquenceMaxModM {

    //暴力方法
    public static int jobOne(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();//枚举所有不重复的子序列累加和
        process(arr, 0, 0, set);
        int res = 0;
        for (Integer sum : set) {
            res = Math.max(sum % m, res);
        }
        return res;
    }

    public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
        } else {
            process(arr, index + 1, sum, set);
            process(arr, index + 1, sum + arr[index], set);
        }
    }

    //动态规划方法(数组元素累加和不大情况下)
    public static int dbOne(int[] arr, int m) {
        int N = arr.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        boolean[][] dpMap = new boolean[N][sum + 1];
        //baseCase
        for (int i = 0; i < N; i++) {
            dpMap[i][0] = true;
        }
        dpMap[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dpMap[i][j] = dpMap[i - 1][j];
                if (j - arr[i] >= 0) {
                    dpMap[i][j] |= dpMap[i - 1][j - arr[i]];
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= sum; i++) {
            if (dpMap[N - 1][i]) {
                res = Math.max(res, i % m);
            }
        }
        return res;
    }

    //动态规划方法（当数组累加和比较大但是m不大情况下）
    public static int dbTwo(int[] arr, int m) {
        int N = arr.length;
        boolean[][] dbMap = new boolean[N][m];
        //baseCase
        for (int i = 0; i < N; i++) {
            dbMap[i][0] = true;
        }
        dbMap[0][(arr[0] % m)] = true;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                dbMap[i][j] = dbMap[i - 1][j];
                int cur = arr[i] % m;
                if (cur <= j) {
                    dbMap[i][j] |= dbMap[i - 1][j - cur];
                } else {
                    dbMap[i][j] |= dbMap[i - 1][m + j - cur];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            if (dbMap[N - 1][i]) {
                res = i;
            }
        }
        return res;
    }

    //如果数组累加和很大，m也很大但是数组长度不大
    //采用分治的思想
    public static int jobTwo(int[] arr, int m) {
        int size = arr.length;
        int mid = (size - 1) >> 1;
        TreeSet<Integer> leftSet = new TreeSet<>();
        processTwo(arr, 0, mid, 0, m, leftSet);
        TreeSet<Integer> rightSet = new TreeSet<>();
        processTwo(arr, mid+1, size-1, 0, m, rightSet);
        int res = 0;
        for (Integer data : leftSet){
            res = Math.max(res, data+ rightSet.floor(m-1-data));
        }
        return res;
    }

    public static void processTwo(int[] arr, int index, int end, int sum, int m, TreeSet<Integer> set) {
        if (index == end + 1) {
            set.add(sum % m);
        } else {
            processTwo(arr, index + 1, end, sum, m, set);
            processTwo(arr, index + 1, end, sum + arr[index], m, set);
        }
    }

    //测试用
    public static int[] createArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int) (Math.random() * value) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int m = 4;
        for (int i = 0; i < 200; i++) {
            int[] array = createArray(20, 30);
            int one = jobOne(array, m);
            int two = dbOne(array, m);
            int three = dbTwo(array, m);
            int four = jobTwo(array, m);
            if (one != two || one != three || one != four) {
                System.out.println("one:" + one + " two:" + two + " three:" + three+ " four:" + four);
                System.out.println("oops!");
            }
        }
        System.out.println("执行完成！");
    }

}
