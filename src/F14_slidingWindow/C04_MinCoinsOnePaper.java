package F14_slidingWindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同不重要
 */
public class C04_MinCoinsOnePaper {

    //暴力递归方法
    public static int jobOne(int[] arr, int aim) {
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
        int next = process(arr, index + 1, rest - arr[index]);
        if (next != Integer.MAX_VALUE) {
            p2 = next + 1;
        }
        return Math.min(p1, p2);
    }

    public static int jobOneDP(int[] arr, int aim) {
        int n = arr.length;
        int[][] dpMap = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[n][i] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int p1 = dpMap[i + 1][j];
                int p2 = j - arr[i] >= 0 ? dpMap[i + 1][j - arr[i]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
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
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> data : map.entrySet()) {
            coins[index] = data.getKey();
            zhangs[index++] = data.getValue();
        }
        return new Info(coins, zhangs);
    }

    //暴力递归方法二
    public static int jobTwo(int[] arr, int aim) {
        Info info = getInfo(arr);
        return process(info.coins, info.zhangs, 0, aim);
    }

    public static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= zhangs[index] && (coins[index] * zhang) <= rest; zhang++) {
            int p = process(coins, zhangs, index + 1, rest - (coins[index] * zhang));
            if (p != Integer.MAX_VALUE) {
                res = Math.min(res, p + zhang);
            }
        }
        return res;
    }

    public static int jobTwoDP(int[] arr, int aim) {
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;
        int[][] dpMap = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[n][i] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang <= zhangs[i] && (coins[i] * zhang) <= j; zhang++) {
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

    //斜率优化（利用窗口最小值）
    public static int jobThree(int[] arr, int aim) {
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;
        int[][] dpMap = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dpMap[n][i] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < Math.min(aim + 1, coins[i]); j++) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(j);
                dpMap[i][j] = dpMap[i + 1][j];//使用零张
                // 当前面值 X
                // mod  mod + x   mod + 2*x   mod + 3 * x
                for (int r = j + coins[i]; r <= aim; r += coins[i]) {
                    while (!list.isEmpty() &&
                            (dpMap[i + 1][list.peekLast()] == Integer.MAX_VALUE || dpMap[i + 1][list.peekLast()] + compensate(list.peekLast(), r, coins[i]) >= dpMap[i + 1][r])) {
                        list.pollLast();
                    }
                    list.addLast(r);
                    int l = r - coins[i] * (zhangs[i] + 1);
                    if (list.peekFirst() == l) {
                        list.pollFirst();
                    }
                    dpMap[i][r] = dpMap[i + 1][list.peekFirst()] + compensate(list.peekFirst(), r, coins[i]);
                }
            }
        }
        return dpMap[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 3, 7, 9, 4, 7};
        int aim = 12;
        System.out.println(jobOne(arr, aim));
        System.out.println(jobOneDP(arr, aim));
        System.out.println(jobTwo(arr, aim));
        System.out.println(jobTwoDP(arr, aim));
        System.out.println(jobThree(arr, aim));
    }

}
