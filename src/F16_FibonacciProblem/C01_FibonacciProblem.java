package F16_FibonacciProblem;

/**
 * 有固定递推公式的方法优化成O（longn）
 * 以斐波那契数列为例
 * 公式：f3=f1+f2
 * 优化后求第n项公式：
 * |fn,fn-1| = |f1,f2|*|固定矩阵|^n-2
 */
public class C01_FibonacciProblem {

    //矩阵相乘
    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;//运算时作为a的列，b的行
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    res[i][j] += a[i][l] * b[l][j];
                }
            }
        }
        return res;
    }

    //矩阵的次幂
    public static int[][] matrixPower(int[][] data, int p) {
        int row = data.length;
        int col = data[0].length;
        int[][] res = new int[row][col];
        //矩阵中的1
        for (int i = 0; i < row; i++) {
            res[i][i] = 1;
        }
        int[][] t = data;
        //快速求次幂的方法二进制拆解次幂，为1乘进来，为0则不要
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    //求斐波那契数列第n项的值
    public static int jobFibonacci(int n){
        if (n<1){
            return 0;
        }
        if (n==1||n==2){
            return 1;
        }
        int[][] jz = {{1,1},{1,0}};
        int[][] res = matrixPower(jz,(n-2));
        return res[0][0]+res[1][0];
    }

    public static void main(String[] args) {
        int n = 7;
        System.out.println(jobFibonacci(n));
    }

}
