package A_One.T14;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次滑过arr，
 * 返回每一次滑出状况的最大值。
 * 例如：arr=[4,3,5,4,3,3,6,7],W=3
 * 返回：[5,5,5,4,6,7]
 */
public class C1 {

    public static int[] job(int[] arr, int w) {
        int N = arr.length;
        int[] res = new int[N - w + 1];
        LinkedList<Integer> list = new LinkedList<>();
        int index = 0;
        for (int r = 0; r < N; r++) {
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[r]) {
                list.pollLast();
            }
            list.add(r);
            if (r - w == list.peekFirst()) {
                list.pollFirst();
            }
            if (r >= w - 1) {
                res[index++] = arr[list.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        int[] job = job(arr, w);
        for (int i = 0; i < job.length; i++) {
            System.out.print(job[i] + " ");
        }
    }

}
