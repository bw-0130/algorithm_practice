package A_One.T13;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡，只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯，
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发。
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 四个参数：int[] arr、int N、int a、int b
 */
public class C9 {

    public static class Machine {
        public int startTime;//开始时间
        public int workTime;//工作时长

        public Machine(int startTime, int workTime) {
            this.startTime = startTime;
            this.workTime = workTime;
        }
    }

    public static class myComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.startTime + o1.workTime) - (o2.startTime + o2.workTime);
        }
    }

    public static int jobOne(int[] arr, int N, int a, int b) {
        PriorityQueue<Machine> queue = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < arr.length; i++) {
            queue.offer(new Machine(0, arr[i]));
        }
        int[] drinks = new int[N];
        for (int i = 0; i < N; i++) {
            Machine poll = queue.poll();
            poll.startTime += poll.workTime;
            drinks[i] = poll.startTime;
            queue.offer(poll);
        }
        return process(drinks, a, b, 0, 0);
    }

    public static int process(int[] drinks, int a, int b, int index, int biginTime){
        if (drinks.length == index){
            return 0;
        }
        //手洗杯子（串行）
        int pt1 = Math.max(drinks[index]+a, biginTime);
        int pt2 = process(drinks, a, b, index+1, pt1);
        int p1 = Math.max(pt1, pt2);
        //自己挥发(可并行)
        int ptt1 = drinks[index]+b;
        int ptt2 = process(drinks, a, b, index+1, biginTime);
        int p2 = Math.max(ptt1, ptt2);
        return Math.max(p1, p2);
    }

    public static int jobTwo(int[] arr, int N, int a, int b){
        PriorityQueue<Machine> queue = new PriorityQueue<>(new myComparator());
        for (int i = 0; i < arr.length; i++) {
            queue.offer(new Machine(0, arr[i]));
        }
        int[] drinks = new int[N];
        for (int i = 0; i < N; i++) {
            Machine poll = queue.poll();
            poll.startTime += poll.workTime;
            drinks[i] = poll.startTime;
            queue.offer(poll);
        }
        int drLen = drinks.length;
        int biginTime = 0;
        for (int i = 0; i < drLen; i++) {
            biginTime  = Math.max(biginTime, drinks[i]+a);
        }
        int[][] dpMap = new int[drLen+1][biginTime+1];
        for (int i = drLen-1; i >= 0; i--) {
            for (int j = 0; j <= biginTime; j++) {
                //手洗杯子（串行）
                int pt1 = Math.max(drinks[i]+a, j);
                int pt2 = dpMap[i+1][pt1];
                int p1 = Math.max(pt1, pt2);
                //自己挥发(可并行)
                int ptt1 = drinks[i]+b;
                int ptt2 = dpMap[i+1][j];
                int p2 = Math.max(ptt1, ptt2);
                dpMap[i][j] = Math.max(p1, p2);
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
