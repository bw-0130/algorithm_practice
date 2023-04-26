package A_One.T14;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个正数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值-sub中最小值<=num，
 * 返回arr中达标的子数组的数量。
 */
public class C2 {

    public static int job(int[] arr, int num) {
        int N = arr.length;
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        int r = 0;
        int res = 0;
        for (int l = 0; l < N; l++) {
            while (r < N) {
                while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[r]) {
                    maxList.pollLast();
                }
                maxList.addLast(r);
                while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[r]) {
                    minList.pollLast();
                }
                minList.addLast(r);
                if (arr[maxList.peekFirst()]-arr[minList.peekFirst()]>num){
                    break;
                }else {
                    r++;
                }
                res += r-l;
                if (maxList.peekFirst() == l){
                    maxList.pollFirst();
                }
                if (minList.peekFirst() == l){
                    minList.pollFirst();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3,3,6,7};
        int num = 4;
        System.out.println(job(arr, num));
    }

}
