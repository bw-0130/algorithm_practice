package A_One.T13;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1,返回1
 * n=2或3, 2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class C20 {

    public static int job(int n) {
        if (n < 1) {
            return 0;
        }
        int[] temp = new int[n];
        return process(temp, 0, n);
    }

    public static int process(int[] temp, int index, int n) {
        if (index == n) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (check(temp, index, i)) {
                temp[index] = i;
                res += process(temp, index + 1, n);
            }
        }
        return res;
    }

    public static boolean check(int[] temp, int index, int j) {
        for (int i = 0; i < index; i++) {
            if (temp[i] == j ||Math.abs(index-i)==Math.abs(temp[i]-j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(job(n));
    }

}
