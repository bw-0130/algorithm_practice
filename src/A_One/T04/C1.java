package A_One.T04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题（用堆实现）
 * 给定很多线段，每个线段都有两个数[start，end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：1）线段的开始和结束位置一定都是整数值
 * ​	2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class C1 {

    //线段封装类
    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //自定义比较器
    public static class myComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int job(int[][] data) {
        if (data == null || data.length < 2) {
            return 0;
        }
        Line[] list = new Line[data.length];
        for (int i = 0; i < data.length; i++) {
            list[i] = new Line(data[i][0], data[i][1]);
        }
        Arrays.sort(list, new myComparator());//排序
        PriorityQueue<Integer> heap = new PriorityQueue<>();//优先级队列（小根堆）
        int res = 0;
        for (int i = 0; i < list.length; i++) {
            Line line = list[i];
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.offer(line.end);
            res = Math.max(res, heap.size());
        }
        return res;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    //对数器
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static void main(String[] args) {
        int[][] data = generateLines(10, 0, 20);
        System.out.println(job(data));
        System.out.println(maxCover1(data));
    }

}
