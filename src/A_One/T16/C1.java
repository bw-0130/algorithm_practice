package A_One.T16;

/**
 * 有固定递推公式的方法优化成O（longn）
 * 以斐波那契数列为例
 * 公式：f3=f1+f2
 * 优化后求第n项公式：
 * |fn,fn-1| = |f1,f2|*|固定矩阵|^n-2
 */
public class C1 {
    //矩阵相乘
    public static int[][] product(int[][] a, int[][] b) {
        int n1 = a.length;
        int n2 = b[0].length;
        int n3 = a[0].length;
        int[][] res = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int k = 0; k < n3; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public static int[][] matrixPower(int[][] data, int n) {
        int row = data.length;
        int col = data[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            res[i][i] = 1;
        }
        int[][] t = data;
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    public static int job(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] data = {{1,1}, {1,0}};
        int[][] ints = matrixPower(data, n-2);
        return ints[0][0] + ints[1][0];
    }

    public static void main(String[] args) {
        System.out.println(job(5));
    }

}
