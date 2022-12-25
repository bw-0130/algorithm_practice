package F25_ArrayThreeQuestion;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 * 思路：求每个位置往前推醉倒累加和为K，的最大长度
 * 25/12/2022 bianwei
 */
public class C02_LongestSumSubArrayLength {

    public static int jobOne(int[] arr, int k){
        if (arr == null || arr.length ==0){
            return 0;
        }
        // key:前缀和
        // value : 0~value这个前缀和是最早出现key这个值的
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//添加0的位置，否则会错过所有0开头的答案
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum-k)){
                len = Math.max(len, i - map.get(sum-k));
            }
            if (!map.containsKey(sum)){
                map.put(sum, i);
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
        for (int i = 0; i < 200 ; i++) {
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