package A_One.T11;

import F11_greedyAlgorithm.T02_bestArrange;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目的开始时间和结束时间，你来安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多返回最多的宣讲场次。
 */
public class C2 {

    public static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //暴力递归
    public static int jobOne(Meeting[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        return process(data, 0, 0);
    }

    public static int process(Meeting[] data, int num, int end) {
        if (data.length == 0) {
            return num;
        }
        int max = num;
        for (int i = 0; i < data.length; i++) {
            Meeting meet = data[i];
            if (end <= meet.start) {
                Meeting[] newData = removeMeeting(data, i);
                max = Math.max(max, process(data, num + 1, meet.end));
            }
        }
        return max;
    }

    public static Meeting[] removeMeeting(Meeting[] data, int index) {
        Meeting[] res = new Meeting[data.length - 1];
        int n = 0;
        for (int i = 0; i < data.length; i++) {
            if (i != index) {
                res[n++] = data[i];
            }
        }
        return res;
    }

    //贪心算法
    public static int jobTwo(Meeting[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        Arrays.sort(data, new myComparator());
        int num = 0;
        int end = 0;
        for (int i = 0; i < data.length; i++) {
            Meeting metting = data[i];
            if (end <= metting.start){
                num++;
                end = metting.end;
            }
        }
        return num;
    }

    public static class myComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    // for test
    public static Meeting[] generatePrograms(int programSize, int timeMax) {
        Meeting[] ans = new Meeting[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting(r1, r1 + 1);
            } else {
                ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Meeting[] meetings = generatePrograms(10, 20);
        System.out.println(jobOne(meetings));
        System.out.println(jobTwo(meetings));
    }

}
