package F14_slidingWindow;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次滑过arr，
 * 返回每一次滑出状况的最大值。
 * 例如：arr=[4,3,5,4,3,3,6,7],W=3
 * 返回：[5,5,5,4,6,7]
 *
 */
public class C01_SlidingWindowMaxArray {

    public static int[] job(int[] arr, int w){
        int N = arr.length;
        int[] res = new int[N-w+1];
        LinkedList<Integer> list = new LinkedList<>();//双端队列
        int index = 0;
        for (int i = 0; i < N; i++) {
            while (!list.isEmpty() && arr[list.peekLast()]<=arr[i]){
                list.pollLast();
            }
            list.addLast(i);
            if (i-w == list.peekFirst()){
                list.pollFirst();
            }
            if (i>=w-1){
                res[index++] = arr[list.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int w = 3;
        int[] job = job(arr, w);
        for (int i = 0; i < job.length; i++) {
            System.out.print(job[i]+" ");
        }
    }

}
