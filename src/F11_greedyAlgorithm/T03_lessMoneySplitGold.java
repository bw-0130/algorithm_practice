package F11_greedyAlgorithm;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度一样的铜板的。
 * 比如长度为20的金条，不管怎么切都要花费20铜板。一群人想分整块金条，怎么分最省铜板？
 * 例如，给定数组[10,20,30]，代表一共三个人，整块金条长度60，金条要分成10,20,30三个部分。
 * 输入一个数组，返回分割最小代价。
 * 斐波那契数列
 */
public class T03_lessMoneySplitGold {

    //暴力递归
    public static int jobOne(int[] data){
        if (data == null || data.length == 0){
            return 0;
        }
        return processOne(data, 0);
    }

    public static int processOne(int[] data, int preNum){
        if (data.length ==1){
            return preNum;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < data.length; i++) {
            for (int j = i+1; j < data.length; j++) {
                res = Math.min(res, processOne(removeData(data, i, j),preNum + data[i]+data[j]));
            }
        }
        return res;
    }
    public static int[] removeData(int[] data, int i, int j){
        int num = data.length;
        int[] res = new int[num-1];
        int index = 0;
        for (int k = 0; k < num; k++) {
            if (k != i && k!= j){
                res[index++] = data[k];
            }
        }
        res[index++] = data[i]+data[j];
        return res;
    }

    //贪心算法
    public static int jobTwo(int[] data){
        if (data == null || data.length == 0){
            return 0;
        }
        //小根堆（优先级队列）
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < data.length; i++) {
            heap.add(data[i]);
        }
        int res = 0;
        while (heap.size()>1){
            int sum = heap.poll()+heap.poll();
            res += sum;
            heap.add(sum);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = new int[]{10,20,30};
        System.out.println(jobOne(data));
        System.out.println(jobTwo(data));
    }

}
