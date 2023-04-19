package A_One.T13;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个几个包含数的个数必须差一个
 * 请尽量让两个集合的累加和接近
 * 返回：最接近的情况下，较小集合的累加和
 */
public class C19 {

    public static int jobOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if ((arr.length & 1) == 0) {//偶数
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {//奇数
            return Math.min(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    public static int process(int[] arr, int index, int len, int rest) {
        if (arr.length == index) {
            return len == 0 ? 0 : -1;
        }
        int p1 = process(arr, index + 1, len, rest);
        int p2 = -1;
        if (arr[index] <= rest) {
            int temp = process(arr, index + 1, len - 1, rest - arr[index]);
            if (temp != -1) {
                p2 = temp + arr[index];
            }
        }
        return Math.max(p1, p2);
    }

    public static int jobTwo(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum = sum / 2;
        int N = arr.length;
        int len = (N + 1) / 2;
        int[][][] dpMap = new int[N + 1][len + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < sum; k++) {
                    dpMap[i][j][k] = -1;
                }
            }
        }
        for (int i = 0; i <= sum; i++) {
            dpMap[N][0][i] = 0;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= len; j++) {
                for (int k = 0; k <= sum; k++) {
                    int p1 = dpMap[i + 1][j][k];
                    int p2 = -1;
                    if (arr[i] <= k && j - 1 >= 0) {
                        int temp = dpMap[i+1][j-1][k-arr[i]];
                        if (temp != -1) {
                            p2 = temp + arr[i];
                        }
                    }
                    dpMap[i][j][k] = Math.max(p1, p2);
                }
            }
        }
        if ((N & 1) == 0) {//偶数
            return dpMap[0][N/2][sum];
        } else {//奇数
            return Math.min(dpMap[0][N/2][sum], dpMap[0][N/2+1][sum]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,2};
        System.out.println(jobOne(arr));
        System.out.println(jobTwo(arr));
    }

}
