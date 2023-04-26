package A_One.T14;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同不重要
 */
public class C4 {

    public static int jobOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (arr.length == index) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = Integer.MAX_VALUE;
        int temp = process(arr, index + 1, rest - arr[index]);
        if (temp != Integer.MAX_VALUE) {
            p2 = temp + 1;
        }
        return Math.min(p1, p2);
    }

    public static int jobTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        int N = arr.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[N][i] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int p1 = dpMap[i + 1][j];
                int p2 = j - arr[i] < 0 ? Integer.MAX_VALUE : dpMap[i + 1][j - arr[i]];
                if (p2 != Integer.MAX_VALUE) {
                    p2 = p2 + 1;
                }
                dpMap[i][j] = Math.min(p1, p2);
            }
        }
        return dpMap[0][aim];
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> data : map.entrySet()) {
            coins[index] = data.getKey();
            zhangs[index] = data.getValue();
            index++;
        }
        return new Info(coins, zhangs);
    }

    public static int jobThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        Info info = getInfo(arr);
        return processThree(info.coins, info.zhangs, 0, aim);
    }

    public static int processThree(int[] coins, int[] zhangs, int index, int rest) {
        if (coins.length == index) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= zhangs[index] && (coins[index] * zhang) <= rest; zhang++) {
            int p = processThree(coins, zhangs, index + 1, rest - (coins[index] * zhang));
            if (p != Integer.MAX_VALUE) {
                res = Math.min(res, p + zhang);
            }
        }
        return res;
    }

    public static int jobFour(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[N][i] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang <= zhangs[i] && coins[i] * zhang <= j; zhang++) {
                    int p = dpMap[i + 1][j - (coins[i] * zhang)];
                    if (p != Integer.MAX_VALUE) {
                        res = Math.min(res, p + zhang);
                    }
                }
                dpMap[i][j] = res;
            }
        }
        return dpMap[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 3, 7, 9, 4, 7};
        int aim = 12;
        System.out.println(jobOne(arr, aim));
        System.out.println(jobTwo(arr, aim));
        System.out.println(jobThree(arr, aim));
        System.out.println(jobFour(arr, aim));
        /*System.out.println(jobThree(arr, aim));*/
    }

}
