package F14_slidingWindow;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个正数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值-sub中最小值<=num，
 * 返回arr中达标的子数组的数量。
 */
public class C02_AllLessNumSubArray {

    public static int job(int[] arr, int num) {
        int n = arr.length;
        int count = 0;
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < n; L++) {
            while (R < n) {
                while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[R]) {
                    maxList.pollLast();
                }
                maxList.addLast(R);
                while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[R]) {
                    minList.pollLast();
                }
                minList.addLast(R);
                if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > num) {
                    break;
                }else {
                    R++;
                }
            }
            count += R-L;
            if (maxList.peekFirst() == L){
                maxList.pollFirst();
            }
            if (minList.peekFirst() == L){
                minList.pollFirst();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {3,3,6,7};
        int num = 4;
        System.out.println(job(arr, num));
    }

}
