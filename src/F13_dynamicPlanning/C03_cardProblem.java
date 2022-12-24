package F13_dynamicPlanning;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都决定聪明
 * 请返回最后胜利者的分数
 */
public class C03_cardProblem {

    //暴力递归方式
    public static int jobOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int p1 = firstProcess(arr, 0, arr.length - 1);
        int p2 = endProcess(arr, 0, arr.length - 1);
        return Math.max(p1, p2);
    }

    //先手
    public static int firstProcess(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int p1 = arr[l] + endProcess(arr, l + 1, r);
        int p2 = arr[r] + endProcess(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    public static int endProcess(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int p1 = firstProcess(arr, l + 1, r);
        int p2 = firstProcess(arr, l, r-1);
        return Math.min(p1, p2);
    }

    //动态规划方式
    public static int jobTwo(int[] arr){
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        int[][] fMap = new int[n][n];
        int[][] eMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            fMap[i][i] = arr[i];
        }
        for (int i = 1; i < n; i++) {
            int l = 0;
            int r = i;
            while (r<n){
                fMap[l][r] = Math.max(arr[l]+eMap[l+1][r],arr[r]+eMap[l][r-1]);
                eMap[l][r] = Math.min(fMap[l+1][r], fMap[l][r-1]);
                l++;
                r++;
            }
        }
        return Math.max(fMap[0][n-1], eMap[0][n-1]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 2, 5, 1};
        System.out.println(jobOne(arr));
        System.out.println(jobTwo(arr));
    }

}
