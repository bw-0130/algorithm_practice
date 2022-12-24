package F24_DataQuantityGuessMethod;

import java.util.Map;
import java.util.TreeMap;

/**
 * 牛牛家里一共有n袋零食，第i袋零食的体积为V[i]，背包容量为W。
 * 牛牛想知道在总体积不超过背包容量的情况下，一共有多少种零食放法，体积为0也算一种放法
 * 1<=n<=30,1<=w<=2*10^9
 * v[i] (0<=v[i]<=10^9)
 */
public class C02_SnacksWays {

    public static int jobOne(int[] arr, int w) {
        return process(arr, 0, w);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == arr.length) {
            return 1;
        }
        int way1 = process(arr, index + 1, rest);
        int way2 = process(arr, index + 1, rest - arr[index]);
        return way1 + (way2 == -1 ? 0 : way2);
    }

    //动态规划方法
    public static int dbOne(int[] arr, int w) {
        int size = arr.length;
        int[][] dpMap = new int[size + 1][w + 1];
        for (int i = 0; i <= w; i++) {
            dpMap[size][i] = 1;
        }
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dpMap[i][j] = dpMap[i + 1][j] + (j - arr[i] >= 0 ? dpMap[i + 1][j - arr[i]] : 0);
            }
        }
        return dpMap[0][w];
    }

    //分治思想方法
    public static int jobTwo(int[] arr, int w) {
        int res = 0;
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] < w ? 2 : 1;
        }

        int mid = (arr.length - 1) >> 1;
        TreeMap<Integer, Integer> mapLeft = new TreeMap<>();
        res = processTwo(arr, 0, mid, w, 0, mapLeft);
        TreeMap<Integer, Integer> mapRight = new TreeMap<>();
        res += processTwo(arr, mid + 1, arr.length - 1, w, 0, mapRight);
        TreeMap<Integer, Integer> preMap = new TreeMap<>();
        int pre = 0;
        for (Map.Entry<Integer, Integer> data : mapRight.entrySet()) {
            pre += data.getValue();
            preMap.put(data.getKey(), pre);
        }
        for (Map.Entry<Integer, Integer> data : mapLeft.entrySet()) {
            Integer key = data.getKey();
            Integer value = data.getValue();
            Integer floor = preMap.floorKey(w - key);
            if (floor != null) {
                Integer preValue = preMap.get(floor);
                res += preValue * value;
            }
        }
        return res + 1;//一个不选也是一种放法
    }

    public static int processTwo(int[] arr, int index, int end, int w, int sum, TreeMap<Integer, Integer> map) {
        if (sum > w) {
            return 0;
        }
        if (index > end) {
            if (sum != 0) {//排除一个不选的情况，最后再算
                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        }
        int way = processTwo(arr, index + 1, end, w, sum, map);
        way += processTwo(arr, index + 1, end, w, sum + arr[index], map);
        return way;
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
        int w = 20;
        for (int i = 0; i < 200; i++) {
            int[] array = createArray(10, 30);
            int one = jobOne(array, w);
            int two = dbOne(array, w);
            int three = jobTwo(array, w);
            if (one != two || two != three){
                System.out.println("oops!");
            }
        }
        System.out.println("执行完毕!");
    }


}
