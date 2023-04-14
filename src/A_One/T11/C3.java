package A_One.T11;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度一样的铜板的。
 * 比如长度为20的金条，不管怎么切都要花费20铜板。一群人想分整块金条，怎么分最省铜板？
 * 例如，给定数组[10,20,30]，代表一共三个人，整块金条长度60，金条要分成10,20,30三个部分。
 * 输入一个数组，返回分割最小代价。
 * 斐波那契数列
 */
public class C3 {

    public static int jobOne(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        return process(data, 0);
    }

    public static int process(int[] data, int preNum) {
        if (data.length == 1) {
            return preNum;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                res = Math.min(res, process(removeData(data, i, j), preNum + data[i] + data[j]));
            }
        }
        return res;
    }

    public static int[] removeData(int[] data, int i, int j) {
        int[] res = new int[data.length - 1];
        int index = 0;
        for (int k = 0; k < data.length; k++) {
            if (k != i && k != j) {
                res[index++] = data[k];
            }
        }
        res[index++] = data[i] + data[j];
        return res;
    }

    public static int jobTwo(int[] data){
        if (data == null || data.length == 0){
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < data.length; i++) {
            queue.offer(data[i]);
        }
        int res = 0;
        while (queue.size() > 1){
            int sum = queue.poll() + queue.poll();
            res += sum;
            queue.offer(sum);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = new int[]{10,20,30};
        System.out.println(jobOne(data));
        System.out.println(jobTwo(data));
    }

}
