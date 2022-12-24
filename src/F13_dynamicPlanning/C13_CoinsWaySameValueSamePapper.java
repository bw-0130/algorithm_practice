package F13_dynamicPlanning;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，认为值相同的货币没有任何不同，
 * 返回组成aim的方法数。
 * 例如：arr = {1,2,1,1,2,1,2}, aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3。
 */
public class C13_CoinsWaySameValueSamePapper {

    //暴力递归方法
    public static int jobOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            } else {
                hashMap.put(arr[i], 1);
            }
        }
        int[] mz = new int[hashMap.size()];
        int[] zhang = new int[hashMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            mz[index] = entry.getKey();
            zhang[index] = entry.getValue();
            index++;
        }
        return process(mz, zhang, 0, aim);
    }

    public static int process(int[] mz, int[] zhang, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == mz.length) {
            return rest == 0 ? 1 : 0;
        }
        int way = 0;
        for (int i = 0; i <= zhang[index]; i++) {
            way += process(mz, zhang, index + 1, rest - (i * mz[index]));
        }
        return way;
    }

    //动态规划方法
    public static int jobTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            } else {
                hashMap.put(arr[i], 1);
            }
        }
        int[] mz = new int[hashMap.size()];
        int[] zhang = new int[hashMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            mz[index] = entry.getKey();
            zhang[index] = entry.getValue();
            index++;
        }
        int N = mz.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int way = 0;
                for (int zh = 0; zh <= zhang[i] && mz[i] * zh <= j; zh++) {
                    way += dpMap[i + 1][j - (zh * mz[i])];
                }
                dpMap[i][j] = way;
            }
        }
        return dpMap[0][aim];
    }

    //动态规划斜率优化
    public static int jobThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            } else {
                hashMap.put(arr[i], 1);
            }
        }
        int[] mz = new int[hashMap.size()];
        int[] zhang = new int[hashMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            mz[index] = entry.getKey();
            zhang[index] = entry.getValue();
            index++;
        }
        int N = mz.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dpMap[i][j] = dpMap[i + 1][j];
                if (j - mz[i] >= 0) {
                    dpMap[i][j] += dpMap[i][j - mz[i]];
                }
                if (j - mz[i] * (zhang[i] + 1) >= 0) {
                    dpMap[i][j] -= dpMap[i][mz[i] * (zhang[i] + 1)];
                }
            }
        }
        return dpMap[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 2, 1, 2};
        int aim = 4;
        System.out.println(jobOne(arr, aim));
        System.out.println(jobTwo(arr, aim));
        System.out.println(jobThree(arr, aim));
    }

}
