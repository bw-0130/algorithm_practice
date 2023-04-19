package A_One.T13;

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
public class C13 {

    public static int jobOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i])+1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int[] mzs = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> data : map.entrySet()) {
            mzs[index] = data.getKey();
            zhangs[index] = data.getValue();
            index++;
        }
        return process(mzs, zhangs, 0, aim);
    }

    public static int process(int[] mzs, int[] zhangs, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == mzs.length) {
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i <= zhangs[index]; i++) {
            res += process(mzs, zhangs, index + 1, rest - (mzs[index] * i));
        }
        return res;
    }

    public static int jobTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int[] mzs = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> data : map.entrySet()) {
            mzs[index] = data.getKey();
            zhangs[index] = data.getValue();
            index++;
        }
        int N = mzs.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int res = 0;
                for (int k = 0; k <= zhangs[i] && (mzs[i] * k) <= j; k++) {
                    res += dpMap[i + 1][j - (mzs[i] * k)];
                }
                dpMap[i][j] = res;
            }
        }
        return dpMap[0][aim];
    }

    //斜率优化
    public static int jobThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int[] mzs = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> data : map.entrySet()) {
            mzs[index] = data.getKey();
            zhangs[index] = data.getValue();
            index++;
        }
        int N = mzs.length;
        int[][] dpMap = new int[N + 1][aim + 1];
        dpMap[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dpMap[i][j] = dpMap[i + 1][j];
                if (j - mzs[i] >= 0) {
                    dpMap[i][j] += dpMap[i][j - mzs[i]];
                }
                if (j - mzs[i] * (zhangs[i] + 1) >= 0) {
                    dpMap[i][j] -= dpMap[i+1][j - mzs[i] * (zhangs[i] + 1)];
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
