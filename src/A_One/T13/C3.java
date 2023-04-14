package A_One.T13;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都决定聪明
 * 请返回最后胜利者的分数
 */
public class C3 {

    public static int jobOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int p1 = firstProcess(arr, 0, arr.length - 1);
        int p2 = lastProcess(arr, 0, arr.length - 1);
        return Math.max(p1, p2);
    }

    public static int firstProcess(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int p1 = arr[l] + lastProcess(arr, l + 1, r);
        int p2 = arr[r] + lastProcess(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    public static int lastProcess(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int p1 = firstProcess(arr, l + 1, r);
        int p2 = firstProcess(arr, l, r - 1);
        return Math.min(p1, p2);
    }

    public static int jobTwo(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] firstMap = new int[n][n];
        int[][] lastMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            firstMap[i][i] = arr[i];
        }
        for (int i = 1; i < n; i++) {
            int l = 0;
            int r = i;
            while (r<n) {
                firstMap[l][r] = Math.max(arr[l] + lastMap[l + 1][r], arr[r] + lastMap[l][r - 1]);
                lastMap[l][r] = Math.min(firstMap[l + 1][r], firstMap[l][r - 1]);
                l++;
                r++;
            }
        }
        return Math.max(firstMap[0][n - 1], lastMap[0][n - 1]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 2, 5, 1};
        System.out.println(jobOne(arr));
        System.out.println(jobTwo(arr));
    }

}
