package F11_greedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目的开始时间和结束时间，你来安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多返回最多的宣讲场次。
 */
public class T02_bestArrange {

    public static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //暴力递归方法
    public static int jobOne(Meeting[] data){
        if (data == null || data.length == 0){
            return 0;
        }
        return process(data, 0, 0);
    }
    public static int process(Meeting[] data, int num, int end){
        if (data.length == 0){
            return num;
        }
        int max = num;
        for (int i = 0; i < data.length; i++) {
            Meeting meeting = data[i];
            if (end<=meeting.start){
                Meeting[] newMeeting = removeMeeting(data, i);
                max = Math.max(max, process(newMeeting, num+1, meeting.end));
            }
        }
        return max;
    }
    public static Meeting[] removeMeeting(Meeting[] data, int index){
        int num = data.length;
        Meeting[] res = new Meeting[num-1];
        int a = 0;
        for (int i = 0; i < num; i++) {
            if (i != index){
                res[a++] = data[i];
            }
        }
        return res;
    }

    //贪心算法
    public static int jobTwo(Meeting[] data){
        if (data == null || data.length == 0){
            return 0;
        }
        Arrays.sort(data, new myComparator());
        int res = 0;
        int endTime = 0;
        for (int i = 0; i < data.length; i++) {
            Meeting meeting = data[i];
            if (endTime<=meeting.start){
                res++;
                endTime = meeting.end;
            }
        }
        return res;
    }
    //按会议结束时间正序比较器
    public static class myComparator implements Comparator<Meeting>{
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end-o2.end;
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
