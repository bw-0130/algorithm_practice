package F13_dynamicPlanning;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡，只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯，每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发。
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 四个参数：int[] arr、int N、int a、int b
 */
public class C09_Coffee {

    public static class Machine {
        public int timePoint;//开始时间
        public int workTime;//工作时间

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    public static class myComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    //暴力递归
    public static int jobOne(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[N];
        for (int i = 0; i < N; i++) {
            Machine pollData = heap.poll();
            pollData.timePoint += pollData.workTime;
            drinks[i] = pollData.timePoint;
            heap.add(pollData);
        }
        return process(drinks, a, b, 0, 0);
    }

    public static int process(int[] drinks, int a, int b, int index, int beginTime) {
        if (index == drinks.length) {
            return 0;
        }
        //使用机器洗杯子
        int p1a = Math.max(drinks[index] + a, beginTime);
        int p1b = process(drinks, a, b, index + 1, p1a);
        int p1 = Math.max(p1a, p1b);
        //使用挥发洗杯子
        int p2a = drinks[index] + b;
        int p2b = process(drinks, a, b, index + 1, beginTime);
        int p2 = Math.max(p2a, p2b);
        return Math.min(p1, p2);
    }

    //动态规划方法
    public static int jobTwo(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[N];
        for (int i = 0; i < N; i++) {
            Machine pollData = heap.poll();
            pollData.timePoint += pollData.workTime;
            drinks[i] = pollData.timePoint;
            heap.add(pollData);
        }

        int n = drinks.length;
        int beginTime = 0;
        for (int i = 0; i < drinks.length; i++) {
            beginTime = Math.max(beginTime, drinks[i] + a);
        }
        int[][] dpMap = new int[n + 1][beginTime + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= beginTime; j++) {
                //使用机器洗杯子
                int p1a = Math.max(drinks[i] + a, j);
                int p1b = dpMap[i + 1][p1a];
                int p1 = Math.max(p1a, p1b);
                //使用挥发洗杯子
                int p2a = drinks[i] + b;
                int p2b = dpMap[i + 1][j];
                int p2 = Math.max(p2a, p2b);
                dpMap[i][j] = Math.min(p1, p2);
            }
        }
        return dpMap[0][0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 8};
        int a = 2;
        int b = 4;
        int N = 6;
        System.out.println(jobOne(arr, N, a, b));
        System.out.println(jobTwo(arr, N, a, b));
    }

}
