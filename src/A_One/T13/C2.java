package A_One.T13;

/**
 * 背包问题
 * 有两个数组长度一致都为N
 * 数组a 表示重量，数组b表示价值
 * 现在有一个背包bag，假设背包能装载重量为k
 * 返回背包能装的最大价值为多少
 */
public class C2 {

    public static int jobOne(int[] a, int[] b, int k) {
        if (a == null || a.length == 0 || b == null || b.length == 0 || a.length != b.length || k <= 0) {
            return -1;
        }
        return process(a, b, 0, k);
    }

    public static int process(int[] a, int[] b, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (a.length == index) {
            return 0;
        }
        int p1 = process(a, b, index + 1, rest);
        int p2 = -1;
        int process = process(a, b, index + 1, rest - a[index]);
        if (process != -1) {
            p2 = process + b[index];
        }
        return Math.max(p1, p2);
    }

    public static int jobTwo(int[] a, int[] b, int k) {
        if (a == null || a.length == 0 || b == null || b.length == 0 || a.length != b.length || k <= 0) {
            return -1;
        }
        int N = a.length;
        int[][] dpMap = new int[N + 1][k + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                int p1 = dpMap[i + 1][j];
                int p2 = -1;
                if (j - a[i] >= 0) {
                    p2 = dpMap[i+1][j-a[i]]+b[i];
                }
                dpMap[i][j] = Math.max(p1, p2);
            }
        }
        return dpMap[0][k];
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 4, 7, 3, 1, 7};
        int[] b = {5, 6, 3, 19, 12, 4, 2};
        int k = 15;
        System.out.println(jobOne(a, b, k));
        System.out.println(jobTwo(a, b, k));
    }

}
