package A_One.T14;

import java.awt.event.PaintEvent;
import java.util.LinkedList;

/**
 * 加油站的良好出发点问题
 * N个加油站组成一个环形，给定两个长度都是N的非负数组 oil和dis(N>1)，oil[i]代表 第i个加油站存的油可以跑多少千米，
 * dis[i]代表第i个加油站到环中下一个加油站相隔 多少千米。
 * 假设你有一辆油箱足够大的车，初始时车里没有油。如果车从第i个加油站出发，最终 可以回到这个加油站，那么第i个加油站就算良好出发点，否则就不算。
 * 请返回长度为N的boolean型数组res，res[i]代表第 i 个加油站是不是良好出发点。
 * 思路：
 * 创建一个两倍长度的数组（因为是环形）
 * 数组中放的是oil-dis的差值
 * 将数组转化成前缀和数组，注：从某一点出发累加和大于0标识能跑完一圈
 */
public class C3 {

    public static boolean[] job(int[] oil, int[] dis) {
        int N = oil.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = oil[i] - dis[i];
            arr[i + N] = oil[i] - dis[i];
        }
        //前缀和数组
        for (int i = 1; i < M; i++) {
            arr[i] = arr[i] + arr[i-1];
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!list.isEmpty() && arr[list.peekLast()]>=arr[i]){
                list.pollLast();
            }
            list.addLast(i);
        }
        boolean[] res = new boolean[N];
        for (int offset = 0,i = 0, j = N;j<M; offset = arr[i++],j++){
            if (arr[list.peekFirst()]-offset>=0){
                res[i] = true;
            }
            if (list.peekFirst()==i){
                list.pollFirst();
            }
            while (!list.isEmpty() && arr[list.peekLast()]>=arr[j]){
                list.pollLast();
            }
            list.addLast(j);
        }
        return res;
    }

    public static void main(String[] args) {
        /**
         * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
         * 输出: 3
         */
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        boolean[] job = job(gas, cost);
        for (int i = 0; i < job.length; i++) {
            System.out.println(job[i]);
        }
    }

}
