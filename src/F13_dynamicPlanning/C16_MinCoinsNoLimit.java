package F13_dynamicPlanning;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数。
 */
public class C16_MinCoinsNoLimit {

    //暴力递归方法
    public static int jobOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int process = process(arr, index + 1, rest - (zhang * arr[index]));
            if (process != Integer.MAX_VALUE) {
                res = Math.min(process + zhang, res);
            }
        }
        return res;
    }

    //动态规划方法
    public static int jobTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dpMap[N][i] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[i] <= j; zhang++) {
                    int process = dpMap[i + 1][j - (zhang * arr[i])];
                    if (process != Integer.MAX_VALUE) {
                        res = Math.min(process + zhang, res);
                    }
                }
                dpMap[i][j] = res;
            }
        }
        return dpMap[0][aim];
    }

    //动态规划斜率优化
    public static int jobThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dpMap[N][i] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dpMap[i][j] = dpMap[i + 1][j];
                if (j - arr[i] >= 0 && dpMap[i][j - arr[i]] != Integer.MAX_VALUE) {
                    dpMap[i][j] = Math.min(dpMap[i][j], dpMap[i][j - arr[i]] + 1);
                }
            }
        }
        return dpMap[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int aim = 6;
        System.out.println(jobOne(arr, aim));
        System.out.println(jobTwo(arr, aim));
        System.out.println(jobThree(arr, aim));
    }

}
